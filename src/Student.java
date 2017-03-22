/* Name: 
 * StudentID:
 * Section:
 */

/* Student is a class that collect registered courses information and subject of interest.
 * The objective of this class is to allow a students to be able to do the following tasks 
 * 
 *  - Register on the course.
 *  - Set scores for a course based on course code
 *  - Calculate the accummulate GPA
 *  - Show the subject that students may fail after the midterm exam
 *  - Method to calculate how much score students need to do to get an A 
 * */

import java.util.ArrayList;

public class Student extends Person {
	
	private ArrayList<String> subjectInterest;
	private ArrayList<RegCourse> registeredCourses;
	
	// Constructor
	public Student(String firstName, String lastName, int age, char gender) {
		//CODE HERE
		super(firstName,lastName,age,gender);
		this.subjectInterest = new ArrayList<String>();
		this.registeredCourses = new ArrayList<RegCourse>();
	}
	//getter for registeredCourses used in LMSRunner
	public ArrayList<RegCourse> getRegisteredCourses() {
		return registeredCourses;
	}
	
	//Printing Students basic information @Overriding
	public void printInfo(){
		//CODE HERE
		super.printInfo();
		if(this.registeredCourses.size()!=0){
			System.out.println("[Registered courses]");
			for(RegCourse i : this.registeredCourses){
				System.out.println(i.getCourseCode()+" - "+i.getCourseName());
			}
		}
	}

	//Method for students to register course
	public void RegisterCourse(RegCourse a){
		//CODE HERE
		this.registeredCourses.add(a);
		a.enrollStudent(this);
	}
	
	//Method for removing student from a course
	public void removeFromCourse(RegCourse a){
		this.registeredCourses.remove(a);
		a.removeStudent(this);
	}
	
	//Method for student to add RAW scores on particular course code 
	public void setAllScore(String cCode, int attScore, int quiScore, int proScore,int miScore, int fiScore) {
		//CODE HERE
		for(RegCourse i : this.registeredCourses){
			if(i.getCourseCode().equals(cCode)){
				for(RegCourse j : i.getStudentScore()){
					if(j.getScoreOwner().getFirstName().equals(this.getFirstName()) && j.getScoreOwner().getLastName().equals(this.getLastName())){
						j.setAttendance(attScore);
						j.setQuiz(quiScore);
						j.setProjects(proScore);
						j.setMidScore(miScore);
						j.setFinalScore(fiScore);
						return;
					}
				}
			}
		}
		
	}
	
	//Method for converting accumulate score to an alphabet GRADE (e.g., A, B, C, D, F)
	// A=4.0, B=3.0, C=2.0, D=1.0, F=0.0
	public char Grading(RegCourse b){
		RegCourse a=null;
		double tot;
		double att=0,qui=0,pro=0,mi=0,fi=0;
		for(RegCourse i : b.getStudentScore()){
			if(i.getScoreOwner().getFirstName().equals(this.getFirstName()) && i.getScoreOwner().getLastName().equals(this.getLastName())){
				a=i;
				break;
			}
		}
/////////////////////////////////////////Calculation////////////////////////////////////////////////
		
		if (b.getFull_score_attendance() != 0)
			att = (a.getAttendance() / b.getFull_score_attendance()) * b.getAttendancePercent();
		if (b.getFull_score_quiz() != 0)
			qui = (a.getQuiz() / b.getFull_score_quiz()) * b.getQuizPercent();
		if (b.getFull_score_projects() != 0)
			pro = (a.getProjects() / b.getFull_score_projects()) * b.getProjPercent();
		if (b.getFull_score_midScore() != 0)
			mi = (a.getMidScore() / b.getFull_score_midScore()) * b.getMidtermPercent();
		if (b.getFull_score_finalScore() != 0 && a.getFinalScore() != -1)
			fi = (a.getFinalScore() / b.getFull_score_finalScore()) * b.getFinalPercent();
		
/////////////////////////////////////////Assign Score///////////////////////////////////////////////
		a.setAccumScore(att + qui + pro + mi + fi);
//////////////////////////////////////////Return Part///////////////////////////////////////////////
		
		tot = a.getAccumScore();
		if(tot>=0 && tot<=100){
			if(tot<50)
				return 'F';
			else if(tot<60)
				return 'D';
			else if(tot<70)
				return 'C';
			else if(tot<80)
				return 'B';
			else
				return 'A';
		}
		else{
			System.out.println("Score Error"); //in case of error
			return ' ';
		}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	// Method for calculating accumulate GPA (only applied for completed courses)
	// The Accumulate GPA is calculated by (1) multiply each numeric grade value 
	// by the number of credits the course was defined. (2) add these number together  
	// (3) Divided this number by total number of credit a student took (only the completed course)
	public double accumGPA(){	
		double finalgpa = 0.0;
		double totCredit = 0;
		double totGain = 0;
		for(RegCourse i : this.registeredCourses){
			for(RegCourse j : i.getStudentScore()){
				if(j.getScoreOwner().getFirstName().equals(this.getFirstName()) && j.getScoreOwner().getLastName().equals(this.getLastName())){
					if(j.isCompletedCourse()){
						totCredit+=i.getCourseCredit();
						switch(this.Grading(i)){
						case 'A':
							totGain+=4.0*i.getCourseCredit();
							break;
						case 'B':
							totGain+=3.0*i.getCourseCredit();
							break;
						case 'C':
							totGain+=2.0*i.getCourseCredit();
							break;
						case 'D':
							totGain+=1.0*i.getCourseCredit();
							break;
						case 'F':
							totGain+=0.0*i.getCourseCredit();
							break;
						}
					}
				}
			}
		}
		finalgpa = totGain/totCredit;
		//CODE HERE
		return finalgpa;
	}
	
	// Method for checking and printing course that students may have problems
	// The severe subject is calculated by accumulate score < half of a current full score
	// E.g., Assume that  the Object-Oriented programming has grading criteria as attendance=10%, quiz=10%, project=20%, midterm=30%, final=30% 
	// Currently your score is attendance=50/100, quiz=4/10, project=45/100, midterm 48/100, finalexam = 0/100 (haven't done final exam) .
	// The accumulate score = (50*10)/100 + (4*10)/10 + (45*20)/100 + (48*30)/100 + 0 
	//	                    =  5 + 4 + 9 + 14.4 + 0  = 32.4
	// Therefore, the accumulate score (32.4) less than half of a current grading criteria score which is (35)
	// Student may have a problem with this subject at the end of the course (after final exam).
	public void severeSubject(){
		//CODE HERE
		ArrayList<RegCourse> ser = new ArrayList<RegCourse>();
		for(RegCourse i : this.registeredCourses){
			for(RegCourse j : i.getStudentScore()){
				if(j.getScoreOwner().getFirstName().equals(this.getFirstName()) && j.getScoreOwner().getLastName().equals(this.getLastName())){
					if(j.isCompletedCourse()){
						continue;
					}
					double hocfs=0; //half of current full score
					double curScore=0;
					curScore=j.getAttendance()+j.getQuiz()+j.getProjects()+j.getMidScore();
					hocfs=i.getFull_score_attendance()+i.getFull_score_quiz()+i.getFull_score_projects()+i.getFull_score_midScore();
					hocfs/=2;
					if(curScore<hocfs){
						ser.add(i);
					}
				}
			}
		}
		if(ser.size()!=0){
			System.out.println("[Severe subject]");
			for(RegCourse i : ser){
				System.out.println(i.getCourseCode()+": "+i.getCourseName());
			}
		}
	}

	// Method to calculate on how much score a student need to work on to get an A on a given course
	// To get an 'A', students must have score more than 80%
	public void howToGetASubject(String cCode){
		//CODE HERE
		double scoreNeeded=0;
		for(RegCourse i : this.registeredCourses){
			if(i.getCourseCode().equals(cCode)){
				for(RegCourse j : i.getStudentScore()){
					if(j.getScoreOwner().getFirstName().equals(this.getFirstName()) && j.getScoreOwner().getLastName().equals(this.getLastName())){
						if(j.isCompletedCourse()){
							System.out.println("Completed Course!!!");
							return;
						}
						this.Grading(i);
						scoreNeeded=80.0-j.getAccumScore();
						System.out.println("["+i.getCourseCode()+"-"+i.getCourseName()+"]");
						if(scoreNeeded>i.getFinalPercent()){
							System.out.println("You can't get A for this subject");
						}
						else{
							System.out.println("You need "+scoreNeeded+" score more to get an A for this subject");
						}
						break;
					}
				}
			}
		}
	}
	
	//Method to lists all instructors that teach the registered subjects
	public void relevantInstructor(ArrayList<Instructor> listINS){
		//CODE HERE
		ArrayList<Instructor> rel = new ArrayList<Instructor>();
		boolean checkAld=false;
		for(RegCourse i : this.registeredCourses){	 //check each student's register courses
			for(Instructor j : listINS){			//check each instructor
				for(RegCourse k : j.getTeaching()){	//check each instructor's register courses
					if(i.getCourseCode().equals(k.getCourseCode())){	
						for(Instructor l : rel){	//check of the instructor is already in relevant list
							if(j.equals(l)){
								checkAld = true;
								break;
							}
						}
						if(checkAld == false)
							rel.add(j);
						else
							checkAld = false;
						break;
					}
				}
			}
		}
		if(rel.size()!=0){
			System.out.println("[Relevant Instructor]");
			for(Instructor i : rel){
				System.out.println(i.getFirstName()+" "+i.getLastName());
			}
		}
	}

}
