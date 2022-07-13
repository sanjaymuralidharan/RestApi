package com.demo.StudentInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;

public class StudentDao 
{
	
	Connection conn=null;
	InputStream fis=null;
	Properties p=new Properties (); 
	
	public StudentDao()
	{	
		try 
		{
		fis=new FileInputStream("C:\\Users\\intel\\Documents\\projectj\\StudentInfo\\db.properties"); 
        p.load (fis); 
        String dname=p.getProperty ("Dname"); 
        String url=p.getProperty ("URL"); 
        String username=p.getProperty ("Uname"); 
        String password=  p.getProperty ("password"); 
        Class.forName(dname); 
		conn=DriverManager.getConnection(url,username,password);
		
		}
		catch (Exception e) {
			System.out.println(e);
		}
		 
	}
	
	public List<Student> getStudents()
	{
		List<Student> students =new ArrayList<Student>();
		String sql="select * from student";
		try 
		{
			Statement stm = conn.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			while(rs.next()) 
			{
				Student s = new Student();
				s.setStudentId(rs.getInt(1));
				s.setStudentName(rs.getString(2));
				s.setStudentDOB(rs.getString(3));
				s.setStudentDOJ(rs.getString(4));
				
				students.add(s);
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		
		return students;
	}
	
	
	public Student getStudent(int id)
	{
		Student s = new Student();
		String sql="select * from student where id="+id;
		try 
		{
			Statement stm = conn.createStatement();
			ResultSet rs= stm.executeQuery(sql);
			if(rs.next()) 
			{
				
				s.setStudentId(rs.getInt(1));
				s.setStudentName(rs.getString(2));
				s.setStudentDOB(rs.getString(3));
				s.setStudentDOJ(rs.getString(4));
				
				
			}
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		return s;
		
	}

	public void create(Student s) 
	{
		String sql="insert into student values(?,?,?,?)";
		try 
		{
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1,s.getStudentId());
			stm.setString(2,s.getStudentName());
			stm.setString(3,s.getStudentDOB());
			stm.setString(4,s.getStudentDOJ());
			
			stm.executeUpdate();
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void update(Student s) 
	{
		String sql="update student set name=?,dob=?,doj=? where id=?;";
		try 
		{
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setString(1,s.getStudentName());
			stm.setString(2,s.getStudentDOB());
			stm.setString(3,s.getStudentDOJ());
			stm.setInt(4,s.getStudentId());
			
			stm.executeUpdate();
			
		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void delete(int id)
	{
		String sql="delete from student where id=?";
		try 
		{
			PreparedStatement stm = conn.prepareStatement(sql);
			
			stm.setInt(1,id);
			stm.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println(e);
		}
		
	}
}
