package UsingTxtAsStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {	
		Scanner sc = new Scanner(System.in);
		ArrayList<Student> students = new ArrayList<Student>();
		File studentsTxt = new File("students.txt");
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		if(studentsTxt.isFile()) {
			ois = new ObjectInputStream(new FileInputStream(studentsTxt));
			students = (ArrayList<Student>)ois.readObject();
			ois.close();
		}
		
		while(true) {
			try {
				System.out.println("STUDENT MANAGEMENT SYSTEM");
				System.out.println("[ 1 ] Add");
				System.out.println("[ 2 ] View");
				System.out.println("[ 3 ] Update");
				System.out.println("[ 4 ] Delete");
				System.out.println("[ 5 ] Sort (Asc)");
				System.out.println("[ 6 ] Sort (Desc)");
				System.out.println("[ 7 ] Search");
				System.out.println("[ 0 ] Exit");
				System.out.print("Enter selected action: ");
				int act = sc.nextInt();
				sc.nextLine();

				switch (act) {
				case 1:
					try {
			            System.out.print("Enter Student ID: ");
			            int id = sc.nextInt();
			            sc.nextLine();
			            System.out.print("Enter Student Name: ");
			            String name = sc.nextLine();
			            System.out.print("Enter Student Grade: ");
			            int grade = sc.nextInt();
			            sc.nextLine();

			            students.add(new Student(id, name, grade));
			            
			        	oos = new ObjectOutputStream(new FileOutputStream(studentsTxt));
						oos.writeObject(students);
						oos.close();
			            
			            System.out.println("Student added successfully!");
			        } catch (Exception e) {
						System.out.println("Invalid input. Please enter valid data.");
						sc.nextLine();
			        }
					break;
				case 2:
					if (students.isEmpty()) {
						System.out.println("No students available.");
					} else {
						System.out.println("---------------");	
						System.out.println("LIST OF STUDENTS");
						for (Student student : students) {
							System.out.println(student.toString());
						}
						System.out.println("---------------");
					}
					break;
				case 3:
					try {
						if(!studentsTxt.isFile() || students.isEmpty()) {
							System.out.println("No students available.");
							break;
						}
						boolean found = false;
						System.out.print("Enter Student ID to update: ");
						int id = sc.nextInt();
						sc.nextLine();

						for (Student student : students) {
							if (student.getIdNo() == id) {
								found = true;
								System.out.print("Enter new name: ");
								String newName = sc.nextLine();
								System.out.print("Enter new grade: ");
								int newGrade = sc.nextInt();
								sc.nextLine();
								student.setName(newName);
								student.setGrade(newGrade);
								
								oos = new ObjectOutputStream(new FileOutputStream(studentsTxt));
								oos.writeObject(students);
								oos.close();
								System.out.println("Student updated successfully!");
								break;
							}
						}
						
						if(!found) {
							System.out.println("Student not found.");
						}

					} catch (Exception e) {
						System.out.println("Invalid input. Please enter valid data.");
						sc.nextLine();
					}

					break;
				case 4:
					try {
						if(!studentsTxt.isFile() || students.isEmpty()) {
							System.out.println("No students available.");
							break;
						}
						boolean found = false;
						System.out.print("Enter Student ID to delete: ");
						int id = sc.nextInt();
						sc.nextLine();

						for (Student student : students) {
							if (student.getIdNo() == id) {
								found = true;
								students.remove(student);
								
								oos = new ObjectOutputStream(new FileOutputStream(studentsTxt));
								oos.writeObject(students);
								oos.close();
								System.out.println("Student deleted successfully!");
								break;
							}
						}
						
						if(!found) {
							System.out.println("Student not found.");
						}

					} catch (Exception e) {
						System.out.println("Invalid input. Please enter valid data.");
						sc.nextLine();
					}

					break;
				case 5:
					if(!studentsTxt.isFile() || students.isEmpty()) {
						System.out.println("No students available.");
						break;
					}
					
					students.sort(Comparator.comparing(Student::getIdNo));
					oos = new ObjectOutputStream(new FileOutputStream(studentsTxt));
					oos.writeObject(students);
					oos.close();
					System.out.println("Students sorted in ascending order by ID No.");
					break;
				case 6:
					if(!studentsTxt.isFile() || students.isEmpty()) {
						System.out.println("No students available.");
						break;
					}
					
					students.sort(Comparator.comparing(Student::getIdNo).reversed());
					oos = new ObjectOutputStream(new FileOutputStream(studentsTxt));
					oos.writeObject(students);
					oos.close();
					System.out.println("Students sorted in descending order by ID No.");
					break;
				case 7:
					try {
						if(!studentsTxt.isFile() || students.isEmpty()) {
							System.out.println("No students available.");
							break;
						}
						
						System.out.print("Enter Student ID to search: ");
						int id1 = sc.nextInt();
						sc.nextLine();

						for (Student student : students) {
							if (student.getIdNo() == id1) {
								System.out.println("---------------");
								System.out.println(student.toString());
								System.out.println("---------------");
								break;
							}
						}
						System.out.println("Student not found.");

					} catch (Exception e) {
						System.out.println("Invalid input. Please enter valid data.");
						sc.nextLine();
					}
					break;
				case 0:
					sc.close();
					System.out.println("Exiting system...");
					System.exit(0);
				default:
					System.out.println("Invalid action, please try again.");
					sc.nextLine();
				}
			} catch (InputMismatchException  e) {
				System.out.println("Invalid action, please try again.");
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("Something went wrong, please try again.");
				sc.nextLine();
			}
		}
	}
}

