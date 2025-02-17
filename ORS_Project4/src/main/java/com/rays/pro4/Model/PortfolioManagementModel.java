package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.rays.pro4.Bean.PortfolioManagementBean;
import com.rays.pro4.Util.JDBCDataSource;

public class PortfolioManagementModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from portfolio_management");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public long add(PortfolioManagementBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into portfolio_management values (?,?,?,?,?,?,?,?,?)");
		int pk = nextPk();

		pstmt.setLong(1, pk);
		pstmt.setString(2, bean.getPortfolioName());
		pstmt.setLong(3, bean.getInitialInvestmentAmount());
		pstmt.setString(4, bean.getRiskToleranceLevel());
		pstmt.setString(5, bean.getInvestmentStrategy());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDatetime());
		pstmt.setTimestamp(9, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		System.out.println("Data Inserted  Successfully =====>" + i);
		return pk;

	}

	public void update(PortfolioManagementBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update portfolio_management set portfolio_name = ?, initial_investment_amount = ?, risk_tolerance_level = ?, investment_strategy = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setString(1, bean.getPortfolioName());
		pstmt.setLong(2, bean.getInitialInvestmentAmount());
		pstmt.setString(3, bean.getRiskToleranceLevel());
		pstmt.setString(4, bean.getInvestmentStrategy());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDatetime());
		pstmt.setTimestamp(8, bean.getModifiedDatetime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully =====>" + i);

	}

	public void delete(PortfolioManagementBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from portfolio_management where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully =====>" + i);
	}

	public java.util.List search(PortfolioManagementBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from portfolio_management where 1=1");

		if (bean != null) {
			System.out.println("bean not = nullllllllllllll");

			if (bean.getPortfolioName() != null && bean.getPortfolioName().length() > 0) {
				sql.append(" and portfolioName like '" + bean.getPortfolioName() + "%'");
			}
			if (bean.getInitialInvestmentAmount() != null) {
				sql.append(" and initialInvestmentAmount like '" + bean.getInitialInvestmentAmount() + "%'");
			}

			if (bean.getRiskToleranceLevel() != null && bean.getRiskToleranceLevel().length() > 0) {
				sql.append(" and riskToleranceLevel like '" + bean.getRiskToleranceLevel() + "%'");
			}
			if (bean.getInvestmentStrategy() != null && bean.getInvestmentStrategy().length() > 0) {
				sql.append(" and investmentStrategy like '" + bean.getInvestmentStrategy() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		System.out.println(sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		java.util.List list = new ArrayList();

		while (rs.next()) {

			bean = new PortfolioManagementBean();

			bean.setId(rs.getLong(1));
			bean.setPortfolioName(rs.getString(2));
			bean.setInitialInvestmentAmount(rs.getLong(3));
			bean.setRiskToleranceLevel(rs.getString(4));
			bean.setInvestmentStrategy(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));

			list.add(bean);
		}
		return list;
	}

	public PortfolioManagementBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from portfolio_management where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		PortfolioManagementBean bean = null; // Initialize the bean to null

		if (rs.next()) {
			bean = new PortfolioManagementBean(); // Create a new instance of stock_purchaseBean when data is found

			bean.setId(rs.getLong(1));
			bean.setPortfolioName(rs.getString(2));
			bean.setInitialInvestmentAmount(rs.getLong(3));
			bean.setRiskToleranceLevel(rs.getString(4));
			bean.setInvestmentStrategy(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));
		}

		JDBCDataSource.closeConnection(conn);

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public PortfolioManagementBean findByPortfolioName(String portfolioName) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from portfolio_management where portfolioName = ?");

		pstmt.setString(1, portfolioName);

		ResultSet rs = pstmt.executeQuery();

		PortfolioManagementBean bean = null;

		while (rs.next()) {
			bean = new PortfolioManagementBean();

			bean.setId(rs.getLong(1));
			bean.setPortfolioName(rs.getString(2));
			bean.setInitialInvestmentAmount(rs.getLong(3));
			bean.setRiskToleranceLevel(rs.getString(4));
			bean.setInvestmentStrategy(rs.getString(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));

		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}

	public java.util.List list() throws Exception {
		return search(null, 0, 0);
	}

	public void delete(int int1) {
		// TODO Auto-generated method stub

	}

}
