package com.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDBUtil studentDBUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	
	private DataSource datasource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			studentDBUtil = new StudentDBUtil(datasource);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String theSearchName = request.getParameter("theSearchName");
		System.out.println("theSearchName parameter: " + theSearchName);

		try {
			String theCommand = request.getParameter("command");
			
			if(theCommand == null) {
				theCommand = "LIST";
			}
			switch(theCommand) {
				
			case "LIST":
				listStudents(request,response);
				break;
			
			case "LOAD":
				loadStudent(request,response);
				break;	
				
			case "UPDATE":
				updateStudent(request,response);
				break;
				
			case "DELETE":
				deleteStudent(request,response);
				break;
				
			case "SEARCH":
                searchStudents(request, response);
                break;
			
			default:
				listStudents(request,response);
			}
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String theCommand = request.getParameter("command");
                    
            switch (theCommand) {
                            
            case "ADD":
                addStudent(request, response);
                break;
                                
            default:
                listStudents(request, response);
            }
                
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
        
    }
	
	private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String theSearchName = request.getParameter("theSearchName");
        
        List<Student> students = studentDBUtil.searchStudents(theSearchName);
        
        request.setAttribute("student_List", students);
                
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list_students.jsp");
        dispatcher.forward(request, response);
    }

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String studentId = request.getParameter("studentId");
		
		studentDBUtil.deleteStudent(studentId);
		
		listStudents(request,response);
		
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student theStudent = new Student(id, firstName, lastName, email);
		
		studentDBUtil.updateStudent(theStudent);
		
		listStudents(request,response);
	}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response)throws Exception {
		
		String theStudentId = request.getParameter("studentId");
		
		Student theStudent = studentDBUtil.getStudent(theStudentId);
		
		request.setAttribute("the_student", theStudent);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update_student_form.jsp");
		dispatcher.forward(request,response);
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student newStudent = new Student(firstName,lastName,email);
		
		studentDBUtil.addStudent(newStudent);
		
		response.sendRedirect(request.getContextPath() + "/StudentControllerServlet?command=LIST");
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Student> students = studentDBUtil.getStudents();
		
		request.setAttribute("student_list", students);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list_students.jsp");
		dispatcher.forward(request, response);
	}
	
}
