package sort;

public class Student implements Comparable<Student>{//비교할 수 있는 객체로 만들기
	
	private int no; //학번
	private String name; //이름
	private int score; //학점
	
	public Student(int no, String name, int score) {
		super();
		this.no = no;
		this.name = name;
		this.score = score;
	}
	
	@Override
	public int compareTo(Student o) { //논리적인 기준 설정
		//학번 기준 오름차순
		//return no - o.no;
		return Integer.compare(no, o.no)*-1; //왼쪽이 크면 1 반환
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return ""+no+" "+name+" "+score;
	}
	
}
