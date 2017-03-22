import java.util.*;

/**
 * LMSRunner.java - Start building ICT-LMS system Name: StudentID: Section:
 */

/*
 * LMSRunner is a main class for Administrators and Students to access the LMS
 * based on their role. In LMSRunner, your task is to implement the system
 * asking which roles a user is.
 * 
 * If a user is an Administrator, he/she can do the following tasks -
 * Add/Update/Delete Course - Add/Update/Delete Instructor - Add/Update/Delete
 * Student - Able to set grading criteria (e.g., attendance, project, quiz,
 * midterm, final) to the Course. - Able to add score of students in each
 * criterion to the system. - Able to print information of all instructors and
 * their teaching courses. - Able to print information of all students and their
 * registered courses.
 * 
 * For Students, They can do the following tasks - Able to register to the
 * existing courses (add by administrator). - Able to see the accumulated GPA
 * (only the subject that they have completed). - Able to evaluate the score
 * that a student need to do to get ‘A’ for a particular subject (only work with
 * subject that they haven’t completed). - Able to illustrate subject that a
 * student is likely have a problem with (only work with subject that they
 * haven’t completed). - Able to search names of the instructors who teach in
 * the registered courses.
 *
 */

public class LMSRunner {

	// CODE HERE
	private static ArrayList<RegCourse> courseList = new ArrayList<RegCourse>();
	private static ArrayList<Student> studentList = new ArrayList<Student>();
	private static ArrayList<Instructor> instructorList = new ArrayList<Instructor>();
	private static Scanner a = new Scanner(System.in);

	public static void main(String[] args) {
		// CODE HERE
		menu();

	}

	private static void menu() {
		while (true) {
			System.out.println("\nPlease enter your role");
			System.out.println("[1] Administrator");
			System.out.println("[2] Student");
			System.out.println("[3] Exit");
			System.out.print("Input(1 or 2 or 3): ");
			switch (a.next()) {
			case "1":
				administrator();
				break;
			case "2":
				student();
				break;
			case "3":
				a.close();
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void student() {

	}

	private static void administrator() {
		while (true) {
			System.out.println("\n[Administrator]");
			System.out.println("[1] Add/Update/Delete (Course,Instructor,Student)");
			System.out.println("[2] Add score of students");
			System.out.println("[3] Student or instructor information");
			System.out.println("[4] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				AUD();
				break;
			case "2":
				addScoreChooseStudent();
				break;
			case "3":
				information();
				break;
			case "4":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void AUD() {
		while (true) {
			System.out.println("\n[Which one you want to edit]");
			System.out.println("[1] Course");
			System.out.println("[2] Instructor");
			System.out.println("[3] Student");
			System.out.println("[4] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				AUDcourse();
				break;
			case "2":
				AUDinstructor();
				break;
			case "3":
				AUDstudent();
				break;
			case "4":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void AUDcourse() {
		while (true) {
			System.out.println("\n[Course : What do you want to do?]");
			System.out.println("[1] Add Course");
			System.out.println("[2] Update Course");
			System.out.println("[3] Delete Course");
			System.out.println("[4] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				while (true) {
					String code, name, temp;
					int credit;
					boolean isCore;
					System.out.print("\nEnter Course Code: ");
					code = a.next();
					System.out.print("Enter Course Name: ");
					name = a.next();
					System.out.print("Is this Course Core(y/n): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						isCore = true;
					} else if (temp.equals("n") || temp.equals("N")) {
						isCore = false;
					} else {
						System.out.println("Error: Wrong input please try again");
						continue;
					}
					System.out.print("Enter Course Credit: ");
					if (a.hasNextInt()) {
						credit = a.nextInt();
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					System.out.println("CourseCode: " + code + " | CourseName: " + name + " | Core: " + isCore
							+ " | Credit: " + credit);
					System.out.print("Do you want to add this course? (If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						courseList.add(new RegCourse(code, name, isCore, credit));
						System.out.println("Add Complete!!!");
					} else {
						System.out.println("Cancel Complete!!!");
					}
					System.out.print("Do you want to add another course?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "2":
				while (true) {
					int number;
					System.out.println("\n[Course List]");
					for (int i = 0; i < courseList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + courseList.get(i).getCourseCode() + " - "
								+ courseList.get(i).getCourseName());
					}
					System.out.println("[" + (courseList.size() + 1) + "] Back");
					System.out.print("Please enter course number you want to edit: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == courseList.size()) {
						break;
					}
					if (number >= courseList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					editCourse(courseList.get(number));
				}
				break;
			case "3":
				while (true) {
					int number;
					String temp;
					System.out.println("\n[Course List]");
					for (int i = 0; i < courseList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + courseList.get(i).getCourseCode() + " - "
								+ courseList.get(i).getCourseName());
					}
					System.out.println("[" + (courseList.size() + 1) + "] Back");
					System.out.print("Please enter course number you want to delete: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == courseList.size()) {
						break;
					}
					if (number >= courseList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					System.out.print("Are you sure you want to delete: " + courseList.get(number).getCourseCode()
							+ " - " + courseList.get(number).getCourseName() + "(If yes enter \"y\"):");
					temp = a.next();
					if (!(temp.equals("y") || temp.equals("Y"))) {
						System.out.println("Cancel Complete");
						continue;
					}
					deleteCourse(courseList.get(number));
					System.out.println("Delete Complete");
					System.out.print("Do you want to delete another course?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "4":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void deleteCourse(RegCourse course) {
		// remove from all student
		for (Student i : studentList) {
			for (RegCourse j : i.getRegisteredCourses()) {
				if (j.getCourseCode().equals(course.getCourseCode())
						&& j.getCourseName().equals(course.getCourseName())) {
					i.getRegisteredCourses().remove(j);
				}
			}
		}
		// remove from all instructor
		for (Instructor i : instructorList) {
			for (RegCourse j : i.getTeaching()) {
				if (j.getCourseCode().equals(course.getCourseCode())
						&& j.getCourseName().equals(course.getCourseName())) {
					i.getTeaching().remove(j);
				}
			}
		}
		// remove from course list
		courseList.remove(course);
	}

	private static void editCourse(RegCourse course) {
		String in, temp;
		int credit;
		while (true) {
			System.out.println("\n[Course Update(" + course.getCourseCode() + " - " + course.getCourseName()
					+ "): What do you want to edit?]");
			System.out.println("[1] Grading Criteria");
			System.out.println("[2] Course Code");
			System.out.println("[3] Course Name");
			System.out.println("[4] Course Core");
			System.out.println("[5] Course Credit");
			System.out.println("[6] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				setGradeCriteria(course);
				break;
			case "2":
				System.out.println("Old: " + course.getCourseCode());
				System.out.print("Enter Code: ");
				in = a.next();
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setCourseCode(in);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "3":
				System.out.println("Old: " + course.getCourseName());
				System.out.print("Enter Name: ");
				in = a.next();
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setCourseName(in);
					;
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "4":
				System.out.println("Old: " + course.isCoreCourse());
				System.out.print("Do you want to change? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setCoreCourse(!course.isCoreCourse());
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "5":
				System.out.println("Old: " + course.getCourseCredit() + " Credits");
				System.out.print("Enter Credit: ");
				if (a.hasNextInt()) {
					credit = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					continue;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setCourseCredit(credit);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "6":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void setGradeCriteria(RegCourse course) {
		while (true) {
			System.out.println("\n[Course Update(" + course.getCourseCode() + " - " + course.getCourseName()
					+ "): Which criteria do you want to change?]");
			System.out.println("[1] Attendance Full Score");
			System.out.println("[2] Quiz Full Score");
			System.out.println("[3] Project Full Score");
			System.out.println("[4] Midterm Full Score");
			System.out.println("[5] Final Full Score");
			System.out.println("[6] Course Grading (Percent of each score)");
			System.out.println("[7] Back");
			System.out.print("Input: ");
			double num;
			String temp;
			switch (a.next()) {
			case "1":
				if (course.getAttendancePercent() == 0) {
					System.out.println("You haven't set attendance percent!!!!");
					break;
				}
				System.out.println("Old: " + course.getFull_score_attendance());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setFull_score_attendance(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "2":
				if (course.getQuizPercent() == 0) {
					System.out.println("You haven't set quiz percent!!!");
					break;
				}
				System.out.println("Old: " + course.getFull_score_quiz());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setFull_score_quiz(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "3":
				if (course.getProjPercent() == 0) {
					System.out.println("You haven't set project percent!!!");
					break;
				}
				System.out.println("Old: " + course.getFull_score_projects());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setFull_score_projects(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "4":
				if (course.getMidtermPercent() == 0) {
					System.out.println("You haven't set midterm percent!!!");
					break;
				}
				System.out.println("Old: " + course.getFull_score_midScore());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setFull_score_midScore(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "5":
				if (course.getFinalPercent() == 0) {
					System.out.println("You haven't set final percent!!!");
					break;
				}
				System.out.println("Old: " + course.getFull_score_finalScore());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setFull_score_finalScore(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "6":
				int atten = 0, quiz = 0, project = 0, mid = 0, fina = 0;
				System.out.print("Please enter the percent of attendance: ");
				if (a.hasNextInt()) {
					atten = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Please enter the percent of quiz: ");
				if (a.hasNextInt()) {
					quiz = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Please enter the percent of project: ");
				if (a.hasNextInt()) {
					project = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Please enter the percent of midterm: ");
				if (a.hasNextInt()) {
					mid = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.print("Please enter the percent of final: ");
				if (a.hasNextInt()) {
					fina = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					break;
				}
				System.out.println("Attendance: " + atten + "% | Quiz: " + quiz + "% | Project: " + project
						+ "% | Midterm: " + mid + "% | Final: " + fina + "%");
				System.out.print("Do you want to add this course? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setCourseGrading(atten, quiz, project, mid, fina);
					System.out.println("Change Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "7":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}

	}

	private static void AUDinstructor() {
		while (true) {
			System.out.println("\n[Instructor : What do you want to do?]");
			System.out.println("[1] Add Instructor");
			System.out.println("[2] Update Instructor");
			System.out.println("[3] Delete Instructor");
			System.out.println("[4] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				while (true) {
					String nameF, nameL, gender, temp;
					int age;
					System.out.print("\nEnter First Name: ");
					nameF = a.next();
					System.out.print("Enter Last Name: ");
					nameL = a.next();
					System.out.print("Enter Gender(f/m): ");
					temp = a.next();
					if (temp.equals("f") || temp.equals("F")) {
						gender = "f";
					} else if (temp.equals("m") || temp.equals("M")) {
						gender = "m";
					} else {
						System.out.println("Error: Wrong input please try again");
						continue;
					}
					System.out.print("Enter Age: ");
					if (a.hasNextInt()) {
						age = a.nextInt();
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (age < 0) {
						System.out.println("Invalid age");
						continue;
					}
					System.out.println(
							"Name: " + nameF + " " + nameL + " | Gender: " + gender.charAt(0) + " | Age: " + age);
					System.out.print("Do you want to add this instructor? (If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						instructorList.add(new Instructor(nameF, nameL, age, gender.charAt(0)));
						System.out.println("Add Complete!!!");
					} else {
						System.out.println("Cancel Complete!!!");
					}
					System.out.print("Do you want to add another instructor?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "2":
				while (true) {
					int number;
					System.out.println("\n[Instructor List]");
					for (int i = 0; i < instructorList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + instructorList.get(i).getFirstName() + " "
								+ instructorList.get(i).getLastName());
					}
					System.out.println("[" + (instructorList.size() + 1) + "] Back");
					System.out.print("Please enter instructor number you want to edit: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == instructorList.size()) {
						break;
					}
					if (number >= instructorList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					editInstructor(instructorList.get(number));
				}
				break;
			case "3":
				while (true) {
					int number;
					String temp;
					System.out.println("\n[Instructor List]");
					for (int i = 0; i < instructorList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + instructorList.get(i).getFirstName() + " "
								+ instructorList.get(i).getLastName());
					}
					System.out.println("[" + (instructorList.size() + 1) + "] Back");
					System.out.print("Please enter instructor number you want to delete: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == instructorList.size()) {
						break;
					}
					if (number >= instructorList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					System.out.print("Are you sure you want to delete: " + instructorList.get(number).getFirstName()
							+ " " + instructorList.get(number).getLastName() + "(If yes enter \"y\"):");
					temp = a.next();
					if (!(temp.equals("y") || temp.equals("Y"))) {
						System.out.println("Cancel Complete");
						continue;
					}
					instructorList.remove(instructorList.get(number));
					System.out.println("Delete Complete");
					System.out.print("Do you want to delete another instructor?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "4":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void editInstructor(Instructor instructor) {
		String in, temp = null;
		while (true) {
			System.out.println("\n[Instructor Update(" + instructor.getFirstName() + " " + instructor.getLastName()
					+ "): What do you want to edit?]");
			System.out.println("[1] First Name");
			System.out.println("[2] Last Name");
			System.out.println("[3] Gender");
			System.out.println("[4] Age");
			System.out.println("[5] Add Teached Course");
			System.out.println("[6] Remove From Course");
			System.out.println("[7] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				System.out.println("Old: " + instructor.getFirstName());
				System.out.print("Enter First Name: ");
				in = a.next();
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					instructor.setFirstName(in);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "2":
				System.out.println("Old: " + instructor.getLastName());
				System.out.print("Enter Last Name: ");
				in = a.next();
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					instructor.setLastName(in);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "3":
				String gender;
				System.out.println("Old: " + instructor.getGender());
				System.out.print("Enter Gender(f/m): ");
				in = a.next();
				if (temp.equals("f") || temp.equals("F")) {
					gender = "f";
				} else if (temp.equals("m") || temp.equals("M")) {
					gender = "m";
				} else {
					System.out.println("Error: Wrong input please try again");
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					instructor.setGender(gender.charAt(0));
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "4":
				int age;
				System.out.println("Old: " + instructor.getAge());
				if (a.hasNextInt()) {
					age = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					break;
				}
				if (age < 0) {
					System.out.println("Invalid age");
					break;
				}
				System.out.print("Do you want to change? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					instructor.setAge(age);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "5":
				while (true) {
					int number;
					System.out.println("\n[Course List]");
					for (int i = 0; i < courseList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + courseList.get(i).getCourseCode() + " - "
								+ courseList.get(i).getCourseName());
					}
					System.out.println("[" + (courseList.size() + 1) + "] Back");
					System.out.print("Please enter course number you want to add this instructor: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == courseList.size()) {
						break;
					}
					if (number >= courseList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					System.out.print(
							"Are you sure you want to add this instructor to " + courseList.get(number).getCourseCode()
									+ " - " + courseList.get(number).getCourseName() + "(If yes enter \"y\"):");
					temp = a.next();
					if (!(temp.equals("y") || temp.equals("Y"))) {
						System.out.println("Cancel Complete");
						continue;
					}
					boolean already_teach = false;
					for (RegCourse i : instructor.getTeaching()) {
						if (i.equals(courseList.get(number))) {
							already_teach = true;
							continue;
						}
					}
					if (already_teach) {
						System.out.println("Instructor " + instructor.getFirstName() + " " + instructor.getLastName()
								+ " has already taught " + courseList.get(number).getCourseCode() + " - "
								+ courseList.get(number).getCourseName());
						continue;
					}
					instructor.setTeaching(courseList.get(number));
					System.out.println("Add Complete");
					System.out.print("Do you want to add this instructor to another course?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "6":
				while (true) {
					int number;
					System.out.println("\n[Teached Course List]");
					for (int i = 0; i < instructor.getTeaching().size(); i++) {
						System.out.println("[" + (i + 1) + "] " + instructor.getTeaching().get(i).getCourseCode()
								+ " - " + instructor.getTeaching().get(i).getCourseName());
					}
					System.out.println("[" + (instructor.getTeaching().size() + 1) + "] Back");
					System.out.print("Please enter course number you want to remove this instructor from: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == instructor.getTeaching().size()) {
						break;
					}
					if (number >= instructor.getTeaching().size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					System.out.print("Are you sure you want to remove this instructor from "
							+ instructor.getTeaching().get(number).getCourseCode() + " - "
							+ instructor.getTeaching().get(number).getCourseName() + "(If yes enter \"y\"):");
					temp = a.next();
					if (!(temp.equals("y") || temp.equals("Y"))) {
						System.out.println("Cancel Complete");
						continue;
					}
					instructor.getTeaching().remove(instructor.getTeaching().get(number));
					System.out.println("Remove Complete");
					System.out
							.print("Do you want to remove this instructor from another course?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "7":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void AUDstudent() {
		while (true) {
			System.out.println("\n[Student : What do you want to do?]");
			System.out.println("[1] Add Student");
			System.out.println("[2] Update Student");
			System.out.println("[3] Delete Student");
			System.out.println("[4] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				while (true) {
					String nameF, nameL, gender, temp;
					int age;
					System.out.print("\nEnter First Name: ");
					nameF = a.next();
					System.out.print("Enter Last Name: ");
					nameL = a.next();
					System.out.print("Enter Gender(f/m): ");
					temp = a.next();
					if (temp.equals("f") || temp.equals("F")) {
						gender = "f";
					} else if (temp.equals("m") || temp.equals("M")) {
						gender = "m";
					} else {
						System.out.println("Error: Wrong input please try again");
						continue;
					}
					System.out.print("Enter Age: ");
					if (a.hasNextInt()) {
						age = a.nextInt();
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (age < 0) {
						System.out.println("Invalid age");
						continue;
					}
					System.out.println(
							"Name: " + nameF + " " + nameL + " | Gender: " + gender.charAt(0) + " | Age: " + age);
					System.out.print("Do you want to add this student? (If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						studentList.add(new Student(nameF, nameL, age, gender.charAt(0)));
						System.out.println("Add Complete!!!");
					} else {
						System.out.println("Cancel Complete!!!");
					}
					System.out.print("Do you want to add another student?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "2":
				while (true) {
					int number;
					System.out.println("\n[Student List]");
					for (int i = 0; i < studentList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + studentList.get(i).getFirstName() + " "
								+ studentList.get(i).getLastName());
					}
					System.out.println("[" + (studentList.size() + 1) + "] Back");
					System.out.print("Please enter student number you want to edit: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == studentList.size()) {
						break;
					}
					if (number >= studentList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					editStudent(studentList.get(number));
				}
				break;
			case "3":
				while (true) {
					int number;
					String temp;
					System.out.println("\n[Student List]");
					for (int i = 0; i < studentList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + studentList.get(i).getFirstName() + " "
								+ studentList.get(i).getLastName());
					}
					System.out.println("[" + (studentList.size() + 1) + "] Back");
					System.out.print("Please enter student number you want to delete: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == studentList.size()) {
						break;
					}
					if (number >= studentList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					System.out.print("Are you sure you want to delete: " + studentList.get(number).getFirstName() + " "
							+ studentList.get(number).getLastName() + "(If yes enter \"y\"):");
					temp = a.next();
					if (!(temp.equals("y") || temp.equals("Y"))) {
						System.out.println("Cancel Complete");
						continue;
					}
					for (RegCourse i : studentList.get(number).getRegisteredCourses()) {
						i.removeStudent(studentList.get(number));
					}
					studentList.remove(studentList.get(number));
					System.out.println("Delete Complete");
					System.out.print("Do you want to delete another instructor?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "4":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void editStudent(Student student) {
		String in, temp = null;
		while (true) {
			System.out.println("\n[Instructor Update(" + student.getFirstName() + " " + student.getLastName()
					+ "): What do you want to edit?]");
			System.out.println("[1] First Name");
			System.out.println("[2] Last Name");
			System.out.println("[3] Gender");
			System.out.println("[4] Age");
			System.out.println("[5] Add Registered Course");
			System.out.println("[6] Remove From Course");
			System.out.println("[7] Back");
			System.out.print("Input: ");
			switch (a.next()) {
			case "1":
				System.out.println("Old: " + student.getFirstName());
				System.out.print("Enter First Name: ");
				in = a.next();
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					student.setFirstName(in);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "2":
				System.out.println("Old: " + student.getLastName());
				System.out.print("Enter Last Name: ");
				in = a.next();
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					student.setLastName(in);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "3":
				String gender;
				System.out.println("Old: " + student.getGender());
				System.out.print("Enter Gender(f/m): ");
				in = a.next();
				if (temp.equals("f") || temp.equals("F")) {
					gender = "f";
				} else if (temp.equals("m") || temp.equals("M")) {
					gender = "m";
				} else {
					System.out.println("Error: Wrong input please try again");
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					student.setGender(gender.charAt(0));
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "4":
				int age;
				System.out.println("Old: " + student.getAge());
				if (a.hasNextInt()) {
					age = a.nextInt();
				} else {
					System.out.println("Please enter only integer!!!\nPlease try again");
					a.next();
					break;
				}
				if (age < 0) {
					System.out.println("Invalid age");
					break;
				}
				System.out.print("Do you want to change? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					student.setAge(age);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "5":
				while (true) {
					int number;
					System.out.println("\n[Course List]");
					for (int i = 0; i < courseList.size(); i++) {
						System.out.println("[" + (i + 1) + "] " + courseList.get(i).getCourseCode() + " - "
								+ courseList.get(i).getCourseName());
					}
					System.out.println("[" + (courseList.size() + 1) + "] Back");
					System.out.print("Please enter course number you want to add this student to: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == courseList.size()) {
						break;
					}
					if (number >= courseList.size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					System.out.print(
							"Are you sure you want to add this student to " + courseList.get(number).getCourseCode()
									+ " - " + courseList.get(number).getCourseName() + "(If yes enter \"y\"):");
					temp = a.next();
					if (!(temp.equals("y") || temp.equals("Y"))) {
						System.out.println("Cancel Complete");
						continue;
					}
					boolean already_regis = false;
					for (RegCourse i : student.getRegisteredCourses()) {
						if (i.equals(courseList.get(number))) {
							already_regis = true;
							continue;
						}
					}
					if (already_regis) {
						System.out.println("student " + student.getFirstName() + " " + student.getLastName()
								+ " has already registered " + courseList.get(number).getCourseCode() + " - "
								+ courseList.get(number).getCourseName());
						continue;
					}
					student.RegisterCourse((courseList.get(number)));
					System.out.println("Add Complete");
					System.out.print("Do you want to add this student to another course?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "6":
				while (true) {
					int number;
					System.out.println("\n[Registered Course List]");
					for (int i = 0; i < student.getRegisteredCourses().size(); i++) {
						System.out.println("[" + (i + 1) + "] " + student.getRegisteredCourses().get(i).getCourseCode()
								+ " - " + student.getRegisteredCourses().get(i).getCourseName());
					}
					System.out.println("[" + (student.getRegisteredCourses().size() + 1) + "] Back");
					System.out.print("Please enter course number you want to remove this student from: ");
					if (a.hasNextInt()) {
						number = a.nextInt() - 1;
					} else {
						System.out.println("Please enter only integer!!!\nPlease try again");
						a.next();
						continue;
					}
					if (number == student.getRegisteredCourses().size()) {
						break;
					}
					if (number >= student.getRegisteredCourses().size() || number < 0) {
						System.out.println("Invalid input!!!\nPlease try again");
						continue;
					}
					System.out.print("Are you sure you want to remove this student from "
							+ student.getRegisteredCourses().get(number).getCourseCode() + " - "
							+ student.getRegisteredCourses().get(number).getCourseName() + "(If yes enter \"y\"):");
					temp = a.next();
					if (!(temp.equals("y") || temp.equals("Y"))) {
						System.out.println("Cancel Complete");
						continue;
					}
					student.removeFromCourse((courseList.get(number)));
					System.out.println("Remove Complete");
					System.out.print("Do you want to remove this student from another course?(If yes enter \"y\"): ");
					temp = a.next();
					if (temp.equals("y") || temp.equals("Y")) {
						continue;
					} else {
						break;
					}
				}
				break;
			case "7":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void addScoreChooseStudent() {
		while (true) {
			int number;
			System.out.println("\n[Student List]");
			for (int i = 0; i < studentList.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + studentList.get(i).getFirstName() + " "
						+ studentList.get(i).getLastName());
			}
			System.out.println("[" + (studentList.size() + 1) + "] Back");
			System.out.print("Please enter student number you want to add score: ");
			if (a.hasNextInt()) {
				number = a.nextInt() - 1;
			} else {
				System.out.println("Please enter only integer!!!\nPlease try again");
				a.next();
				continue;
			}
			if (number == studentList.size()) {
				break;
			}
			if (number >= studentList.size() || number < 0) {
				System.out.println("Invalid input!!!\nPlease try again");
				continue;
			}
			addScoreChooseCourse(studentList.get(number));
		}
	}

	private static void addScoreChooseCourse(Student student) {
		while (true) {
			int number;
			System.out.println("\n[Registered Course List]");
			for (int i = 0; i < student.getRegisteredCourses().size(); i++) {
				System.out.println("[" + (i + 1) + "] " + student.getRegisteredCourses().get(i).getCourseCode() + " - "
						+ student.getRegisteredCourses().get(i).getCourseName());
			}
			System.out.println("[" + (student.getRegisteredCourses().size() + 1) + "] Back");
			System.out.print("Please enter course number you want add score for this student: ");
			if (a.hasNextInt()) {
				number = a.nextInt() - 1;
			} else {
				System.out.println("Please enter only integer!!!\nPlease try again");
				a.next();
				continue;
			}
			if (number == student.getRegisteredCourses().size()) {
				break;
			}
			if (number >= student.getRegisteredCourses().size() || number < 0) {
				System.out.println("Invalid input!!!\nPlease try again");
				continue;
			}
			addScore(student, courseList.get(number));
		}
	}

	private static void addScore(Student student, RegCourse course) {
		RegCourse target = null;
		for (RegCourse i : course.getStudentScore()) {
			if (i.getScoreOwner().getFirstName().equals(student.getFirstName())
					&& i.getScoreOwner().getLastName().equals(student.getLastName())) {
				target = i;
				break;
			}
		}
		while (true) {
			System.out.println("\n[Add Score(" + course.getCourseCode() + " - " + course.getCourseName() + ", "
					+ student.getFirstName() + " " + student.getLastName()
					+ "): Which criteria do you want to change?]");
			System.out.println("[1] Attendance Score");
			System.out.println("[2] Quiz Score");
			System.out.println("[3] Project Score");
			System.out.println("[4] Midterm Score");
			System.out.println("[5] Final Score");
			System.out.println("[6] Back");
			System.out.print("Input: ");
			double num;
			String temp;
			switch (a.next()) {
			case "1":
				if (course.getAttendancePercent() == 0) {
					System.out.println("This course doesn't have attendace score");
					break;
				}
				System.out.println("Old: " + target.getAttendance() + " from " + course.getFull_score_attendance());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				if (num > course.getFull_score_attendance() || num < 0) {
					System.out.println("Invalid score\nPlease try again");
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					target.setAttendance(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "2":
				if (course.getQuizPercent() == 0) {
					System.out.println("This course doesn't have quiz score");
					break;
				}
				System.out.println("Old: " + target.getQuiz() + " from " + course.getFull_score_quiz());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				if (num > course.getFull_score_quiz() || num < 0) {
					System.out.println("Invalid score\nPlease try again");
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					target.setQuiz(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "3":
				if (course.getProjPercent() == 0) {
					System.out.println("This course doesn't have project score");
					break;
				}
				System.out.println("Old: " + target.getProjects() + " from " + course.getFull_score_projects());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				if (num > course.getFull_score_projects() || num < 0) {
					System.out.println("Invalid score\nPlease try again");
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					target.setProjects(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "4":
				if (course.getMidtermPercent() == 0) {
					System.out.println("This course doesn't have midterm examination score");
					break;
				}
				System.out.println("Old: " + target.getMidScore() + " from " + course.getFull_score_midScore());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				if (num > course.getFull_score_midScore() || num < 0) {
					System.out.println("Invalid score\nPlease try again");
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					course.setMidScore(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "5":
				if (course.getFinalPercent() == 0) {
					System.out.println("This course doesn't have final examination score");
					break;
				}
				System.out.println("Old: " + target.getFinalScore() + " from " + course.getFull_score_finalScore());
				System.out.print("Enter number: ");
				if (a.hasNextDouble()) {
					num = a.nextDouble();
				} else {
					System.out.println("Please enter only floating point number!!!\nPlease try again");
					a.next();
					break;
				}
				if (num > course.getFull_score_finalScore() || num < 0) {
					System.out.println("Invalid score\nPlease try again");
					break;
				}
				System.out.print("Are you sure? (If yes enter \"y\"): ");
				temp = a.next();
				if (temp.equals("y") || temp.equals("Y")) {
					target.setFinalScore(num);
					System.out.println("Set Complete!!!");
				} else {
					System.out.println("Cancel Complete!!!");
				}
				break;
			case "6":
				return;
			default:
				System.out.println("Wrong input please try again");
			}
		}
	}

	private static void information() {

	}

}
