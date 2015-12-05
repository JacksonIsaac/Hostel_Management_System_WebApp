/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JacksonIsaac
 */
public class attnServlet extends HttpServlet
{

	static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USERNAME = "postgres";
    static final String PASSWORD = "jackson";
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet studentServlet</title>");			
			out.println("</head>");
			out.println("<body background=\"images/brown.jpg\">");
			
			Connection conn = null;
			PreparedStatement ps = null;        
			String sql;
			try {
				Class.forName(JDBC_DRIVER);            

				System.out.println("Connecting to Database...");

				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

				sql = "SELECT roll_no, fname, lname, gaurdian, address, hostel, room, email, \n" +
"       contact, remarks\n" +
"  FROM student where roll_no=?;";

				ps = conn.prepareStatement(sql);

				HttpSession session=request.getSession();  
				String rollNo = (String)session.getAttribute("roll");
				out.print(rollNo);
				
			} catch(SQLException e) {
					e.printStackTrace();
			} catch (ClassNotFoundException ex) {

			} finally {
				try {
					if(ps != null)
					ps.close();
				} catch (SQLException e) {

				}
				try {
					if(conn != null)
					conn.close();
				} catch (SQLException e) {

				}
			}
//			out.println("<h1>Servlet studentServlet at " + request.getContextPath() + 
//				request.getParameter("profile") + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo()
	{
		return "Short description";
	}// </editor-fold>

}
