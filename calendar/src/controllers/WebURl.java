package controllers;

import javax.servlet.http.HttpServletRequest;

public class WebURl {
	
	public String getURLWithContextPath(HttpServletRequest request) {
		   return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		}

}
