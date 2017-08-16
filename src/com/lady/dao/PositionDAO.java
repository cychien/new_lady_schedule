package com.lady.dao;

import com.lady.entity.Position;

public interface PositionDAO {
//	public List<Employee> readEmployee();
	public void insertEmployee(Position position);
	public void deleteEmployee(int id);
	public void updateEmployee(int id, Position position);
}
