package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rays.pro4.Bean.RouteBean;
import com.rays.pro4.Bean.RouteBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.RouteModel;

/**
 * User Model Test classes.
 * 
 * @author Avnish Upadhyay
 *
 */

public class TestRouteModel {

	public static void main(String[] args) throws Exception {

//		 testAdd();
//		 testDelete();
//		 testFindByPk();
//		 testUpdate();
//		testSearch();
		getList();

	}

	private static void getList() throws Exception {
		try {
			RouteBean bean = new RouteBean();
			RouteModel model = new RouteModel();
			List list = new ArrayList();
			list = model.list(0, 0);
			if (list.size() < 0) {
				System.out.println("Test list fail ");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RouteBean) it.next();
				System.out.println(bean.getId());
				System.out.print(bean.getName());
				System.out.print(bean.getStartDate());
				System.out.print(bean.getAllowedSpeed());
				System.out.print(bean.getVehicleId());

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	private static void testSearch() throws Exception {
		try {
			RouteBean bean = new RouteBean();
			RouteModel model = new RouteModel();
			List list = new ArrayList();
			// bean.setFirstName("Roshani");
			// bean.setLastName("Bandhiye");
			// bean.setLogin("banti@gmail.com");
			// bean.setId(8L);
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RouteBean) it.next();
				System.out.println(bean.getId());
				System.out.print(bean.getName());
				System.out.print(bean.getStartDate());
				System.out.print(bean.getAllowedSpeed());
				System.out.print(bean.getVehicleId());

			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testUpdate() throws Exception {
		try {
			RouteBean bean = new RouteBean();
			RouteModel model = new RouteModel();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
			bean = model.findByPK(1L);
			bean.setName(" Keshav ");
			bean.setStartDate(sdf.parse("2020-08-17 "));
			bean.setAllowedSpeed(110);
			bean.setVehicleId("MP 09 KL 6622");

			model.update(bean);

			System.out.println("test update successfully");

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	private static void testFindByPk() throws Exception {
		try {
			RouteBean bean = new RouteBean();
			long pk = 2L;
			RouteModel model = new RouteModel();
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test find by pk fail");
			}
			System.out.println(bean.getId());
			System.out.print(bean.getName());
			System.out.print(bean.getStartDate());
			System.out.print(bean.getAllowedSpeed());
			System.out.print(bean.getVehicleId());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() throws Exception {
		RouteBean bean = new RouteBean();
		bean.setId(1);
		;
		RouteModel model = new RouteModel();
		model.delete(bean);

	}

	public static void testAdd() throws Exception {
		try {
			RouteBean bean = new RouteBean();
			RouteModel model = new RouteModel();

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			bean.setId(1);
			bean.setName("Janesh");
			bean.setStartDate(sdf.parse("27-07-2024"));
			bean.setAllowedSpeed(120);
			bean.setVehicleId("MP 09 OP 2727");

			long pk = model.add(bean);
			RouteBean addedbean = model.findByPK(pk); // Route
			System.out.println("Test add succussfull");

			if (addedbean == null) {
				System.out.println("Test add fail");
			}

			System.out.println("record insert");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
