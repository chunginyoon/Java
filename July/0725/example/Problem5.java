package example;

import java.util.Arrays;
import java.util.Comparator;

public class Problem5 {

	public static void main(String[] args) {
		Employee[] emps = {
				new Employee("5","설한결","no.1", 5000),
				new Employee("2","남궁소라","no.2", 3000),
				new Employee("3","오버들","no.3", 1000),
				new Employee("1","탁아람","no.3", 1000),
				new Employee("7","권철", "no.4", 500),
				new Employee("4","유바다","no.4", 500),
				new Employee("6","풍우람","no.4", 500),
			};
		
		//#5 사번 기준 오름차순 정렬
		System.out.println("=====기본 기준 정렬=====");
		Arrays.sort(emps);
		for(Employee e: emps)
        	System.out.println(e);
		
		
		//#6 사번 기준 내림차순 정렬
		System.out.println("=====사번 기준 내림차순 정렬=====");
		Comparator<Employee> comparator = new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				return (o2.getEn().compareTo(o1.getEn()));
			}
		};
		Arrays.sort(emps, comparator);
		for(Employee e: emps)
			System.out.println(e);
		
		
		//#7 직책 기준 오름차순 정렬
		System.out.println("=====직책 기준 오름차순 정렬=====");
		Comparator<Employee> comparator2 = new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				
				return (o1.getLevel().compareTo(o2.getLevel()));
			}
			
		};
		Arrays.sort(emps, comparator2);
		for(Employee e: emps)
			System.out.println(e);
		
		
		//#8 월급 기준 내림차순 정렬
		System.out.println("=====월급 기준 내림차순 정렬=====");
		Comparator<Employee> comparator3 = new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o2.getSalary(), o1.getSalary());
			}
			
		};
		Arrays.sort(emps, comparator3);
		for(Employee e: emps)
			System.out.println(e);
		
	}

}
