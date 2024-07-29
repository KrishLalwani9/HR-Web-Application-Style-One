package com.krish.lalwani.hr.servlets;
import com.krish.lalwani.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;
public class ConfirmDeleteEmployee extends HttpServlet
{
PrintWriter pw=null;
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
String employeeId="";
try
{
employeeId=request.getParameter("employeeId");
EmployeeDAO employeeDAO=new EmployeeDAO();
try
{
EmployeeDTO employeeDTO=employeeDAO.getByEmployeeId(employeeId);
pw=response.getWriter();
response.setContentType("text/html");
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function cancelDeletion()");
pw.println("{");
pw.println("document.getElementById('cancelDeletionForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main Continer starts here -->");
pw.println("<div style='width:90hw;margin:40px;height:auto;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left'></a><div style='margin-top=30px;margin-bottom:20px;font-size:25pt'>Krish Lalwani</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div> <!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-top:5px;margin-bottom:5px;margin-left:105px;margin-right:5px;padding:5px;border:1px solid black'>");
pw.println("<h2>Employee (Delete Module)</h2>");
pw.println("<form method='post' action='/styleone/deleteEmployee'>");

pw.println("Name : "); 
pw.println("<b>"+employeeDTO.getName()+"</b><br>");

pw.println("Designation : "); 
pw.println("<b>"+employeeDTO.getDesignation()+"</b><br>");

SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
String stringDateOfBirth=sdf.format(employeeDTO.getDateOfBirth());
pw.println("Date of birth : "); 
pw.println("<b>"+stringDateOfBirth+"</b><br>");

pw.println("Gender : "); 
if(employeeDTO.getGender().equals("M")) pw.println("<b>Male</b><br>");
else pw.println("<b>Female</b><br>");

pw.println("Nationality : "); 
if(employeeDTO.getIsIndian()) pw.println("<b>Indian</b><br>");
else pw.println("<b>Not an Indian</b><br>");

pw.println("Basic Salary : "); 
pw.println("<b>"+employeeDTO.getBasicSalary().toPlainString()+"</b><br>");

pw.println("PAN Number : "); 
pw.println("<b>"+employeeDTO.getPANNumber()+"</b><br>");

pw.println("Aadhar Card Number : "); 
pw.println("<b>"+employeeDTO.getAadharCardNumber()+"</b><br><br>");

pw.println("Are you sure, you want to delete ?<br><br>");
pw.println("<input type='hidden' id='employeeId' name='employeeId' value='"+employeeId+"'>");
pw.println("<button type='submit'>Yes</button>");
pw.println("<button type='button' onclick='cancelDeletion()'>No</button>");
pw.println("</form>");
pw.println("</div> <!-- right panel ends here -->");
pw.println("</div> <!-- conten-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>");
pw.println("&copy; Krish Lalwani 2050");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- Main Continer ends here -->");
pw.println("<form id='cancelDeletionForm' action='/styleone/employeesView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
sendBackView(response);
return;
}
}catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
private void sendBackView(HttpServletResponse response)
{
try
{
List<EmployeeDTO> employees=new EmployeeDAO().getAll();

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function Employee() //this is class ");
pw.println("{");
pw.println("this.employeeId=\"\"; //this is public properties ");
pw.println("this.name=\"\";");
pw.println("this.designation=\"\";");
pw.println("this.dateOfBirth=\"\";");
pw.println("this.gender=\"\";");
pw.println("this.isIndian=true;");
pw.println("this.basicSalary=\"\";");
pw.println("this.panNumber=\"\";");
pw.println("this.aadharCardNumber=\"\";");
pw.println("}");
pw.println("var employees=[];");

int i=0;
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeDTO employee : employees)
{
pw.println("employee=new Employee();");
pw.println("employee.employeeId=\""+employee.getEmployeeId()+"\";");
pw.println("employee.name=\""+employee.getName()+"\";");
pw.println("employee.designation=\""+employee.getDesignation()+"\";");
pw.println("employee.dateOfBirth=\""+simpleDateFormat.format(employee.getDateOfBirth())+"\";");
pw.println("employee.gender=\""+employee.getGender()+"\";");
pw.println("employee.isIndian="+employee.getIsIndian()+";");
pw.println("employee.basicSalary=\""+(employee.getBasicSalary()).toPlainString()+"\"");
pw.println("employee.panNumber=\""+employee.getPANNumber()+"\";");
pw.println("employee.aadharCardNumber=\""+employee.getAadharCardNumber()+"\";");
pw.println("employees["+i+"]=employee;");
i++;
}

pw.println("var selectedRow=null;");
pw.println("var i=0;");
pw.println("function selectEmployee(row,employeeId)");
pw.println("{");
pw.println("if(row==selectedRow) return;");
pw.println("if(selectedRow!=null)");
pw.println("{");
pw.println("selectedRow.style.background='white'");
pw.println("selectedRow.style.color='black';");
pw.println("}");
pw.println("row.style.background='#7C7B7B';");
pw.println("row.style.color='white';");
pw.println("selectedRow=row;");
pw.println("for(i=0;i<employees.length;i++)");
pw.println("{");
pw.println("if(employees[i].employeeId==employeeId)");
pw.println("{");
pw.println("document.getElementById('detailsPanel_employeeId').innerHTML=employees[i].employeeId;");
pw.println("document.getElementById('detailsPanel_name').innerHTML=employees[i].name;");
pw.println("document.getElementById('detailsPanel_designation').innerHTML=employees[i].designation;");
pw.println("document.getElementById('detailsPanel_dateOfBirth').innerHTML=employees[i].dateOfBirth;");
pw.println("document.getElementById('detailsPanel_gender').innerHTML=employees[i].gender;");
pw.println("document.getElementById('detailsPanel_isIndian').innerHTML=((employees[i].isIndian) ? 'Yes' : 'No');");
pw.println("document.getElementById('detailsPanel_basicSalary').innerHTML=employees[i].basicSalary;");
pw.println("document.getElementById('detailsPanel_panNumber').innerHTML=employees[i].panNumber;");
pw.println("document.getElementById('detailsPanel_aadharCardNumber').innerHTML=employees[i].aadharCardNumber;");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("</script>");
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
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div> <!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-top:5px;margin-bottom:5px;margin-left:105px;margin-right:5px;padding:5px;border:1px solid black'>");
pw.println("<!-- table panel starts here -->");
pw.println("<div style='height:40vh;margin:5px;padding:5px;overflow:scroll;border:1px solid black'>");
pw.println("<h3>Employees</h3>");
pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='6' style='text-align:right;padding:5px'>");
pw.println("<a href='/styleone/getEmployeeAddForm'>Add Employee</a>");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px;text-align:center'>S.No.</th>");
pw.println("<th style='width:200px'>Id.</th>");
pw.println("<th style='width:200px'>Name</th>");
pw.println("<th style='width:200px'>Designation</th>");
pw.println("<th style='width:100px'> Edit </th>");
pw.println("<th style='width:100px'> Delete </th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");
i=1;
for(EmployeeDTO employee : employees)
{
pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this,\""+employee.getEmployeeId()+"\")'>");
pw.println("<td style='text-align:right'>"+i+"</td>");
pw.println("<td>"+employee.getEmployeeId()+"</td>");
pw.println("<td>"+employee.getName()+"</td>");
pw.println("<td>"+employee.getDesignation()+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editEmployee?employeeId="+employee.getEmployeeId()+"'>Edit</a> </td>");
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteEmployee?employeeId="+employee.getEmployeeId()+"'>Delete</a> </td>");
pw.println("</tr>");
i++;
}

pw.println("</tbody>");
pw.println("</table>");
pw.println("</div><!-- table panel ends here -->");
pw.println("<!-- details panel starts here -->");
pw.println("<div style='height:19vh;marigin:5px;padding:5px;border:1px solid black'>");
pw.println("<lable style='background:gray;color:white;padding-left:5px;padding-right:5px'>Details</lable>");
pw.println("<table border='0' width='100%'>");
pw.println("<tr>");
pw.println("<td>EmployeeId : <span id='detailsPanel_employeeId' style='margin-right:30px'></span></td>");
pw.println("<td>Name : <span id='detailsPanel_name' style='margin-right:30px'></span></td>");
pw.println("<td>Desigantion : <span id='detailsPanel_designation' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth : <span id='detailsPanel_dateOfBirth' style='margin-right:30px'></span></td>");
pw.println("<td>Gender : <span id='detailsPanel_gender' style='margin-right:30px'></span></td>");
pw.println("<td>IsIndian : <span id='detailsPanel_isIndian' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary : <span id='detailsPanel_basicSalary' style='margin-right:30px'></span></td>");
pw.println("<td>PAN Number : <span id='detailsPanel_panNumber' style='margin-right:30px'></span></td>");
pw.println("<td>Aadhar Card Number : <span id='detailsPanel_aadharCardNumber' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div><!-- details panel ends here -->");
pw.println("</div> <!-- right panel ends here -->");
pw.println("</div> <!-- conten-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>");
pw.println("&copy; Krish Lalwani 2050");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- Main Continer ends here -->");
pw.println("</body>");
pw.println("</html>");
}catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing
}
}
}