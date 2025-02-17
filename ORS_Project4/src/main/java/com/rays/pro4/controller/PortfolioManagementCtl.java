package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.PortfolioManagementBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.PortfolioManagementModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "PortfolioManagementCtl", urlPatterns = { "/ctl/PortfolioManagementCtl" })

public class PortfolioManagementCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("portfolioName"))) {
			request.setAttribute("portfolioName", PropertyReader.getValue("error.require", "Portfolio Name "));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("initialInvestmentAmount"))) {
			request.setAttribute("initialInvestmentAmount",
					PropertyReader.getValue("error.require", "RiskToleranceLevel"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("riskToleranceLevel"))) {
			request.setAttribute("riskToleranceLevel", PropertyReader.getValue("error.require", "Purchase Date"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("investmentStrategy"))) {
			request.setAttribute("investmentStrategy", PropertyReader.getValue("error.require", "InvestmentStrategy"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		PortfolioManagementBean bean = new PortfolioManagementBean();

		System.out.println("requesttttidddddd=>" + request.getParameter("id"));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		System.out.println("beannnnnnnnniddddddd=>" + bean.getId());

		System.out.println("requesttttDessssss=>" + request.getParameter("portfolioName"));
		bean.setPortfolioName(DataUtility.getString(request.getParameter("portfolioName")));
		System.out.println("beannnnnnnnnnDesssss=>" + bean.getPortfolioName());

		System.out.println("reqestrtttt expppppppppp=>" + request.getParameter("initialInvestmentAmount"));
		bean.setInitialInvestmentAmount(DataUtility.getLong(request.getParameter("initialInvestmentAmount")));
		System.out.println("beannnnnnnnnnExpppppp" + bean.getInitialInvestmentAmount());

		System.out.println("date simple => " + request.getParameter("riskToleranceLevel"));
		bean.setRiskToleranceLevel(DataUtility.getString(request.getParameter("riskToleranceLevel")));
		System.out.println("date => " + bean.getRiskToleranceLevel());

		System.out.println("reqqqqqqqqqqqqComnnnnnnnnnnn=>" + request.getParameter("OrderType"));
		bean.setInvestmentStrategy(DataUtility.getString(request.getParameter("investmentStrategy")));
		System.out.println("beannnnnnnnnConnnnnnn" + bean.getInvestmentStrategy());

		populateDTO(bean, request);
		return bean;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("in do post method");
		String op = DataUtility.getString(request.getParameter("operation"));

		PortfolioManagementModel model = new PortfolioManagementModel();

		PortfolioManagementBean bean = (PortfolioManagementBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			try {

				if (bean.getId() != 0 && bean.getId() > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Portfolio Managment updated Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				} else {
					model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Portfolio Managment Added Successfully..!!", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Portfolio Name already exist", request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PORTFOLIO_MANAGEMENT_CTL, request, response);
			return;
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do get method");
		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			PortfolioManagementModel model = new PortfolioManagementModel();

			try {
				PortfolioManagementBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PORTFOLIO_MANAGEMENT_VIEW;
	}

}
