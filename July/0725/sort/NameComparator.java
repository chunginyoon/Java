package sort;

import java.util.Comparator;

public class NameComparator implements Comparator<Student> { //이 기준을 계속해서 사용할 거라면 따로 클래스를 구현한다
	
	@Override
	public int compare(Student o1, Student o2) {
		//name 기준으로 오름차순 정렬 o1.compareTo(o2);
		//name 기준으로 오름차순 정렬 o1.compareTo(o2)*-1 또는 o2.compareTo(o1)
		return o1.getName().compareTo(o2.getName()); //외부에서 접근하므로
	}
}
