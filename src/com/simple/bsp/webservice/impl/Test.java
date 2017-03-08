package com.simple.bsp.webservice.impl;

import java.util.Vector;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.simple.bsp.common.util.Base64;



public class Test {
	
	
//	public static void main(String[] args) throws Exception {
//		
//		 try {
//			   String endpoint = "http://localhost:8080/sz/WS/SendMsgToPhoneService?wsdl";
//			   Service service = new Service();
//			   Call call = (Call)service.createCall();// 通过service创建call对象
//			   // 设置service所在URL
//			   call.setTargetEndpointAddress(new java.net.URL(endpoint));
//			   call.setOperationName(new QName("http://webservice.simple.com/", "sendMsgToPhone"));
//			   call.addParameter("phone",org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//		//	   call.addParameter("age",org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
//			   call.setUseSOAPAction(true);
//			   call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING); //返回参数的类型(不能用Array，否则报错)
//			   String ret =  (String) call.invoke(new Object[]{"15628860975"});
//			   System.out.println("--------"+ret);
//			  }
//		 catch (Exception e) {
//			   e.printStackTrace();
//			  }
//	}
	
	public static void main(String[] args) throws Exception {
		
		 try {
			   String endpoint = "http://localhost:8080/amms/webservice/Storage?wsdl";
			   Service service = new Service();
			   Call call = (Call)service.createCall();// 通过service创建call对象
			   // 设置service所在URL
			   call.setTargetEndpointAddress(new java.net.URL(endpoint));
			   call.setOperationName(new QName("http://webservice.bsp.simple.com/", "storageData"));
			   call.addParameter("arg0",org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			//   call.addParameter("arg1",org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			   call.setUseSOAPAction(true);
			   call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING); 
			   String ret =  (String) call.invoke(new Object[]{"编号,日期|编号,日期"});
//			   System.out.println("--------"+Base64.decode(ret));
//			   String ret2=Base64.decode(ret);
			//   JSONObject jsonObject = JSONObject.fromObject(ret2);
		//	   String t=(String) jsonObject.get("result");
			   System.out.println("结果："+ret);
			   /**
			    * 
			   JSONArray r=(JSONArray) jsonObject.get("userList");
			   for(int i=0;i<r.size();i++){
				   JSONObject json=r.getJSONObject(i);  
				   System.out.println(json.get("uPhone2"));
			   }
			 //  System.out.println(r);
			  */
			  
		 }
		 catch (Exception e) {
			   e.printStackTrace();
			  }
	}
	
	
}
