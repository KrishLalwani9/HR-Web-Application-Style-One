package com.krish.lalwani.hr.servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.krish.lalwani.hr.dl.*;
public class servletsTemplate extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
PrintWriter pw=null;
try
{
pw=response.getWriter();
response.setContentType("text/html");
}catch(Exception exception)
{
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}