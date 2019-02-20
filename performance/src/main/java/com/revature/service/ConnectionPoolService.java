package com.revature.service;

import java.sql.Connection;

public interface ConnectionPoolService {

	Connection getConnection();
}
