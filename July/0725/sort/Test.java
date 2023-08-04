package sort;

import java.util.Arrays;
import java.util.Comparator;

public class Test {
	public static void main(String[] args) {
		
        Student[] students = {
                new Student(3, "학생1", 190),
                new Student(10, "학생2", 150),
                new Student(1, "학생3", 160),
                new Student(5, "학생4", 160)
        };
        
        Arrays.sort(students); //student 클래스에서 기준을 만들어서 정렬 가능
        
        System.out.println("=====기본 정렬 기준 정렬=====");
        for(Student s: students)
        	System.out.println(s);
        
        System.out.println("=====이름 기준 사전 오름차순 정렬=====");
        Comparator<Student> comparator = new NameComparator(); //이 기준을 계속해서 사용할 거라면 따로 클래스를 구현한다
        													   //그러나 계속해서 바뀔 기준이라면 아래와 같은 익명 객체를 만든다
        //비교자를 통한 정렬기준 부여
        Arrays.sort(students, comparator);
        for(Student s: students)
        	System.out.println(s);
        
        System.out.println("=====학점 기준 내림차순 정렬=====");
        Comparator<Student> comparator2 = new Comparator<Student>() { //타입이 없는 익명 객체를 만드는 것, 이너 타입 클래스
        															  //comparator로 참조는 할 수 있는

			@Override
			public int compare(Student o1, Student o2) {
				// 학점 기준 내림차순
				//return o1.getScore()-o2.getScore();
				return Integer.compare(o2.getScore(), o1.getScore());
			}
        	
        };
       
        Arrays.sort(students, comparator2);
        for(Student s: students)
        	System.out.println(s);
        
        
        System.out.println("=====학점 기준 오름차순=====");
        //Comparator<Student> comparator3 = (o1, o2) -> o1.getScore()-o2.getScore();
       //Arrays.sort(students, comparator3);
        Arrays.sort(students, (o1, o2) -> o1.getScore()-o2.getScore());
        for(Student s: students)
        	System.out.println(s);
        
        
        String[] strs = {"4", "2", "10", "1"};
        Arrays.sort(strs);
        for(Student s: students)
        	System.out.println(s);
	}
}
