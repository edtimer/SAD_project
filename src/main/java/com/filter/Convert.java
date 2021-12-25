package com.filter;

import java.io.File;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class Convert
 */
@WebServlet("/Convert")
public class Convert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Convert() {
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

//		JSONParser parser = new JSONParser();

		try {

//			String json = "";
			HttpSession session = request.getSession();
			String locationString = (String) session.getAttribute("location");
			String exampleRequest = FileUtils.readFileToString(new File(locationString + "\\Test.json"),
					StandardCharsets.UTF_8);
			System.out.println(exampleRequest);
			session.setAttribute("convertedjson", exampleRequest);
			String content = exampleRequest;
			String path = "C:\\Users\\Phaze\\eclipse-workspace\\Projects\\SDA\\src\\main\\webapp\\convertedFiles\\convertedFile.txt";
			Files.write(Paths.get(path), content.getBytes());

			session.setAttribute("path", path);

//			response.sendRedirect("ParserCont");
			System.out.print("asd");
			RequestDispatcher rd = request.getRequestDispatcher("ParserCont");
	        rd.forward(request, response);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
