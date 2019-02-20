package com.revature.model;

import java.util.List;
import java.util.Objects;

public class Statistics {

	private double totalTime;
	private double averageTime;
	private int countOfConnections;

	public Statistics() {
	}

	public Statistics(List<Double> measurements) {
		this.totalTime = measurements.stream().reduce(0.0, (a, b) -> a + b);
		this.countOfConnections = measurements.size();
		this.averageTime = this.totalTime / this.countOfConnections;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public double getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(double averageTime) {
		this.averageTime = averageTime;
	}

	public int getCountOfConnections() {
		return countOfConnections;
	}

	public void setCountOfConnections(int countOfConnections) {
		this.countOfConnections = countOfConnections;
	}

	@Override
	public int hashCode() {
		return Objects.hash(averageTime, countOfConnections, totalTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Statistics)) {
			return false;
		}
		Statistics other = (Statistics) obj;
		return Double.doubleToLongBits(averageTime) == Double.doubleToLongBits(other.averageTime)
				&& countOfConnections == other.countOfConnections
				&& Double.doubleToLongBits(totalTime) == Double.doubleToLongBits(other.totalTime);
	}

}
