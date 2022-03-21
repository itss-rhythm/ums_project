package pack;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import java.util.*;


public class fpass extends HttpServlet
{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String roll,phone;
	ServletContext ctxt;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out =res.getWriter();
		ctxt=getServletContext();
		roll=req.getParameter("roll");
		phone=req.getParameter("phone");
		int flag=0;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///"+ctxt.getInitParameter("sql_database"),"root",ctxt.getInitParameter("sql_pass"));
			ps=con.prepareStatement("select roll,phone from stdlog");
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(roll.equals(rs.getString("roll")) && phone.equals(rs.getString("phone")))
				{
					ctxt.setAttribute("froll",roll);
					ctxt.setAttribute("fphone",phone);
					flag=1;
					RequestDispatcher rd= req.getRequestDispatcher("new_pass.html");
					rd.forward(req,res);
				}
				else
				{
					continue;
				}
			}
			
			
		}catch(Exception e)
		{
			out.print(e);
			RequestDispatcher rd= req.getRequestDispatcher("index.html");
			rd.include(req,res);
		}
		
		if(flag==0)
		{
			out.print("<font color=red>USER DOES NOT EXISTS</font>");
			RequestDispatcher rd= req.getRequestDispatcher("student.html");
			rd.include(req,res);
			
		}
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
    {
            doPost(req,res);
    }
}