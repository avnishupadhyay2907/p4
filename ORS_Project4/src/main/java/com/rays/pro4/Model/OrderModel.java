package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.CollegeBean;
import com.rays.pro4.Bean.CustomerBean;
import com.rays.pro4.Bean.OrderBean;
import com.rays.pro4.Util.JDBCDataSource;

public class OrderModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from orders");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(OrderBean bean) throws Exception {
	    // Establishing a connection
	    try (Connection conn = JDBCDataSource.getConnection();
	         PreparedStatement pstmt = conn.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?)")) {

	        // Fetching customer details
	        CustomerModel customerModel = new CustomerModel();
	        CustomerBean customerBean = customerModel.findByPk(bean.getCustomerId());

	        if (customerBean == null) {
	            throw new Exception("Customer with ID " + bean.getCustomerId() + " not found.");
	        }

	        // Setting customer name
	        bean.setCustomerName(customerBean.getFirstName() + " " + customerBean.getLastName());

	        // Getting the next primary key
	        int pk = nextPk();

	        // Setting parameters for the prepared statement
	        pstmt.setLong(1, pk);
	        pstmt.setLong(2, bean.getCustomerId());
	        pstmt.setString(3, bean.getCustomerName());
	        pstmt.setDate(4, new java.sql.Date(bean.getOrderDate().getTime()));
	        pstmt.setLong(5, bean.getTotalAmount());
	        pstmt.setString(6, bean.getCreatedBy());
	        pstmt.setString(7, bean.getModifiedBy());
	        pstmt.setTimestamp(8, bean.getCreatedDatetime());
	        pstmt.setTimestamp(9, bean.getModifiedDatetime());

	        // Executing the update
	        int i = pstmt.executeUpdate();

	        if (i > 0) {
	            System.out.println("Data inserted successfully! Rows affected: " + i);
	        } else {
	            throw new Exception("Data insertion failed.");
	        }
	    } catch (Exception e) {
	        // Handle exceptions
	        e.printStackTrace();
	        throw e; // Rethrow the exception for higher-level handling
	    }
	}


	public void update(OrderBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"update orders set customer_id = ?, customer_name = ?, order_date = ?, total_amount = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setLong(1, bean.getCustomerId());
		pstmt.setString(2, bean.getCustomerName());
		pstmt.setDate(3, new java.sql.Date(bean.getOrderDate().getTime()));
		pstmt.setLong(4, bean.getTotalAmount());
		pstmt.setString(5, bean.getCreatedBy());
		pstmt.setString(6, bean.getModifiedBy());
		pstmt.setTimestamp(7, bean.getCreatedDatetime());
		pstmt.setTimestamp(8, bean.getModifiedDatetime());
		pstmt.setLong(9, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Updated Successfully..." + i);

	}

	public void delete(OrderBean bean) throws Exception {
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("delete from orders where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("Data Deleted Successfully..." + i);
	}

	public List search(OrderBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();
		StringBuffer sql = new StringBuffer("select * from orders where 1=1");
				

		if (bean != null) {
			if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
				sql.append(" and customer_name like '" + bean.getCustomerName() + "%'");
			}
			if (bean.getTotalAmount() != 0 && bean.getTotalAmount() > 0) {
				sql.append(" and total_amount like '" + bean.getTotalAmount() + "%'");
			}
			if (bean.getOrderDate() != null && ((CharSequence) bean.getOrderDate()).length() > 0) {
				sql.append(" and order_date like '" + bean.getOrderDate() + "%'");
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

		List list = new ArrayList();

		while (rs.next()) {

			bean = new OrderBean();

			bean.setId(rs.getLong(1));
			bean.setCustomerId(rs.getLong(2));
			bean.setCustomerName(rs.getString(3));
			bean.setOrderDate(rs.getDate(4));
			bean.setTotalAmount(rs.getLong(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));
			list.add(bean);
		}
		return list;
	}

	public OrderBean findByPk(long id) throws Exception {
		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from orders where id = ?");
		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		OrderBean bean = null; // Initialize the bean to null

		if (rs.next()) {
			bean = new OrderBean(); // Create a new instance of OrderBean when data is found

			bean.setId(rs.getLong(1));
			bean.setCustomerId(rs.getLong(2));
			bean.setCustomerName(rs.getString(4));
			bean.setOrderDate(rs.getDate(3));
			bean.setTotalAmount(rs.getLong(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));
		}

		JDBCDataSource.closeConnection(conn);

		return bean; // Returns null if no record is found, otherwise returns the bean
	}

	public OrderBean findByTotalAmount(String totalAmount) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from orders where total_amount = ?");

		pstmt.setString(1, totalAmount);

		ResultSet rs = pstmt.executeQuery();

		OrderBean bean = null;

		while (rs.next()) {
			bean = new OrderBean();

			bean.setId(rs.getLong(1));
			bean.setCustomerId(rs.getLong(2));
			bean.setCustomerName(rs.getString(4));
			bean.setOrderDate(rs.getDate(3));
			bean.setTotalAmount(rs.getLong(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

}
