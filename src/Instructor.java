/** Name: 
 * StudentID:
 * Section:
 */


import java.util.*;

public class Instructor extends Person {

	private ArrayList<Integer> skill;
	private ArrayList<String> researchInterest;
	private ArrayList<RegCourse> teaching;
	
	public Instructor(String firstName, String lastName, int age, char gender) {
	//CODE HERE
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAge(age);
		this.setGender(gender);
		this.skill = new ArrayList<Integer>();
		this.researchInterest = new ArrayList<String>();
		this.teaching = new ArrayList<RegCourse>();
	}
	
	//Other relevant methods should be defined here
	public ArrayList<RegCourse> getTeaching() {
		return teaching;
	}
	//add a teaching course
	public void setTeaching(RegCourse course) {
		this.teaching.add(course);
	}
	

	//Printing Instructor information @Overridding
    public void printInfo(){
    //CODE HERE
    	if(this.teaching.size()!=0){
			System.out.println("[Teaching courses]");
			for(RegCourse i : this.teaching){
				System.out.println(i.getCourseCode()+" - "+i.getCourseName());
			}
		}
    }
}
