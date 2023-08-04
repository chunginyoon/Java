package objarray;

public class Employee {
	String en; //사번
	String name; //이름
	String level; //직급
	
	//생성자
	public Employee(String en, String name, String level) {
		//super(); //부모 클래스(Object)의 생성자, 없어도 자동으로 존재
		this.en = en;
		this.name = name;
		this.level = level;
	}
	
	@Override
	public String toString() {
		return "사번:"+en+", 이름:"+name+", 직급:"+level;
	}
	
	//재정의
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Employee) {
			Employee emp = (Employee) obj;
			
			if(this.en.equals(obj.en))
		}
		return super.equals(obj);
	}
	
}
