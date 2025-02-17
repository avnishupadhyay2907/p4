package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.RouteBean;
import com.rays.pro4.Model.RouteModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "RouteCtl", urlPatterns = { "/ctl/RouteCtl" })

public class RouteCtl extends BaseCtl {



	protected boolean   validate(HttpServletRequest request) {
		System.out.println("uctl Validate");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("Name"))) {
			request.setAttribute("Name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("StartDate"))) {
			request.setAttribute("StartDate", PropertyReader.getValue("error.require", "StartDate"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("StartDate"))) {
			request.setAttribute("StartDate", PropertyReader.getValue("error.date", "Start Date "));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("AllowedSpeed"))) {
			request.setAttribute("AllowedSpeed", PropertyReader.getValue("error.require", "AllowedSpeed"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("VehicleId"))) {
			request.setAttribute("VehicleId", PropertyReader.getValue("error.require", "VehicleId"));
			pass = false;
		
		}

		return pass;

	}

	protected BaseBean populateBean(HttpServletRequest request) {

		RouteBean bean = new RouteBean();

		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("Name")));
		bean.setStartDate(DataUtility.getDate(request.getParameter("StartDate")));
		bean.setAllowedSpeed(DataUtility.getInt(request.getParameter("insuranceAmount")));
		bean.setVehicleId  (DataUtility.getString(request.getParameter(" VehicleId  ")));

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		RouteModel model = new RouteModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(" Id >= " + id);

		if (id != 0 && id > 0) {

			System.out.println("in id > 0  condition " + id);
		RouteBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uctl Do Post");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(">>>><<<<>><<><<><<><>**********" + id + op);

		RouteModel model = new RouteModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			RouteBean bean = (RouteBean) populateBean(request);

			if (id > 0) {

				try {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Vehicle is successfully Updated", request);
				} catch (Exception e) {
					System.out.println("Vehicle not update");
					e.printStackTrace();
				}

			} else {

				try {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Vehicle is successfully Added", request);
					bean.setId(1);
				} catch (Exception e) {
					System.out.println("Vehicle not added");
					e.printStackTrace();
				}

			}

		}
		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {

		return ORSView.ROUTE_VIEW;
	}
	
}
