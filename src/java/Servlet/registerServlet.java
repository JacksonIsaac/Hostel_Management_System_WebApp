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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JacksonIsaac
 */
public class registerServlet extends HttpServlet
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
			try {
				Class.forName(JDBC_DRIVER);            

				System.out.println("Connecting to Database...");

				conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

				sql = "INSERT INTO login(\n" +
	"            username, password)\n" +
	"    VALUES (?, ?);";

	//			System.out.println(request.getParameter("username"));
	//			System.out.println(request.getParameter("password"));

				ps = conn.prepareStatement(sql);
				ps.setString(1, request.getParameter("username"));
				ps.setString(2, request.getParameter("password"));

				try {
					int rs = ps.executeUpdate();
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Registration Successful');");
					out.println("location='index.html';");
					out.println("</script>");
//					response.sendRedirect("http://localhost:8080/Hostel_Management_System/");
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
