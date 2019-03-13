package com.wsw.protocol.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6635760542625569705L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		new HttpServerHandler().handler(req, resp);
	}
}
