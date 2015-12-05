/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.loginServlet.JDBC_DRIVER;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JacksonIsaac
 */
public class uploadServlet extends HttpServlet
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
		Connection conn = null;
        PreparedStatement ps = null;        
        String sql;
		
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			/*out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet loginServlet</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");*/
			
			try {
			Class.forName(JDBC_DRIVER);            
            
            System.out.println("Connecting to Database...");
            
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				
			String roll = request.getParameter("roll");
            String fn = request.getParameter("fname");
            String ln = request.getParameter("lname");
            String gn = request.getParameter("guardian");
            String add = request.getParameter("address");
            String hostel = request.getParameter("hostel");
            String room = request.getParameter("room");
            String email = request.getParameter("email");
            String cont = request.getParameter("contact");
            String rem = request.getParameter("remarks");

			/*sql = "insert into student values ('" + roll + "', '"+ fn + "', '"+ ln + 
				"', '"+ gn +"', '"+ add + "', '" + hostel +
				"', '"+ room +"', '"+ email +"', '"+ cont +"', '"+ rem +"');";
			
			System.out.println("Executing -> " + sql);
			*/
			
			sql = "INSERT INTO student(\n" +
"            roll_no, fname, lname, gaurdian, address, hostel, room, email, \n" +
"            contact, remarks)\n" +
"    VALUES (?, ?, ?, ?, ?, ?, ?, ?, \n" +
"            ?, ?);";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, roll);
			ps.setString(2, fn);
			ps.setString(3, ln);
			ps.setString(4, gn);
			ps.setString(5, add);
			ps.setString(6, hostel);
			ps.setString(7, room);
			ps.setString(8, email);
			ps.setString(9, cont);
			ps.setString(10, rem);
			
			try {
				int rs = ps.executeUpdate();
				
				sql = "insert into attendance values(?, 0, 0);";
				ps = conn.prepareStatement(sql);
				ps.setString(1, roll);
				
				try {
					rs = ps.executeUpdate();
				} catch (SQLException sqe) {
					
				}
				response.sendRedirect("http://localhost:8080/Hostel_Management_System/details.htm");
			} catch (SQLException sqe) {
				System.out.println("Error " + sqe);
			}
			
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
