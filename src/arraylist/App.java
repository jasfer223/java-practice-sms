package arraylist;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
	private static ArrayList<Student> students = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {	
		while(true) {
			try {
				System.out.println("STUDENT MANAGEMENT SYSTEM");
				System.out.println("[ 1 ] Add");
				System.out.println("[ 2 ] View");
				System.out.println("[ 3 ] Update");
				System.out.println("[ 4 ] Delete");
				System.out.println("[ 5 ] Sort (Asc)");
				System.out.println("[ 6 ] Sort (Desc)");
				System.out.println("[ 0 ] Exit");
				System.out.print("Enter selected action: ");
				int act = sc.nextInt();
				sc.nextLine();

				switch (act) {
				case 1:
					addStudent();
					break;
				case 2:
					viewStudents();
					break;
				case 3:
					updateStudent();
					break;
				case 4:
					deleteStudent();
					break;
				case 5:
					sortAsc();
					break;
				case 6:
					sortDesc();
					break;
				case 0:
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

	private static void addStudent() {	
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
            System.out.println("Student added successfully!");
        } catch (Exception e) {
			System.out.println("Invalid input. Please enter valid data.");
			sc.nextLine();
        }
	}
	
	private static void viewStudents() {
		if (students.isEmpty()) {
			System.out.println("No students available.");
		} else {
			System.out.println("LIST OF STUDENTS");
			for (Student student : students) {
				System.out.println(student.toString());
			}
		}

	}
	
	private static void updateStudent() {
		try {
			System.out.print("Enter Student ID to update: ");
			int id = sc.nextInt();
			sc.nextLine();

			for (Student student : students) {
				if (student.getIdNo() == id) {
					System.out.print("Enter new name: ");
					String newName = sc.nextLine();
					System.out.print("Enter new grade: ");
					int newGrade = sc.nextInt();
					sc.nextLine();

					student.setName(newName);
					student.setGrade(newGrade);
					System.out.println("Student updated successfully!");
					return;
				}
			}
			System.out.println("Student not found.");

		} catch (Exception e) {
			System.out.println("Invalid input. Please enter valid data.");
			sc.nextLine();
		}

	}

	private static void deleteStudent() {
		System.out.print("Enter Student ID to delete: ");
		int id = sc.nextInt();
		sc.nextLine(); 

		for (Student student : students) {
			if (student.getIdNo() == id) {
				students.remove(student);
				System.out.println("Student deleted successfully!");
				return;
			}
		}
		System.out.println("Student not found.");
	}

	private static void sortAsc() {
		students.sort(Comparator.comparing(Student::getIdNo));

		System.out.println("Students sorted in ascending order by ID No.");
	}
	
	private static void sortDesc() {
		students.sort(Comparator.comparing(Student::getIdNo).reversed());
		System.out.println("Students sorted in descending order by ID No.");
	}


}
