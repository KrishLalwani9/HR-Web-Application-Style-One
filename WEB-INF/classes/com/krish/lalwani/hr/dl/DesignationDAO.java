package com.krish.lalwani.hr.dl;
import java.sql.*;
import java.util.*;
public class DesignationDAO
{
public List<DesignationDTO> getAll() throws Exception
{
List<DesignationDTO> designations;
designations=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from designation order by title");
DesignationDTO designationDTO;
int code;
String title;
while(resultSet.next())
{
code=resultSet.getInt("code");
title=resultSet.getString("title").trim();
designationDTO=new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designations.add(designationDTO);
}
resultSet.close();
statement.close();
connection.close();
}catch(Exception exception)
{
System.out.println("1*******************************************************************************");
throw new DAOException(exception.getMessage());
}
return designations;
}
}