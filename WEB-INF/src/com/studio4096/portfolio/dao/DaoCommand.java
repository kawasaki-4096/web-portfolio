package com.studio4096.portfolio.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.studio4096.portfolio.exception.ServiceException;

public interface DaoCommand<T> {
	 public T execute(Connection connection,DaoManager daoManager) throws SQLException,ServiceException;
}
