package com.ntt.mercury;

import com.ntt.seo2.util.ftp.FTPUtil;
import com.ntt.seo2.util.xml.XMLUtil;

public class XMLTest {

	public static void xmlTest(){
		try{
		FTPUtil.setUp("165.136.25.230", "enbhdAsftp", "12345.dell2015", 22);
		FTPUtil.put("C:/SeleniumProjects/cca-portal/testdata/member.xml", "/apps/enb/enbhdAsftp/download/tradingPartnerRoot/11821/X12in");
		FTPUtil.get("/apps/enb/enbhdAsftp/download/tradingPartnerRoot/11821/X12in/member_100.xml","C:/SeleniumProjects/cca-portal/testdata/");
		
		
		String subscriberId = XMLUtil.getValueForTag("C:/SeleniumProjects/cca-portal/testdata", "member.xml", "subscriber_id");
		System.out.println(subscriberId);
		XMLUtil.replaceTagValue("C:/SeleniumProjects/cca-portal/testdata/member.xml", "subscriber_id", "S100");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		xmlTest();
	}

}
