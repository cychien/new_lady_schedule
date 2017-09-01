package com.lady.factory;

import com.lady.dao.*;
import com.lady.daoImpl.*;

public class MySQLDAOFactory extends DAOFactory{

	@Override
	public EmployeeDAO getEmployeeDAO() {
		return new MySQLEmployeeDAO();
	}

	@Override
	public CounterDAO getCounterDAO() {
		return new MySQLCounterDAO();
	}

	@Override
	public PositionDAO getPositionDAO() {
		return new MySQLPositionDAO();
	}

	@Override
	public RestDAO getRestDAO() {
		return new MySQLRestDAO();
	}

	@Override
	public TransferToDAO getTransferToDAO() {
		return new MySQLTransferToDAO();
	}

	@Override
	public AreaDAO getAreaDAO() {
		return new MySQLAreaDAO();
	}

	@Override
	public SpecialDAO getSpecialDAO() {
		return new MySQLSpecialDAO();
	}

	@Override
	public BonusDAO getBonusDAO() {
		return new MySQLBonusDAO();
	}

	@Override
	public PaySummaryInfoDAO getPaySummaryInfoDAO() {
		return new MySQLPaySummaryInfoDAO();
	}

	@Override
	public ChangeDataDAO getChangeDataDAO() {
		return new MySQLChangeDataDAO();
	}

}
