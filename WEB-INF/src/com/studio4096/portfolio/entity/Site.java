package com.studio4096.portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;
@IQTable
public class Site implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6095065301978090903L;
	@IQColumn(name="site_id",autoIncrement=true,primaryKey=true)
	public Long siteId;
	@IQColumn(name="site_name")
	public String siteName;
	@IQColumn(name="top_book_id")
	public Long topBookId;
	@IQColumn
	public String enabled;
	@IQColumn
	public Date created;
	@IQColumn
	public Date modified;

	/**
	 * siteIdを取得します。
	 * 
	 * @return siteId
	 */
	public Long getSiteId() {
		return siteId;
	}

	/**
	 * siteIdを設定します。
	 * 
	 * @param siteId
	 *            siteId
	 */
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	/**
	 * siteNameを取得します。
	 * 
	 * @return siteName
	 */
	public String getSiteName() {
		return siteName;
	}

	/**
	 * siteNameを設定します。
	 * 
	 * @param siteName
	 *            siteName
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * topBookIdを取得します。
	 * 
	 * @return topBookId
	 */
	public Long getTopBookId() {
		return topBookId;
	}

	/**
	 * topBookIdを設定します。
	 * 
	 * @param topBookId
	 *            topBookId
	 */
	public void setTopBookId(Long topBookId) {
		this.topBookId = topBookId;
	}

	/**
	 * enabledを取得します。
	 * 
	 * @return enabled
	 */
	public String getEnabled() {
		return enabled;
	}

	/**
	 * enabledを設定します。
	 * 
	 * @param enabled
	 *            enabled
	 */
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	/**
	 * createdを取得します。
	 * 
	 * @return created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * createdを設定します。
	 * 
	 * @param created
	 *            created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * modifiedを取得します。
	 * 
	 * @return modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * modifiedを設定します。
	 * 
	 * @param modified
	 *            modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}
}
