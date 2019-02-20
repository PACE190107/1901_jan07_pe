package com.revature.service;

import java.util.List;

import com.revature.model.Statistics;

public interface ConnectionPoolStatsService {

	Statistics getConnectionPoolStatistics(List<Double> measurements);
}
