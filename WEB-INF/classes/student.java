package pack;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import java.util.*;


public class student extends HttpServlet
{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String roll,pass;
	ServletContext ctxt;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out =res.getWriter();
		ctxt=getServletContext();
		roll=req.getParameter("roll");
		pass=req.getParameter("pass");
		int flag=0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///"+ctxt.getInitParameter("sql_database"),"root",ctxt.getInitParameter("sql_pass"));
			ps=con.prepareStatement("select * from stdlog");
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(roll.equals(rs.getString("roll")) && pass.equals(rs.getString("password")))
				{
					flag=1;
					ctxt.setAttribute("roll",roll);
					ctxt.setAttribute("pass",pass);
					ctxt.setAttribute("phone",rs.getString("phone"));
					ctxt.setAttribute("address",rs.getString("address"));
					ctxt.setAttribute("email",rs.getString("email"));
					res.sendRedirect("display.jsp");
					//out.println("welcome : "+ roll);
					//out.println("<a href='index.html'><br>LOGOUT???</a>");
				}
				else
				{
					continue;
				}
			}
		}catch(Exception e)
		{
			out.print(e);
			RequestDispatcher rd= req.getRequestDispatcher("student.html");
			rd.include(req,res);
		}
		if(flag==0)
		{
			out.print("<font color=red>YOUR ROLL OR PASSWORD IS INCORRECT");
			RequestDispatcher rd= req.getRequestDispatcher("student.html");
			rd.include(req,res);	
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
    {
            doPost(req,res);
    }
}