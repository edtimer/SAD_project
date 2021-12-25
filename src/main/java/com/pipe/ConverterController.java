package com.pipe;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Converter
 */
@WebServlet("/Converter")
public class ConverterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConverterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//		String location= "C:\\Users\\Phaze\\eclipse-workspace\\Projects\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SDA_teamc_project\\SDA_teamc_project\\uploads\\Test.json";
//		HttpSession session = request.getSession();
//		session.setAttribute("location", location);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("Convert");
//		dispatcher.forward(request, response);
		HttpSession session = request.getSession();
		String location = (String) session.getAttribute("location");
		System.out.println(location);
		session.setAttribute("location", location);
		response.sendRedirect("Convert");
		
		System.out.print("sending to convert");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
