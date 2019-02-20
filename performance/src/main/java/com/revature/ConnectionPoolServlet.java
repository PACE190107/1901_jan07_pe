package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.model.SummaryStatistics;
import com.revature.service.ConnectionPoolService;
import com.revature.service.ConnectionPoolServiceImpl;
import com.revature.service.ConnectionPoolStatsService;
import com.revature.service.ConnectionPoolStatsServiceImpl;

public class ConnectionPoolServlet extends HttpServlet {
	
	@Resource(name="jdbc/PaceCPDemo")
	private DataSource dataSource;
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(ConnectionPoolServlet.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final ConnectionPoolStatsService stats = new ConnectionPoolStatsServiceImpl();
	
	private final ConnectionPoolService cp = ConnectionPoolServiceImpl.getInstance(dataSource);
	private final ConnectionPool homemade = BasicConnectionPool.getInstance();
	
	public ConnectionPoolServlet() {
		super();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		List<Double> measurements = new ArrayList<>();
		SummaryStatistics statistics = new SummaryStatistics();
		Connection conn = null;
		
		/*
		 * Adding TomcatCP stats
		 */
		for (int i = 0; i < 25; i++) {
			try {
				logger.info("Getting Connection from the Connection Pool...");
				long start = System.currentTimeMillis();
				conn = cp.getConnection();
				long end = System.currentTimeMillis();
				measurements.add(new Double(end - start));
				conn.close();
			} catch (SQLException e) {
				logger.fatal("Failed to close JDBC Connection: {}", e.getMessage());
				logger.fatal("SQL State: {}", e.getSQLState());
				logger.fatal("Error Code: {}", e.getErrorCode());
				throw new RuntimeException(e);
			}
		}
		statistics.setTomcat(stats.getConnectionPoolStatistics(measurements));
		
		/*
		 * Adding homemade connection pool stats
		 */
		measurements = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			long start = System.currentTimeMillis();
			conn = homemade.getConnection();
			long end = System.currentTimeMillis();
			measurements.add(new Double(end - start));
			homemade.release(conn);
		}
		statistics.setHomemade(stats.getConnectionPoolStatistics(measurements));
		
		/*
		 * ConnectionUtil stats
		 */
		measurements = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			long start = System.currentTimeMillis();
			conn = ConnectionUtil.getConnection();
			long end = System.currentTimeMillis();
			measurements.add(new Double(end - start));
			try {
				conn.close();
			} catch (SQLException e) {
				logger.fatal("Failed to close JDBC Connection: {}", e.getMessage());
				logger.fatal("SQL State: {}", e.getSQLState());
				logger.fatal("Error Code: {}", e.getErrorCode());
				throw new RuntimeException(e);
			}
		}
		statistics.setUtil(stats.getConnectionPoolStatistics(measurements));
		resp.getWriter().println(mapper.writeValueAsString(statistics));
	}
}
