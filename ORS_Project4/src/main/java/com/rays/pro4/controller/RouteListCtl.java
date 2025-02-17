package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.RouteBean;
import com.rays.pro4.Model.RouteModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "RouteListCtl", urlPatterns = { "/ctl/RouteListCtl" })
public class RouteListCtl extends BaseCtl {

	protected void preload(HttpServletRequest request) {

		RouteModel model = new RouteModel();

		RouteBean bean = new RouteBean();

		try {

			List<RouteBean> list = model.list(0,0);
			request.setAttribute("proList", list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected BaseBean populateBean(HttpServletRequest request) {
		RouteBean bean = new RouteBean();

		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("Name")));
		bean.setStartDate(DataUtility.getDate(request.getParameter("StartDate")));
		bean.setAllowedSpeed(DataUtility.getInt(request.getParameter("AllowedSpeed")));
		bean.setVehicleId(DataUtility.getString(request.getParameter("VehicleId ")));

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<RouteBean> list = null;
		List<RouteBean> nextList = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		RouteBean bean = (RouteBean) populateBean(request);
		RouteModel model = new RouteModel();

		try {
			list = model.search(bean, pageNo, pageSize);
			nextList = model.search(bean, pageNo + 1, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("nextlist", nextList.size());

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<RouteBean> list;
		List<RouteBean> nextList = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));
		RouteBean bean = (RouteBean) populateBean(request);

		String[] ids = request.getParameterValues("ids");

		RouteModel model = new RouteModel();

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROUTE_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.ROUTE_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					RouteBean deletebean = new RouteBean();
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				ServletUtility.setSuccessMessage("Vehicle is Deleted Successfully", request);
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}

		try {
			list = model.search(bean, pageNo, pageSize);
			nextList = model.search(bean, pageNo + 1, pageSize);
			request.setAttribute("nextlist", nextList.size());
		} catch (Exception e) {
			ServletUtility.handleException(e, request, response);
			return;
		}

		if (list == null || list.isEmpty()) {
			if (!"OP_DELETE".equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setBean(bean, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		}

	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROUTE_LIST_VIEW;
	}
}