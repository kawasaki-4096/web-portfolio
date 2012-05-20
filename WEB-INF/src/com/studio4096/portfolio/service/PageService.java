package com.studio4096.portfolio.service;

import static com.studio4096.portfolio.cnstants.Enabled.TRUE;
import static com.studio4096.portfolio.exception.ServiceException.Kind.DB_ERROR;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iciql.Db;
import com.iciql.IciqlException;
import com.studio4096.portfolio.action.PortfolioActionBeanContext;
import com.studio4096.portfolio.dao.DaoCommand;
import com.studio4096.portfolio.dao.DaoManager;
import com.studio4096.portfolio.dto.PageDto;
import com.studio4096.portfolio.entity.Book;
import com.studio4096.portfolio.entity.Page;
import com.studio4096.portfolio.exception.ServiceException;

public class PageService {
	

	public static Page getPage(final long pageId) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<Page>() {
			public Page execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Page page =new Page();
				return db.from(page).where(page.pageId).is(pageId).selectFirst();
			}
		});
	}

	
	public static List<Page> findPages(final long bookId) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<List<Page>>() {

			public List<Page> execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Page page =new Page();
				return db.from(page).where(page.bookId).is(bookId).orderBy(page.sortOrder).select();
			}
		});
	}
	public static List<Page> findAllPages(PortfolioActionBeanContext context) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		List<Page> l = dm.executeAndClose(new DaoCommand<List<Page>>() {

			public List<Page> execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Page page =new Page();
				return db.from(page).orderBy(page.bookId,page.sortOrder).select();
			}
		});
		// TODO kawasaki cache
		return l;
	}

	public static void sortPages(final long bookId, final Long[] pageIds)
			throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		dm.transactionAndClose(new DaoCommand<Integer>() {
			public Integer execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				try {
					Db db= Db.open(connection);
					int i = 1;
					Book book =new Book();
					db.from(book).set(book.getFirstPageId()).to(pageIds[0]).where(book.getBookId()).is(bookId).update();
					for (Long id : pageIds) {
						Page page =new Page();
						int res =db.from(page).set(page.getSortOrder()).to(i).where(page.getBookId()).is(bookId).and(page.getPageId()).is(id).update();
						if(res > 0){
							i++;
						}
					}
					// TODO kawasaki case of not inoughed.
				} catch (IciqlException e) {
					new ServiceException(e,DB_ERROR);
				}
				return 1;// TODO this is dummy.
			}
		});
	}

	public static void setPageImage(final long pageId,final long imageId) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		dm.executeAndClose(new DaoCommand<Integer>() {
			public Integer execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				try {
					Db db= Db.open(connection);
				Page page =new Page();
					return db.from(page).set(page.imageId).to(imageId).where(page.pageId).is(pageId).update();
				} catch (IciqlException e) {
					throw new ServiceException(e,DB_ERROR);
				}
			}
		});
		return ;
		
	}
	public static int savePage(final Page page) throws ServiceException {
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<Integer>() {
			public Integer execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				try {
					Db db= Db.open(connection);
				Date now = Calendar.getInstance().getTime();
				page.setModified(now);
				if (page.getPageId() == null) {
					page.setEnabled(TRUE.VAL);
					page.setCreated(now);
					page.pageId = db.insertAndGetKey(page);
					return 1;
				} else {
					page.setEnabled(TRUE.VAL);
					return db.update(page);
				}
				} catch (IciqlException e) {
					throw new ServiceException(e,DB_ERROR);
				}
			}
		});
	}

	public static Map<Long, PageDto> getPageMap(long bookId) throws ServiceException {
		Map<Long, PageDto> map = new HashMap<Long, PageDto>();
		List<Page> pageList = findPages(bookId);
		PageDto prevPortfolioPage = null;
		for (Page page : pageList) {
			PageDto portfolioPage = new PageDto();
			map.put(page.getPageId(), portfolioPage);
			portfolioPage.preview = prevPortfolioPage;
			portfolioPage.current = page;
			if (prevPortfolioPage != null) {
				prevPortfolioPage.next = portfolioPage;
			}
			prevPortfolioPage = portfolioPage;
		}
		return map;
	}

	public static void deletePage(final Long pageId) throws ServiceException{
		DaoManager dm = DaoManager.getDefaultDaoManager();
		dm.executeAndClose(new DaoCommand<Integer>() {

			public Integer execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Page page =new Page();
				return db.from(page).where(page.pageId).is(pageId).delete();
			}
		});
		
	}



}
