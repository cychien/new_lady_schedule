package com.lady.dao;

import com.lady.entity.Bonus;

public interface BonusDAO {
    public int findBonusFromEmployeeIdAndMonth(int employeeId, int month, int year);
    public void insertBonus(Bonus bonus);
    public void modifyBonus(int employeeId, int month, int originMoney, double percent, int newMoney, int year);
    public int readModify(int employeeId, int month, int year);
}
