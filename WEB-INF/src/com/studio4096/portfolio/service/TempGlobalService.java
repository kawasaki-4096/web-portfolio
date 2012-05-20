package com.studio4096.portfolio.service;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.iciql.Db;
import com.studio4096.portfolio.dao.DaoCommand;
import com.studio4096.portfolio.dao.DaoManager;
import com.studio4096.portfolio.entity.AppUser;
import com.studio4096.portfolio.entity.Site;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.util.MD5;

public class TempGlobalService {

	public static AppUser getUser(final String loginId, String password)
			throws ServiceException {
		final String encryptedPassword;
		try {
			encryptedPassword =MD5.crypt(password);
		} catch (NoSuchAlgorithmException e) {
			throw new ServiceException(e, null);
		}
		DaoManager dm = DaoManager.getDefaultDaoManager();
		return dm.executeAndClose(new DaoCommand<AppUser>() {
			public AppUser execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				AppUser user =new AppUser();
				return db.from(user).where(user.loginId).is(loginId).and(user.password).is(encryptedPassword).selectFirst();
			}
		});
	}
	public static void main(String[] args) throws Exception {
		System.out.print(MD5.crypt("heavy1024"));
	}
	public static Site getSite() throws ServiceException{
		DaoManager dm = DaoManager.getDefaultDaoManager();
		List<Site> l =dm.executeAndClose(new DaoCommand<List<Site>>() {
			public List<Site> execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException {
				Db db= Db.open(connection);
				Site site =new Site();
				return db.from(site).select();
			}
		});
		if(l.isEmpty()){
			return null;
		}
		return l.get(0);
	}

}
