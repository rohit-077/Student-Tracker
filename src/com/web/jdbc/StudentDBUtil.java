package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDBUtil {
	
	private DataSource datasource;
	
	public StudentDBUtil(DataSource theDatasource) {
		datasource =  theDatasource;
	}
	
	public List<Student> getStudents() throws Exception{
		
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs =  null;
		
		try {
			
			myConn = datasource.getConnection();
			
			String sql = "select * from student order by id";
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				Student tempStudent = new Student(id,firstName,lastName,email);
				students.add(tempStudent);
				
			}
			
			return students;
		}
		finally {
			close(myConn, myStmt , myRs);
		}
		
		
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myConn!=null) {
				myConn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void addStudent(Student newStudent) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			myConn = datasource.getConnection();
			
			String sql = "insert into student "
						+ "(first_name, last_name, email) "
						+ "values (?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			//myStmt.setString(1, newStudent.getRegId());
			myStmt.setString(1, newStudent.getFirstName());
			myStmt.setString(2, newStudent.getLastName());
			myStmt.setString(3, newStudent.getEmail());
			
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}
		
	}

	public Student getStudent(String theStudentId)throws Exception {
		
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			studentId = Integer.parseInt(theStudentId);
			
			myConn = datasource.getConnection();
			
			String sql = "select * from student where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, studentId);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				String firstName  = myRs.getString("first_name");
				String lastName  = myRs.getString("last_name");
				String email  = myRs.getString("email");
				
				theStudent  = new Student(studentId, firstName, lastName, email);
				
			}
			else {
				throw new Exception("Could not find student id: " + studentId);
			}
			return theStudent;
		}
		finally {
			close(myConn,myStmt,myRs);
		}
		
	}

	public void updateStudent(Student theStudent)throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {

			myConn = datasource.getConnection();
			
			String sql = "update student " + "set first_name=?, last_name=?, email=? "
						 + "where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			myStmt.execute();
		}
		finally {
			close(myConn, myStmt, null);
		}		
	}

	public void deleteStudent(String studentId)throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			int id = Integer.parseInt(studentId);
			
			myConn = datasource.getConnection();
			
			String sql = "delete from student where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, id);
			
			myStmt.execute();
		}
		finally {
			close(myConn,myStmt,null);
		}		
	}	
	
	public List<Student> searchStudents(String theSearchName)  throws Exception {
        List<Student> students = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        int studentId;
        
        try {
            
            myConn = datasource.getConnection();
            
            String sql;

            
            if (theSearchName != null && theSearchName.trim().length() > 0) {
            	
                sql = "select * from student where lower(first_name) like ? or lower(last_name) like ?";
                
                myStmt = myConn.prepareStatement(sql);
                
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                myStmt.setString(2, theSearchNameLike);
                
                
            } else {
                
                sql = "select * from student order by last_name";
                
                myStmt = myConn.prepareStatement(sql);
            }
            

            myRs = myStmt.executeQuery();
            

            while (myRs.next()) {
                
                
                int id = myRs.getInt("id");
                String firstName = myRs.getString("first_name");
                String lastName = myRs.getString("last_name");
                String email = myRs.getString("email");
                
                
                Student tempStudent = new Student(id, firstName, lastName, email);
                
                
                students.add(tempStudent);            
            }
            
            return students;
        }
        finally {
            
            close(myConn, myStmt, myRs);
        }
    }
}
