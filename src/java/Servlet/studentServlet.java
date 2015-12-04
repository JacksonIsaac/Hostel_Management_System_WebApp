/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import static Servlet.registerServlet.JDBC_DRIVER;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JacksonIsaac
 */
public class studentServlet extends HttpServlet
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
			out.println("<style>\n" +
				"    table, th, td {\n" +
				"        border: 1px solid black;\n" +
				"        border-collapse: collapse;\n" +
				"    }\n" +
				"    th, td {\n" +
				"        padding: 10px;\n" +
				"    }\n" +
				"    </style>");
			out.println("<title>Servlet studentServlet</title>");			
			out.println("</head>");
			out.println("<body>");
			
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
				ps.setString(1, request.getParameter("profile"));

				ResultSet rs = ps.executeQuery();

				if(rs.next()) {
					System.out.println("Valid user");
					String roll = rs.getString("roll_no");
					String fn = rs.getString("fname");
					String ln = rs.getString("lname");
					String gn = rs.getString("gaurdian");
					String add = rs.getString("address");
					String hostel = rs.getString("hostel");
					String room = rs.getString("room");
					String email = rs.getString("email");
					String cont = rs.getString("contact");
					String rem = rs.getString("remarks");
					
					out.print("<table><tr>\n" +
						"  <th colspan=\"2\">Student Details</th>\n" +
						"  <tr><td>Roll No:</td><td>" 
						+ roll + "</td></tr><tr><td>");
					out.print("First Name:</td><td>" + fn + "</td></tr><tr><td>");
					out.print("Last Name:</td><td>" + ln + "</td></tr><tr><td>");
					out.print("Guardian Name:</td><td>" + gn + "</td></tr><tr><td>");
					out.print("Address:</td><td>" + add + "</td></tr><tr><td>");
					out.print("Hostel:</td><td>" + hostel + "</td></tr><tr><td>");
					out.print("Room:</td><td>" + room + "</td></tr><tr><td>");
					out.print("Email:</td><td>" + email + "</td></tr><tr><td>");
					out.print("Contact:</td><td>" + cont + "</td></tr><tr><td>");
					out.print("Remarks:</td><td>" + rem + "</td></tr></table>");
	//				HttpSession session=request.getSession();  
	//				session.setAttribute("name", request.getParameter("username"));  
				} else {
					out.print("Invalid Roll No. Please Enter a correct Roll No.<br>");
					out.print("The following are Roll No. in the DB:");
					
					sql = "SELECT roll_no FROM student";

					ps = conn.prepareStatement(sql);

					rs = ps.executeQuery();
					
					while(rs.next()) {
						String roll = rs.getString("roll_no");
						out.print("Roll No: " 
						+ roll + "<br>");
					}
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
