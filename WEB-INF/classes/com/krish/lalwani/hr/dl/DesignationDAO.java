package com.krish.lalwani.hr.dl;
import java.sql.*;
import java.util.*;
public class DesignationDAO
{
public void addDesignation(DesignationDTO designation) throws DAOException
{
try
{
String title=designation.getTitle();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select (code) from designation where title=(?)");
preparedStatement.setString(1,title);
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Designation : "+title+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into designation (title) values (?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,title);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int code=resultSet.getInt(1);
resultSet.close();
preparedStatement.close();
connection.close();
designation.setCode(code);
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage()); //remove after testing
}
}
public List<DesignationDTO> getAll() throws DAOException
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
throw new DAOException(exception.getMessage());
}
return designations;
}
public DesignationDTO getByCode(int code) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation code : "+code);
}
DesignationDTO designation=new DesignationDTO();
designation.setCode(resultSet.getInt("code"));
designation.setTitle(resultSet.getString("title").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return designation;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
//***********************************************************************************
public void updateDesignation(DesignationDTO designation) throws DAOException
{
try
{
int code=designation.getCode();
String title=designation.getTitle();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation code : "+code);
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("Select * from designation where title=? and code !=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Designation : "+title+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update designation set title=? where code=?");
preparedStatement.setString(1,title);
preparedStatement.setInt(2,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage()); // remove after testing
}
}
public void deleteDesignation(int code) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from designation where code=?");
preparedStatement.setInt(1,code);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid designation code : "+code);
}
resultSet.close();
preparedStatement.close();
//one check pending related to if this designation has been alloted to employee
preparedStatement=connection.prepareStatement("delete from designation where code=?");
preparedStatement.setInt(1,code);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage()); // remove after testing
}
}
}