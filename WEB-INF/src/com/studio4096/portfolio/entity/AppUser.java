package com.studio4096.portfolio.entity;

import java.io.Serializable;
import java.util.Date;

import com.iciql.Iciql.IQColumn;
import com.iciql.Iciql.IQTable;

@IQTable(name="APP_USER")
public class AppUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7386172610714271594L;
	@IQColumn(name="user_id",autoIncrement=true,primaryKey=true)
	public Long userId;
	@IQColumn(name="login_id")
	public String loginId;
	@IQColumn
	public String password;
	@IQColumn(name="screen_name")
	public String screenName;
	@IQColumn
	public String enabled;
	@IQColumn
	public Date created;
	@IQColumn
	public Date modified;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
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
