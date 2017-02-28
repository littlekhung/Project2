/** Name: 
 * StudentID:
 * Section:
 *
 */

/* RegCourse is a class that collect information about registered course of each student.
 * This class extends from the class Course which contain basic course information
 * that are courseCode, courseName, courseCredit, Percentage of grading criteria
 * and Full score of each criteria.
 * 
 * The RegCourse need to collect more specific information about the scores of each student regarding
 * the grading criteria which are quiz, attendance, projects, midScore, finalScore. The detail of these information
 * are provided below.
 * */
public class RegCourse extends Course {
	
	private double quiz;
	private double attendance;
	private double projects;
	private double midScore;
	private double finalScore;
	
	private double accumScore;
	private boolean completedCourse=false;
	
	//Constructor to setup an object of a registered course
	RegCourse(String code, String cname, boolean core, int cCredit){
		super(code,cname,core,cCredit);
	}
	
	//Other relevant methods should be defined here
	public double getQuiz() {
		return quiz;
	}

	public void setQuiz(double quiz) {
		this.quiz = quiz;
	}

	public double getAttendance() {
		return attendance;
	}

	public void setAttendance(double attendance) {
		this.attendance = attendance;
	}

	public double getProjects() {
		return projects;
	}

	public void setProjects(double projects) {
		this.projects = projects;
	}

	public double getMidScore() {
		return midScore;
	}

	public void setMidScore(double midScore) {
		this.midScore = midScore;
	}

	public double getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}

	public double getAccumScore() {
		return accumScore;
	}

	public void setAccumScore(double accumScore) {
		this.accumScore = accumScore;
	}

	public boolean isCompletedCourse() {
		return completedCourse;
	}

	public void setCompletedCourse(boolean completedCourse) {
		this.completedCourse = completedCourse;
	}
	//Printing Course Information 
	public void printCourseInfo(){
		//CODE HERE
	}
	
}
