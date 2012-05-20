package com.studio4096.portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable
public class Page implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1743636941357352763L;
	@IQColumn(name="page_id",autoIncrement=true,primaryKey=true)
	public Long pageId;
	
	@IQColumn(name="book_id")
	public Long bookId;

	@IQColumn(name="image_id")
	public Long imageId;

	@IQColumn(name="sort_order")
	public Integer sortOrder;

	@IQColumn(name="public_flag")
	public String publicFlag;

	@IQColumn
	public String enabled;

	@IQColumn
	public Date created;

	@IQColumn
	public Date modified;
	
	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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
