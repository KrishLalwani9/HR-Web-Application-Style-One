package com.krish.lalwani.hr.servlets;
import com.krish.lalwani.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.math.*;
import java.util.*;
public class EditEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw=null;
String employeeId="";
String name="";
int designationCode=0;
java.util.Date dateOfBirth=null;
String gender="";
boolean isIndian=false;
BigDecimal basicSalary=null;
String panNumber="";
String aadharCardNumber="";
try
{
pw=response.getWriter();
response.setContentType("text/html");
employeeId=request.getParameter("employeeId");
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
EmployeeDAO employeeDAO=new EmployeeDAO();
EmployeeDTO employee=null;
try
{
employee=employeeDAO.getByEmployeeId(employeeId);
name=employee.getName();
dateOfBirth=employee.getDateOfBirth();
designationCode=employee.getDesignationCode();
gender=employee.getGender();
isIndian=employee.getIsIndian();
basicSalary=employee.getBasicSalary();
panNumber=employee.getPANNumber();
aadharCardNumber=employee.getAadharCardNumber();
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
pw.println("function cancelUpdate()");
pw.println("{");
pw.println("document.getElementById('cancelUpdateForm').submit();");
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
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div> <!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-top:5px;margin-bottom:5px;margin-left:105px;margin-right:5px;padding:5px;border:1px solid black'>");
pw.println("<h2>Employee (Edit Module)</h2>");
pw.println("<form method='post' action='/styleone/updateEmployee' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' id='employeeId' name='employeeId' value='"+employeeId+"'>");
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
pw.println("<span id='designationCodeErrorSection' style='color:red'></span><br>");
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
if(!isIndian) pw.println("<input type='checkbox' id='isIndian' name='isIndian' value='Yes'>");
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
pw.println("<span id='panNumberErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Aadhar Card Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='"+aadharCardNumber+"'>");
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span><br>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<tr>");
pw.println("<td colspan='2'>");
pw.println("<button type='submit'>Update</button>&nbsp;&nbsp;");
pw.println("<button type='button' onclick='cancelUpdate()'>Cancel</button>");
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
pw.println("<form id='cancelUpdateForm' action='/styleone/employeesView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(DAOException exception)
{
sendBackView(response,exception);
return;
}
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
private void sendBackView(HttpServletResponse response,DAOException daoException)
{
try
{
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
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
pw.println("<div style='color:red'>"+daoException.getMessage()+"</div>");
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
System.out.println(exception.getMessage()); //remove after testing and setup 500 (internal error code)
}
}
}