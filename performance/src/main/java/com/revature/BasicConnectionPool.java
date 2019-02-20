package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasicConnectionPool implements ConnectionPool {

	private static final Logger logger = LogManager.getLogger(BasicConnectionPool.class);

	private static final ConnectionPool instance = new BasicConnectionPool();
	private static final int INITIAL_POOL_SIZE = 25;
	private final List<Connection> connections = new ArrayList<>();
	private final List<Connection> usedConnections = new ArrayList<>(INITIAL_POOL_SIZE);
	
	public static ConnectionPool getInstance() {
		return instance;
	}
	
	private BasicConnectionPool() {
		super();
		final Properties props = getConnectionProperties();
		for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
				connections.add(DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password")));
			} catch (SQLException e) {
				logger.fatal("Failed to obtain JDBC Connection: {}", e.getMessage());
				logger.fatal("SQL State: {}", e.getSQLState());
				logger.fatal("Error Code: {}", e.getErrorCode());
				throw new RuntimeException(e);
			} catch (ClassNotFoundException e) {
				logger.fatal("Failed to load JDBC Driver: {}", e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public Connection getConnection() {
		Connection conn = connections.get(INITIAL_POOL_SIZE - this.usedConnections.size() - 1);
		usedConnections.add(conn);
		return conn;
	}

	@Override
	public boolean release(Connection connection) {
		connections.add(connection);
		return usedConnections.remove(connection);
	}

	@Override
	public void shutdown() {
		for (Connection c : connections)
			try {
				c.close();
			} catch (SQLException e) {
				logger.fatal("Failed to obtain JDBC Connection: {}", e.getMessage());
				logger.fatal("SQL State: {}", e.getSQLState());
				logger.fatal("Error Code: {}", e.getErrorCode());
				throw new RuntimeException(e);
			}
		for (Connection c : usedConnections)
			try {
				c.close();
			} catch (SQLException e) {
				logger.fatal("Failed to obtain JDBC Connection: {}", e.getMessage());
				logger.fatal("SQL State: {}", e.getSQLState());
				logger.fatal("Error Code: {}", e.getErrorCode());
				throw new RuntimeException(e);
			}
	}

	private static Properties getConnectionProperties() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			logger.fatal("Failed to load application.properties: {}", e.getMessage());
			throw new RuntimeException(e);
		}
		return properties;
	}
}
