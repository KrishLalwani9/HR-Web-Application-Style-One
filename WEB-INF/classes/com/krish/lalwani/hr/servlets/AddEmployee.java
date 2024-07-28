package com.krish.lalwani.hr.servlets;
import com.krish.lalwani.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.math.*;
import java.util.*;
public class AddEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
String name="";
int designationCode=0;
java.util.Date dateOfBirth=null;
String gender="";
String isIndian="No";
BigDecimal basicSalary=null;
String panNumber="";
String aadharCardNumber="";
try
{
pw=response.getWriter();
response.setContentType("text/html");

SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

name=request.getParameter("name").trim();
try
{
designationCode=Integer.parseInt(request.getParameter("designationCode"));
}catch(NumberFormatException nfe)
{
//GetDesignationAdd Form page with a exception
System.out.println(nfe.getMessage());
}
try
{
dateOfBirth=sdf.parse(request.getParameter("dateOfBirth"));
}catch(ParseException pe)
{
//GetDesignationAdd Form page with a exception
System.out.println(pe.getMessage());
}
gender=request.getParameter("gender");
isIndian=request.getParameter("isIndian");
basicSalary=new BigDecimal(request.getParameter("basicSalary"));
panNumber=request.getParameter("panNumber");
aadharCardNumber=request.getParameter("aadharCardNumber");
EmployeeDAO employeeDAO=new EmployeeDAO();
DesignationDAO designationDAO=new DesignationDAO();
try
{
boolean designationCodeExists=designationDAO.designationCodeExists(designationCode);
boolean panNumberExists=employeeDAO.panNumberExists(panNumber);
boolean aadharCardNumberExists=employeeDAO.aadharCardNumberExists(aadharCardNumber);
if(designationCodeExists==false || panNumberExists || aadharCardNumberExists)
{
List<DesignationDTO> designations=new DesignationDAO().getAll();
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var valid=true;");
pw.println("var firstInvalidComponent=null;");
pw.println("var name=frm.name.value.trim();");
pw.println("var nameErrorSection=document.getElementById('nameErrorSection');");
pw.println("nameErrorSection.innerHTML='';");
pw.println("if(name.length==0)");
pw.println("{");
pw.println("nameErrorSection.innerHTML='Name Required';");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.name;");
pw.println("valid=false;");
pw.println("}");
pw.println("");
pw.println("var designationCode=frm.designationCode.value;");
pw.println("var designationCodeErrorSection=document.getElementById('designationCodeErrorSection');");
pw.println("designationCodeErrorSection.innerHTML='';");
pw.println("if(designationCode==-1)");
pw.println("{");
pw.println("designationCodeErrorSection.innerHTML='Select Designation';");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.designationCode;");
pw.println("valid=false;");
pw.println("}");
pw.println("");
pw.println("var dateOfBirth=frm.dateOfBirth.value;");
pw.println("var dateOfBirthErrorSection=document.getElementById('dateOfBirthErrorSection');");
pw.println("dateOfBirthErrorSection.innerHTML='';");
pw.println("if(dateOfBirth.length==0)");
pw.println("{");
pw.println("dateOfBirthErrorSection.innerHTML='Date of birth Required';");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.dateOfBirth;");
pw.println("valid=false;");
pw.println("}");
pw.println("");
pw.println("var genderErrorSection=document.getElementById('genderErrorSection');");
pw.println("genderErrorSection.innerHTML='';");
pw.println("if(frm.gender[0].checked==false && frm.gender[1].checked==false)");
pw.println("{");
pw.println("genderErrorSection.innerHTML='Select Gender';");
pw.println("valid=false;");
pw.println("}");
pw.println("");
pw.println("var basicSalary=frm.basicSalary.value.trim();");
pw.println("var basicSalaryErrorSection=document.getElementById('basicSalaryErrorSection');");
pw.println("basicSalaryErrorSection.innerHTML='';");
pw.println("if(basicSalary.length==0)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Basic salary Required';");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;");
pw.println("valid=false;");
pw.println("}");
pw.println("else");
pw.println("{");
pw.println("var isBasicSalaryValid=true;");
pw.println("e=0;");
pw.println("var v='0123456789.';");
pw.println("while(e<basicSalary.length)");
pw.println("{");
pw.println("if(v.indexOf(basicSalary.charAt(e))==-1)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid Basic salary';");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;");
pw.println("valid=false;");
pw.println("isBasicSalaryValid=false;");
pw.println("break;");
pw.println("}");
pw.println("e++;");
pw.println("}");
pw.println("if(isBasicSalaryValid)");
pw.println("{");
pw.println("var dot=basicSalary.indexOf('.');");
pw.println("if(dot!=-1)");
pw.println("{");
pw.println("var numberOfFractions=basicSalary.length-(dot+1);");
pw.println("if(numberOfFractions>2)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid Basic salary';");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;");
pw.println("valid=false;");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("");
pw.println("");
pw.println("var panNumber=frm.panNumber.value.trim();");
pw.println("var panNumberErrorSection=document.getElementById('panNumberErrorSection');");
pw.println("panNumberErrorSection.innerHTML='';");
pw.println("if(panNumber.length==0)");
pw.println("{");
pw.println("panNumberErrorSection.innerHTML='PAN Number Required';");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.panNumber;");
pw.println("valid=false;");
pw.println("}");
pw.println("");
pw.println("var aadharCardNumber=frm.aadharCardNumber.value.trim();");
pw.println("var aadharCardNumberErrorSection=document.getElementById(\"aadharCardNumberErrorSection\");");
pw.println("aadharCardNumberErrorSection.innerHTML='';");
pw.println("if(aadharCardNumber.length==0)");
pw.println("{");
pw.println("aadharCardNumberErrorSection.innerHTML='Aadhar Card Number Required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.aadharCardNumber;");
pw.println("}");
pw.println("");
pw.println("");
pw.println("if(!valid) firstInvalidComponent.focus();");
pw.println("return valid;");
pw.println("}");
pw.println("function cancelAddition()");
pw.println("{");
pw.println("document.getElementById('cancelAdditionForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main Continer starts here -->");
pw.println("<div style='width:90hw;margin:40px;height:auto;border:1px solid black'>");
pw.println("<!-- header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><image src='/styleone/images/logo.png' style='float:left'></a><div style='margin-top=30px;margin-bottom:20px;font-size:25pt'>Krish Lalwani</div>");
pw.println("</div> <!-- header ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styelone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div> <!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-top:5px;margin-bottom:5px;margin-left:105px;margin-right:5px;padding:5px;border:1px solid black'>");
pw.println("<h2>Employee (Add Module)</h2>");
pw.println("<form method='post' action='/styleone/addEmployee' onsubmit='return validateForm(this)'>");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>Name</td>");
pw.println("<td>");
pw.println("<input type='text' id='name' name='name' maxlength='50' size='51' value='"+name+"'>");
pw.println("<span id='nameErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Designation</td>");
pw.println("<td>");
pw.println("<select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");

for(DesignationDTO designation : designations)
{
if(designation.getCode()==designationCode) pw.println("<option selected value='"+(designation.getCode())+"'>"+designation.getTitle()+"</option>");
else pw.println("<option value='"+(designation.getCode())+"'>"+designation.getTitle()+"</option>");
}
pw.println("</select>");
if(designationCodeExists==false) pw.println("<span id='designationCodeErrorSection' style='color:red'>Invalid designation code</span><br>");
else pw.println("<span id='designationCodeErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth</td>");
pw.println("<td>");
pw.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='"+sdf.format(dateOfBirth)+"'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Gender</td>");
pw.println("<td>");
if(gender.equals("M")) pw.println("<input checked type='radio' id='male' name='gender' value='M'>Male");
else pw.println("<input type='radio' id='male' name='gender' value='M'>Male");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
if(gender.equals("F")) pw.println("<input checked type='radio' id='female' name='gender' value='F'>Female");
else pw.println("<input type='radio' id='female' name='gender' value='F'>Female");
pw.println("<span id='genderErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Indian ?</td>");
pw.println("<td>");
if(isIndian==null || isIndian.equals("No")) pw.println("<input type='checkbox' id='isIndian' name='isIndian' value='Yes'>");
else pw.println("<input checked type='checkbox' id='isIndian' name='isIndian' value='Yes'>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary</td>");
pw.println("<td>");
pw.println("<input type='text' style='text-align:right' id='basicSalary' name='basicSalary' maxlength='11' size='13' value='"+basicSalary.toPlainString()+"'>"); 
pw.println("<span id='basicSalaryErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>PAN Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='"+panNumber+"'>");
if(panNumberExists) pw.println("<span id='panNumberErrorSection' style='color:red'>PAN Number exists.</span><br>");
else pw.println("<span id='panNumberErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Aadhar Card Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='"+aadharCardNumber+"'>");
if(aadharCardNumberExists) pw.println("<span id='aadharCardNumberErrorSection' style='color:red'>Aadhar Card Number exists.</span><br>");
else pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<tr>");
pw.println("<td colspan='2'>");
pw.println("<button type='submit'>Add</button>&nbsp;&nbsp;");
pw.println("<button type='button' onclick='cancelAddition()'>Cancel</button>");
pw.println("</td>");
pw.println("</table>");
pw.println("</form> ");
pw.println("</div> <!-- right panel ends here -->");
pw.println("</div> <!-- conten-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid white'>");
pw.println("&copy; Krish Lalwani 2050");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div> <!-- Main Continer ends here -->");
pw.println("<form id='cancelAdditionForm' action='/styleone/employeesView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
return;
}
}catch(DAOException exception)
{
System.out.println(exception.getMessage()+"***");
return;
}
EmployeeDTO employee=new EmployeeDTO();
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian((isIndian==null || isIndian.equalsIgnoreCase("No"))?false:true);
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);
try
{
employeeDAO.addEmployee(employee);
}catch(DAOException daoException)
{
//send back all exceptions page
System.out.println(daoException.getMessage());
}
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>HR Application</title>");
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
pw.println("<a href='/styleone/index.html'>home</a>");
pw.println("</div> <!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-top:5px;margin-bottom:5px;margin-left:105px;margin-right:5px;padding:5px;border:1px solid black'>");
pw.println("<h3>Notification</h3>");
pw.println("Employee added<br>");
pw.println("Add more Employees ?<br>");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>");
pw.println("<form action='/styleone/getEmployeeAddForm'>");
pw.println("<button type='submit'>Yes</button>"); //style='float:left' is removed
pw.println("</form>");
pw.println("</td>");
pw.println("<td>");
pw.println("<form action='/styleone/employeesView'>");
pw.println("<button type='submit'>No</button>");
pw.println("</form>");
pw.println("</td>");
pw.println("</tr>");
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
}
catch(Exception exception)
{
System.out.println(exception.getMessage());

}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}