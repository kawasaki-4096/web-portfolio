package com.studio4096.portfolio.service;

import static com.studio4096.portfolio.cnstants.Enabled.TRUE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.iciql.Db;
import com.iciql.IciqlException;
import com.studio4096.portfolio.action.PortfolioActionBeanContext;
import com.studio4096.portfolio.dao.DaoCommand;
import com.studio4096.portfolio.dao.DaoManager;
import com.studio4096.portfolio.dto.BookDto;
import com.studio4096.portfolio.entity.Book;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.exception.ServiceException.Kind;

public class BookService {
	public static Book getBook(final Long bookId) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<Book>() {

			public Book execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Book book =new Book();
				return db.from(book).where(book.bookId).is(bookId).selectFirst();
			}
		});
	}

	public static List<Book> findBooks(PortfolioActionBeanContext context) throws ServiceException {
		Map<Long, BookDto> bookCache = context.getBookCache();
		
		DaoManager dm = DaoManager.getDefaultDaoManager();
		List<Book> l = dm.executeAndClose(new DaoCommand<List<Book>>() {

			public List<Book> execute(Connection connection,DaoManager daoManager)
					throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Book book =new Book();
				try{
				return db.from(book).where(book.enabled).is("1")
						.and(book.publicFlag).is("1").select();
				}catch (IciqlException e) {
					throw new ServiceException(e, Kind.DB_ERROR);
				}
			}
		});
		
		for(Book e : l){
			BookDto bookDto = bookCache.get(e.getBookId());
			if (bookDto == null) {
				bookDto = new BookDto();
				bookDto.book = e;
				bookCache.put(e.getBookId(), bookDto);
			}
		}
		return l;
	}
	
	public static BookDto getBookDto(long bookId,PortfolioActionBeanContext context) throws ServiceException{
		Map<Long, BookDto> bookCache = context.getBookCache();
		BookDto bookDto = bookCache.get(bookId);
		if (bookDto != null) {
			if (bookDto.pageMap == null || bookDto.pageMap.isEmpty()) {
				bookDto.pageMap = PageService.getPageMap(bookId);
			}
		} else {
			bookDto = new BookDto();
			bookDto.book = BookService.getBook(bookId);
			if (bookDto.book == null) {
				return null;
			}
			bookDto.pageMap = PageService.getPageMap(bookId);
			bookCache.put(bookDto.book.getBookId(), bookDto);
		}
		return bookDto;
	}

	public static int saveBook(final Book book) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<Integer>() {

			public Integer execute(Connection connection,DaoManager daoManager)
					throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Date now = Calendar.getInstance().getTime();
				book.setModified(now);
				if (book.getBookId() == null) {
					book.setEnabled(TRUE.VAL);
					book.setCreated(now);
					db.insert(book);
					return 1;
				} else {
					book.setEnabled(TRUE.VAL);
					return db.update(book);
				}
			}
		});
	}

}
