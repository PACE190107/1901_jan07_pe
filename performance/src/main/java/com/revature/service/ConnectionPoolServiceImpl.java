package com.revature.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ConnectionPoolServiceImpl implements ConnectionPoolService {

	private final DataSource dataSource;
	
	
	private static ConnectionPoolService instance;
	
	private ConnectionPoolServiceImpl(final DataSource dataSource) {
		super();
//		try {
//			Context initContext = new InitialContext();
//			Context envContext = (Context) initContext.lookup("java:/comp/env");
//			this.dataSource = (DataSource) envContext.lookup("jdbc/PaceCPDemo");
//		} catch (NamingException e) {
//			throw new RuntimeException(e);
//		}
		this.dataSource = dataSource;
	}
	
	public static ConnectionPoolService getInstance(final DataSource dataSource) {
		if (instance == null)
			instance = new ConnectionPoolServiceImpl(dataSource);
		return instance;
	}
	
	@Override
	public Connection getConnection() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
