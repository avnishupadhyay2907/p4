package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.RouteBean;



public class RouteModel {

	public Integer nextPK() throws Exception {

		int pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from Routes");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}

		rs.close();

		return pk + 1;
	}

	public long add(RouteBean bean) throws Exception {

		int pk = 0;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");

		pk = nextPK();

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("insert into Routes values(?,?,?,?,?)");

		pstmt.setInt(1, pk);
		pstmt.setString(2, bean.getName());
		pstmt.setDate(3, (Date) (bean.getStartDate()));

		/// pstmt.setDate(3, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setDouble(4, bean.getAllowedSpeed());
		pstmt.setString(5, bean.getVehicleId());

		int i = pstmt.executeUpdate();
		System.out.println("Product Add Successfully " + i);
		conn.commit();
		pstmt.close();

		return pk;
	}

	public void delete(RouteBean bean) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");

		conn.setAutoCommit(false);

		PreparedStatement pstmt = conn.prepareStatement("delete from Routes where id = ?");

		pstmt.setLong(1, bean.getId());

		int i = pstmt.executeUpdate();
		System.out.println("Product delete successfully " + i);
		conn.commit();

		pstmt.close();
	}

	public void update(RouteBean bean) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");

		conn.setAutoCommit(false); // Begin transaction

		PreparedStatement pstmt = conn.prepareStatement(
				"update Routes set Name = ?, StartDate = ?, AllowedSpeed = ?, VehicleId = ? where id = ?");

		pstmt.setString(2, bean.getName());
		pstmt.setDate(3, (Date) (bean.getStartDate()));

		/// pstmt.setDate(3, new java.sql.Date(bean.getPurchaseDate().getTime()));
		pstmt.setDouble(4, bean.getAllowedSpeed());
		pstmt.setString(5, bean.getVehicleId());

		pstmt.setLong(5, bean.getId());

		int i = pstmt.executeUpdate();

		System.out.println("product update successfully " + i);

		conn.commit();
		pstmt.close();

	}

	public RouteBean findByPK(long pk) throws Exception {

		String sql = "select * from Routes where id = ?";
		RouteBean bean = null;

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setLong(1, pk);

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new RouteBean();
			pstmt.setInt(1, (int) pk);
			pstmt.setString(2, bean.getName());
			pstmt.setDate(3, (Date) (bean.getStartDate()));

			/// pstmt.setDate(3, new java.sql.Date(bean.getPurchaseDate().getTime()));
			pstmt.setDouble(4, bean.getAllowedSpeed());
			pstmt.setString(5, bean.getVehicleId());

		}

		rs.close();

		return bean;
	}

	public List<RouteBean> search(RouteBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from routes where 1=1");
		if (bean != null) {

			if (bean.getName() != null && bean.getName().length() > 0) {

				sql.append("And Name like '" + bean.getName() + "%'");
			}

			if (bean.getStartDate() != null && bean.getStartDate().getTime() > 0) {
				Date d = new Date(bean.getStartDate().getTime()); // Make sure to get the time correctly
				sql.append(" And StartDate ='").append(new java.sql.Date(d.getTime())).append("'");
			}

			if (bean.getAllowedSpeed() > 0) {

				sql.append("And AllowedSpeed = '" + bean.getAllowedSpeed() + "%'");

			}
			if (bean.getVehicleId() != null && bean.getVehicleId().length() > 0) {
				sql.append("And VehicleId = '" + bean.getVehicleId() + "%'");
			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);

		}

		System.out.println("sql query search >>= " + sql.toString());
		List<RouteBean> list = new ArrayList<RouteBean>();

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			bean = new RouteBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setStartDate(rs.getDate(3));
			bean.setAllowedSpeed(rs.getInt(4));
			bean.setVehicleId(rs.getString(5));

			list.add(bean);

		}
		rs.close();

		return list;

	}

	public List<RouteBean> list(int i, int j) throws Exception {

		ArrayList<RouteBean> list = new ArrayList<RouteBean>();

		StringBuffer sql = new StringBuffer("select * from Routes");

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			RouteBean bean = new RouteBean();
			bean = new RouteBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setStartDate(rs.getDate(3));
			bean.setAllowedSpeed(rs.getInt(4));
			bean.setVehicleId(rs.getString(5));

			list.add(bean);

		}

		rs.close();

		return list;
	}

	public RouteBean findByPk(long pk) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Route", "root", "root");
		PreparedStatement ps = conn.prepareStatement("select * from Routes where id = ?");

		ps.setLong(1, pk);
		ResultSet rs = ps.executeQuery();

		RouteBean bean = null;
		if (rs.next()) {

			bean = new RouteBean();
			bean.setId(rs.getInt(1));
			bean.setName(rs.getString(2));
			bean.setStartDate(rs.getDate(3));
			bean.setAllowedSpeed(rs.getInt(4));
			bean.setVehicleId(rs.getString(5));
		}
		rs.close();
		return bean;

	}

}