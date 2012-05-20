package com.studio4096.portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;
@IQTable
public class Book implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2375293317321815332L;
	@IQColumn(name="BOOK_ID",autoIncrement=true,primaryKey=true)
	public  Long bookId;
	@IQColumn(name="book_name",length=128)
	public String bookName;
	@IQColumn(name="first_page_id",nullable=false)
	public Long firstPageId;
	@IQColumn(name="cover_image_id")
	public Long coverImageId;
	@IQColumn(name="public_flag",length=1)
    public String publicFlag;
	@IQColumn(length=1)
    public String enabled;
	@IQColumn
    public Date created;
	@IQColumn
    public Date modified;
    public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Long getFirstPageId() {
		return firstPageId;
	}
	public void setFirstPageId(Long firstPageId) {
		this.firstPageId = firstPageId;
	}
	public Long getCoverImageId() {
		return coverImageId;
	}
	public void setCoverImageId(Long coverImageId) {
		this.coverImageId = coverImageId;
	}
	public String getPublicFlag() {
		return publicFlag;
	}
	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
}
