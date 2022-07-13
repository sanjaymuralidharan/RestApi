package com.demo.StudentInfo;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("students")
public class StudentResource 
{
	
	StudentDao repo=new StudentDao();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudents()
	{
				
		return repo.getStudents();
	}
	
	@GET
	@Path("student/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("id") int id)
	{
		return repo.getStudent(id);
	}
	
	
	@POST
	@Path("addStudent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Student createStudent(Student s)
	{
		repo.create(s);
		
		return s;
	}
	
	
	@PUT
	@Path("updateStudent")
	@Consumes(MediaType.APPLICATION_JSON)
	public Student updateStudent(Student s)
	{
		if(repo.getStudent(s.getStudentId()).getStudentId()==0) 
		{
			repo.create(s);
		}
		else
		{
			repo.update(s);
		}
		
		return s;
	}
	
	@DELETE
	@Path("deleteStudent/{id}")
	public Student deleteStudent(@PathParam("id")int id) 
	{
			Student s=repo.getStudent(id);
			if(s.getStudentId()!=0)
			{
				repo.delete(id);
			}
			return s;
		
	}

}
