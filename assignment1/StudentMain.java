package jdbcpractice;

import java.util.Scanner;

public class StudentMain {

	public static void getAll() {
		JdbcDao<Student, Integer> students = new StudentDao();
		students.getAll().stream().forEach(std -> System.out.println(std));
		
	}

	public static void getOne(int rollNO) {
		JdbcDao<Student, Integer> students = new StudentDao();
		if(students.getone(rollNO)!=null)
			System.out.println(students.getone(rollNO)); 
		else
			System.out.println("Student is not found on roll no "+rollNO);

	}

	public static void addOne(Student std) {

		JdbcDao<Student, Integer> students = new StudentDao();
		students.add(std);

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int ch;
		while (flag) {
			System.out.println("------Menu-------");
			System.out.println("1.for show all students. \n2.for get student by rollNo \n3.to add student \n0.for exit");
			ch = sc.nextInt();
			switch (ch) {
			case 0: {
				flag = false;
				break;
			}
			case 1: {
				getAll();
				break;
			}
			case 2: {
				System.out.println("enter rollno");
				getOne(sc.nextInt());
				break;
			}
			case 3: {
				System.out.println("enter rollno, name, city");
				addOne(new Student(sc.nextInt(), sc.next(), sc.next()));
				break;
			}
			default:
				System.err.println("Enter valid choice");
			}
		}
		sc.close();
	};

}
