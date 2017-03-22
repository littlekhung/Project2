import java.util.ArrayList;

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
	/**
	 * I change these variable to double because i want RegCourse to store many student score
	 */
	private double quiz;
	private double attendance;
	private double projects;
	private double midScore;
	private double finalScore;
	
	private double accumScore;
	private boolean completedCourse=false;
	private Student scoreOwner; // to identify the student who own this score
	private ArrayList<RegCourse> studentScore; // ArrayList of RegCourse to store each student's score
	
	//Constructor to setup an object of a registered course
	RegCourse(String code, String cname, boolean core, int cCredit){
		super(code,cname,core,cCredit);
		this.studentScore = new ArrayList<RegCourse>(); // initialize the ArrayList to store each student's score
	}
	RegCourse(Student student){ // for creating a RegCourse for just store score for each student (no need to put the details)
		super("0","0",true,0);
		this.scoreOwner = student;
	}
	
	//Other relevant methods should be defined here
	
	public double getQuiz() {
		return quiz;
	}

	public Student getScoreOwner() {
		return scoreOwner;
	}
	public void setScoreOwner(Student scoreOwner) {
		this.scoreOwner = scoreOwner;
	}
	public ArrayList<RegCourse> getStudentScore() {
		return studentScore;
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
		if(this.finalScore>=0){
			this.setCompletedCourse(true);
		}
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
	/**
	 * method for the root course only (Course which store every detail)
	 * to add a RegCourse for score storing purpose to itself
	 * @param a
	 */
	public void enrollStudent(Student a){
		this.studentScore.add(new RegCourse(a));
	}
	
	// remove student from the course
	public void removeStudent(Student a){
		RegCourse remove=null;
		for(RegCourse i : this.studentScore){
			if(i.getScoreOwner().getFirstName().equals(a.getFirstName()) && i.getScoreOwner().getLastName().equals(a.getLastName())){
				remove = i;
				break;
			}
		}
		this.studentScore.remove(remove);
	}
	//Printing Course Information 
	public void printCourseInfo(){
		//CODE HERE
	}
	
}
