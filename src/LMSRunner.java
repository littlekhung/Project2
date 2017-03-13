import java.util.*;
/** LMSRunner.java - Start building ICT-LMS system 
 * Name: 
 * StudentID:
 * Section:
 */

/*
 *  LMSRunner is a main class for Administrators and Students to access the LMS based on their role.
 *  In LMSRunner, your task is to implement the system asking which roles a user is.
 *  
 *  If a user is an Administrator, he/she can do the following tasks
 *  - Add/Update/Delete Course
 *  - Add/Update/Delete Instructor
 *  - Add/Update/Delete Student
 *  - Able to set grading criteria (e.g., attendance, project, quiz, midterm, final) to the Course.
 *  - Able to add score of students in each criterion to the system.
 *  - Able to print information of all instructors and their teaching courses.
 *  - Able to print information of all students and their registered courses.
 *  
 *  For Students, They can do the following tasks
 *  - Able to register to the existing courses (add by administrator).
 *  - Able to see the accumulated GPA (only the subject that they have completed).
 *  - Able to evaluate the score that a student need to do to get  ‘A’ for a particular subject (only work with subject that they haven’t completed).
 *  - Able to illustrate subject that a student is likely have a problem with (only work with subject that they haven’t completed).
 *  - Able to search names of the instructors who teach in the registered courses.
 *
 * */

public class LMSRunner {

	//CODE HERE
	
	public static void main(String[] args) {
		Scanner a = new Scanner(System.in);
		ArrayList<Integer> c = new ArrayList<Integer>();
		
		for(int i=0;i<5;i++){
			c.add(a.nextInt());
		}
		for(Integer i : c){
			System.out.println(i);
		}
		
		//CODE HERE
		
	}

}
