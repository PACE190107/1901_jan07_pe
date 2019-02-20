package com.revature;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.model.User;
import com.revature.service.ConnectionPoolService;
import com.revature.service.ConnectionPoolServiceImpl;

public class UserServlet extends HttpServlet {
	
	@Resource(name="jdbc/PaceCPDemo")
	private DataSource dataSource;
	
	private static final long serialVersionUID = 1L;
	private final ConnectionPoolService cp = ConnectionPoolServiceImpl.getInstance(dataSource);
	private static final ObjectMapper mapper = new ObjectMapper();
       
    public UserServlet() {
        super();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		try {
			response.getWriter().println(mapper.writeValueAsString(getUsersFromDatabase()));
		} catch (SQLException e) {
			response.getWriter().println("Failed to retrieve users");
		}
	}

	private List<User> getUsersFromDatabase() throws SQLException {
		List<User> users = new ArrayList<>();
		Connection conn = cp.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT username, password, email, role FROM ers_user");
		ResultSet rs = stmt.executeQuery();
		while (rs.next())
			users.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("role")));
		return users;
	}
}
