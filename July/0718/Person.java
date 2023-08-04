package Test;

public class Person {
	String name;
	int age;
	boolean isHungry;
	
	void eat() {
		System.out.println("냠냠.");
	}
	
	void work() {
		System.out.println("열심히");
		isHungry = true;
	}
}
