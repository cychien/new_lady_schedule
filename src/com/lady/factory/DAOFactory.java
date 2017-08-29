package com.lady.factory;

import com.lady.dao.*;
import com.lady.entity.PaySummaryInfo;

public abstract class DAOFactory {
	public static final int MSSQL = 1;
	public static final int MySQL = 2;
	
	public abstract EmployeeDAO getEmployeeDAO();
	public abstract CounterDAO getCounterDAO();
	public abstract PositionDAO getPositionDAO();
//	public abstract AuthorityDAO getAuthorityDAO();
	public abstract RestDAO getRestDAO();
//	public abstract GetAccessToDAO getGetAccessToDAO();
	public abstract TransferToDAO getTransferToDAO();
	public abstract AreaDAO getAreaDAO();
	public abstract SpecialDAO getSpecialDAO();
	public abstract BonusDAO getBonusDAO();
	public abstract PaySummaryInfoDAO getPaySummaryInfoDAO();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MSSQL:
			return new MSSQLDAOFactory();
		case MySQL:
			return new MySQLDAOFactory();
		default: 
			return null;
		}
	}
}
