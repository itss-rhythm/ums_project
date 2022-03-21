package pack;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import java.util.*;


public class new_usr extends HttpServlet
{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String roll,pass,phone,address,email;
	ServletContext ctxt;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out =res.getWriter();
		ctxt=getServletContext();
		roll=req.getParameter("roll");
		pass=req.getParameter("pass");
		phone=req.getParameter("phone");
		address=req.getParameter("address");
		email=req.getParameter("email");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///"+ctxt.getInitParameter("sql_database"),"root",ctxt.getInitParameter("sql_pass"));
			ps=con.prepareStatement("select roll from stdlog");
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(roll.equals(rs.getString("roll")))
				{
					out.print("user already exists");
					RequestDispatcher rd= req.getRequestDispatcher("student.html");
			        rd.include(req,res);
				}
				else
				{
					continue;
				}
			}
			
			
			ps=con.prepareStatement("insert into stdlog values (?,?,?,?,?)");
			ps.setString(1,roll);
			ps.setString(2,pass);
			ps.setString(3,phone);
			ps.setString(4,address);
			ps.setString(5,email);
			ps.executeUpdate();
			out.print("<font color=red>USER SUCCESSFUY REGISTERED</font>");
			RequestDispatcher rd= req.getRequestDispatcher("student.html");
			rd.include(req,res);
			
			
		}catch(Exception e)
		{
			out.print(e);
			RequestDispatcher rd= req.getRequestDispatcher("student.html");
			rd.include(req,res);
		}
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
    {
            doPost(req,res);
    }
}