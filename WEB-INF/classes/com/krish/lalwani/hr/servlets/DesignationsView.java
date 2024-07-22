package com.krish.lalwani.hr.servlets;
import com.krish.lalwani.hr.dl.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DesignationsView extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
DesignationDAO designationDAO=new DesignationDAO();
List<DesignationDTO> designations=designationDAO.getAll();
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
System.out.println("Got the load*********************************************************88");
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main Continer starts here -->");
pw.println("<div style='width:90hw;margin:30px;height:auto;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a><div style='margin-top=30px;margin-bottom:20px;font-size:25pt'>Krish Lalwani</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<b>Designations</b><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div> <!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-top:5px;margin-bottom:5px;margin-left:105px;margin-right:5px;padding:5px;overflow:scroll;border:1px solid black'>");
pw.println("<h2>Designations</h2>");
pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='4' style='text-align:right;padding:5px'>");
pw.println("<a href='/styleone/AddDesignation.html'>Add New Designation</a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px;text-align:center'> S.NO </th>");
pw.println("<th style='width:200px'> Designation </th>");
pw.println("<th style='width:100px'> Edit </th>");
pw.println("<th style='width:100px'> Delete </th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");

int i=0;
int sno=0;
DesignationDTO designationDTO;
int code;
String title;
for(i=0;i<designations.size();i++)
{
sno++;
designationDTO=designations.get(i);
code=designationDTO.getCode();
title=designationDTO.getTitle();
pw.println("<tr>");
pw.println("<td style='text-align:right'>"+sno+"</td>");
pw.println("<td>"+title+"</td>");
pw.println("<td style='text-align:center'> <a href='/styleone/editDesignation?code="+code+"'>Edit</a> </td>");
pw.println("<td style='text-align:center'> <a href='/styleone/confirmDeleteDesignation?code="+code+"'>Delete</a> </td>");
pw.println("</tr>");
}

pw.println("</tbody>");
pw.println("</table>");
pw.println("</div> <!-- right panel ends here -->");
pw.println("</div> <!-- conten-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>");
pw.println("&copy; Krish Lalwani 2050");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- Main Continer ends here -->");
pw.println("</body>");
pw.println("</html>");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage()); //remove after testing and setup 500 (internal error code)
}
catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing and setup 500 (internal error code)
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}