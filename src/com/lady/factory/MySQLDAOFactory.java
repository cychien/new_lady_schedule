package com.lady.factory;

import com.lady.dao.AreaDAO;
import com.lady.dao.CounterDAO;
import com.lady.dao.EmployeeDAO;
import com.lady.dao.PositionDAO;
import com.lady.dao.RestDAO;
import com.lady.dao.SpecialDAO;
import com.lady.dao.TransferToDAO;
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

}
