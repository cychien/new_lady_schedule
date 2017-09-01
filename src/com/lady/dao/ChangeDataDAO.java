package com.lady.dao;

import com.lady.entity.ChangeData;

public interface ChangeDataDAO {
    public ChangeData findChangeDataFromEmployeeId(int employeeId, int year, int month);
    public void insertChangeData(ChangeData changeData);
    public void modifyChangeData(int employeeId, String column, String newValue, int year, int month);
    public void modifySalesPerformance(int employeeId, int month, int originMoney, double percent, int newMoney, int year);
}
