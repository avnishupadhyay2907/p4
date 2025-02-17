package com.rays.pro4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.OrderBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.CustomerModel;
import com.rays.pro4.Model.OrderModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "OrderCtl", urlPatterns = { "/ctl/OrderCtl" })
public class OrderCtl extends BaseCtl {

    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("customerId")) || 
            DataUtility.getLong(request.getParameter("customerId")) <= 0) {
            request.setAttribute("customerId", PropertyReader.getValue("error.require", "Customer"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("orderDate"))) {
            request.setAttribute("orderDate", PropertyReader.getValue("error.require", "Order Date"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("totalAmount"))) {
            request.setAttribute("totalAmount", PropertyReader.getValue("error.require", "Total Amount"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected void preload(HttpServletRequest request) {
        CustomerModel model = new CustomerModel();
        try {
            List customerList = model.list();
            request.setAttribute("customerList", customerList);
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        OrderBean bean = new OrderBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setCustomerId(DataUtility.getLong(request.getParameter("customerId")));
        bean.setCustomerName(DataUtility.getString(request.getParameter("customerName")));
        bean.setOrderDate(DataUtility.getDate(request.getParameter("orderDate")));
        bean.setTotalAmount(DataUtility.getLong(request.getParameter("totalAmount")));

        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = DataUtility.getString(request.getParameter("operation"));
        OrderModel model = new OrderModel();
        OrderBean bean = (OrderBean) populateBean(request);

        try {
            if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
                if (bean.getId() > 0) {
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Order updated successfully!", request);
                } else {
                    model.add(bean);
                    ServletUtility.setSuccessMessage("Order added successfully!", request);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.forward(getView(), request, response);
            } else if (OP_RESET.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.ORDER_CTL, request, response);
                return;
            }
        } catch (DuplicateRecordException e) {
            ServletUtility.setErrorMessage("Order already exists.", request);
            ServletUtility.setBean(bean, request);
            ServletUtility.forward(getView(), request, response);
        } catch (ApplicationException e) {
            e.printStackTrace();
            ServletUtility.handleException(e, request, response);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = DataUtility.getString(request.getParameter("operation"));
        Long id = DataUtility.getLong(request.getParameter("id"));

        if (id > 0) {
            OrderModel model = new OrderModel();
            try {
                OrderBean bean = model.findByPk(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
            } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.ORDER_VIEW;
    }
}
