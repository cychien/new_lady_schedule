package com.lady.factory;

import com.lady.dao.*;
import com.lady.daoImpl.*;

public class MSSQLDAOFactory extends DAOFactory{

	@Override
	public EmployeeDAO getEmployeeDAO() {
		return new MSSQLEmployeeDAO();
	}

	@Override
	public CounterDAO getCounterDAO() {
		return new MSSQLCounterDAO();
	}

	@Override
	public PositionDAO getPositionDAO() {
		return new MSSQLPositionDAO();
	}

	@Override
	public RestDAO getRestDAO() {
		return new MSSQLRestDAO();
	}

	@Override
	public TransferToDAO getTransferToDAO() {
		return new MSSQLTransferToDAO();
	}

	@Override
	public AreaDAO getAreaDAO() {
		return new MSSQLAreaDAO();
	}

	@Override
	public SpecialDAO getSpecialDAO() {
		return new MSSQLSpecialDAO();
	}

	@Override
	public BonusDAO getBonusDAO() {
		return null;
	}

	@Override
	public PaySummaryInfoDAO getPaySummaryInfoDAO() {
		return null;
	}

}
