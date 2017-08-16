package com.lady.dao;

import java.util.List;

import com.lady.entity.Counter;

public interface CounterDAO {
	public List<Counter> readCounter();
	public int insertCounter(Counter counter);
	public void deleteCounter(int counterId);
	public void updateCounter(int counterId, Counter counter);
	
	public Counter selectCounter(int counterId);
	public int findCounterId(String counterName);
	public List<Counter> findCounterFromAreaId(int areaId);
	public String[] getWorkTime(int counterId);
	public String findCounterName(int counterId);
	public String findOnTimeFromDateAndId(String date, int counterId);
	public String findOffTimeFromDateAndId(String date, int counterId);
	public int isValid(int areaId, int counterId);
}
