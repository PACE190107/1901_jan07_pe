package com.revature.service;

import java.util.List;

import com.revature.model.Statistics;

public class ConnectionPoolStatsServiceImpl implements ConnectionPoolStatsService {
	
	@Override
	public Statistics getConnectionPoolStatistics(List<Double> measurements) {
		return new Statistics(measurements);
	}

}
