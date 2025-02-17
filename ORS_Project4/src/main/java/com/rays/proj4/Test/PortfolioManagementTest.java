package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rays.pro4.Bean.PortfolioManagementBean;
import com.rays.pro4.Bean.StudentBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.PortfolioManagementModel;

public class PortfolioManagementTest {

	public static void main(String[] args) throws Exception {
		
		testAdd();
		
		
	}

	private static void testAdd() throws Exception {
		
		try{
			PortfolioManagementBean bean=new PortfolioManagementBean();
			PortfolioManagementModel model = new PortfolioManagementModel();
			//bean.setId(3L);
			bean.setPortfolioName("Paras Defence");
			bean.setInitialInvestmentAmount((long) 500000);
			bean.setRiskToleranceLevel("Medium");
			bean.setInvestmentStrategy("High");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			
			PortfolioManagementBean addbean = model.findByPk(pk);
			if(addbean==null){
				System.out.println("Test add fail");
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}catch(DuplicateRecordException e){
			e.printStackTrace();
		}
	}		
	
	
}
