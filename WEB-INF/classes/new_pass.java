package pack;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import java.util.*;


public class new_pass extends HttpServlet
{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	String roll,npass,phone,address,email,cpass;
	ServletContext ctxt;
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out =res.getWriter();
		ctxt=getServletContext();
		roll=(String)ctxt.getAttribute("froll");
		npass=req.getParameter("npass");
		cpass=req.getParameter("cpass");
		phone=(String)ctxt.getAttribute("fphone");
		
		
		if(npass.equals(cpass)){}
		else
		{
			out.print("<font color=red>PASSWORDS DO NOT MATCH</font>");
			RequestDispatcher rd= req.getRequestDispatcher("student.html");
			rd.include(req,res);
		}
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql:///"+ctxt.getInitParameter("sql_database"),"root",ctxt.getInitParameter("sql_pass"));
			ps=con.prepareStatement("select * from stdlog");
			rs=ps.executeQuery();
			while(rs.next())
			{
				if(roll.equals(rs.getString("roll")))
				{
					address=rs.getString("address");
					email=rs.getString("email");
				}
				else
				{
					continue;
				}
			}
			
			ps=con.prepareStatement("delete from stdlog where roll="+roll);
			ps.executeUpdate();
			
			
			ps=con.prepareStatement("insert into stdlog values (?,?,?,?,?)");
			ps.setString(1,roll);
			ps.setString(2,npass);
			ps.setString(3,phone);
			ps.setString(4,address);
			ps.setString(5,email);
			ps.executeUpdate();
			out.print("<font color=red>PASSWORD SUCCESSFUY CHANGED</font>");
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