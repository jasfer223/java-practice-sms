package arraylist;
public class Student implements Comparable<Student> {
	int idNo;
	String name;
	int grade;
	
	public Student(int idNo, String name, int grade) {
		super();
		this.idNo = idNo;
		this.name = name;
		this.grade = grade;
	}
	
	public String toString() {
		return "ID No : " + idNo + "\n" +
				"Name  : " + name + "\n" +
				"Grade : " + grade + "\n";
	}
	
	public int getIdNo() {
		return idNo;
	}
	
	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public int compareTo(Student other) {
		return Integer.compare(this.idNo, other.idNo);
	}
}
