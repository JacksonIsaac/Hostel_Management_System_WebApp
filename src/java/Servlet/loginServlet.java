package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JacksonIsaac
 */
public class loginServlet extends HttpServlet
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
			
			sql = "SELECT * FROM LOGIN WHERE username = ? AND password = ?;";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, request.getParameter("username"));
            ps.setString(2, request.getParameter("password"));

			ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                System.out.println("Valid user");
				Cookie ck=new Cookie("username", request.getParameter("username"));//creating cookie object  
				ck.setMaxAge(60*60); // TTL = One Hour
				System.out.println(ck.getValue());
				response.addCookie(ck);//adding cookie in the response 
//				HttpSession session=request.getSession();  
//				session.setAttribute("name", request.getParameter("username"));  
                response.sendRedirect("http://localhost:8080/Hostel_Management_System/selection.jsp");
            } else {
				/*sql = "INSERT INTO login(\n" +
				"username, password)\n" +
				"VALUES (?, ?);";

				ps = conn.prepareStatement(sql);
				ps.setString(1, request.getParameter("username"));
				ps.setString(2, request.getParameter("password"));
				
				try {
					int _rs = ps.executeUpdate();
				} catch (SQLException sqe) {
					System.out.println("Error " + sqe);
				}*/
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Incorrect username/password.');");
				out.println("location='index.html';");
				out.println("</script>");
				//response.sendRedirect("http://localhost:8080/Hostel_Management_System/");
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
