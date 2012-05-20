package com.studio4096.portfolio.dao;

import static com.studio4096.portfolio.exception.ServiceException.Kind.DB_ERROR;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

import com.studio4096.portfolio.exception.ServiceException;

public class DaoManager {
	private static DaoManager defaultInstance;
	protected DataSource dataSource = null;
//	protected Connection connection = null;

	public DaoManager(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public static DaoManager getDefaultDaoManager(){
		if(defaultInstance !=null){
			return defaultInstance;
		}
		Properties p = new Properties();
		try {
			InputStream is =DaoManager.class.getResourceAsStream("/DbSetting.properties");
			p.load(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			Class.forName(p.getProperty("jdbcClass"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		JdbcConnectionPool dataSource =
		JdbcConnectionPool.create(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
//		JdbcDataSource dataSource = new JdbcDataSource();
//		dataSource.setURL();
//		dataSource.setUser(p.getProperty("user"));
//		dataSource.setPassword(p.getProperty("password"));
//		try {
//			dataSource.getConnection().setAutoCommit(false);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
		defaultInstance = new DaoManager(dataSource);
		return defaultInstance;
	}

	protected Connection getConnection() throws SQLException {
		return getConnection(false);
	}

	protected Connection getConnection(boolean isTransaction) throws SQLException {
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(!isTransaction);
		return connection;
	}

	public <T> T  transactionAndClose(DaoCommand<T> command) throws ServiceException {
		Connection connection =null;
		try {
			connection =getConnection(true);
			T returnValue = command.execute(connection,this);
			connection.commit();
			return returnValue;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new ServiceException(e1,DB_ERROR);
			}
			throw new ServiceException(e,DB_ERROR);
		}catch (ServiceException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new ServiceException(e1,DB_ERROR);
			}
			throw e;
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				throw new ServiceException(e,DB_ERROR);
			}
		}
	}

	public <T> T  executeAndClose(DaoCommand<T> command) throws ServiceException {
		Connection connection =null;
		try {
			connection =getConnection();
			return command.execute(connection,this);
		} catch (SQLException e) {
			throw new ServiceException(e,DB_ERROR);
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				throw new ServiceException(e,DB_ERROR);
			}
		}
	}

//	public <T> T transactionAndClose(final DaoCommand<T> command)
//			throws ServiceException {
//		return executeAndClose(new DaoCommand<T>() {
//			public T  execute(Connection connection,DaoManager manager) throws SQLException,ServiceException {
//				return manager.transaction(command);
//			}
//		});
//	}
}
