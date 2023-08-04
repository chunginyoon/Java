package example;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListEmployee {
	static List<Employee> employees = Arrays.asList(new Employee("5","설한결","no.1", 5000),
													new Employee("2","남궁소라","no.2", 3000),
													new Employee("3","오버들","no.3", 1000),
													new Employee("1","탁아람","no.3", 1000),
													new Employee("7","권철", "no.4", 500),
													new Employee("4","유바다","no.4", 500),
													new Employee("6","풍우람","no.4", 500));
	
	public static void main(String[] args) {
		
		System.out.println("=====사번 기준 오름차순 정렬=====");
		Collections.sort(employees);
		for(Employee e: employees)
			System.out.println(e);
		
		
		//직책 오름차순 정렬
		System.out.println("=====직책 기준 오름차순 정렬=====");
		Comparator<Employee> comparator = new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				
				if(o1.getLevel().compareTo(o2.getLevel()) == 0)
					return (o1.getName().compareTo(o2.getName()));
				
				else
					return (o1.getLevel().compareTo(o2.getLevel()));
			}
		};
		
		Collections.sort(employees, comparator);
		for(Employee e: employees)
			System.out.println(e);
	}
}
