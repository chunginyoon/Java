package objarray;

public class EmployeeTest {
	public static void main(String[] args) {
		
		EmployeeManager em = new EmployeeManager();
		
		em.regist(new Employee("1", "사원1", "회장"));
		em.regist(new Employee("2", "사원2", "부회장"));
		em.regist(new Employee("3", "사원3", "사장"));
		em.regist(new Employee("4", "사원4", "전무"));
		em.regist(new Employee("5", "사원5", "이사"));
		//전체 조회 테스트
		Employee[] list = em.getEmployeeList();
		for(Employee e : list)
			System.out.println(e);
		
		//사원 정보 조회 테스트
		System.out.println("사원 정보 조회 테스트");
		Employee emp = em.searchByEn("3");
		System.out.println(emp);
		Employee emp2 = em.searchByEn("6");
		System.out.println(emp2);
		
		//삭제 테스트
		System.out.println("=======삭제 테스트========");
		em.deleteByEn("2");
		Employee[] list2 = em.getEmployeeList();
		for(Employee e : list2)
			System.out.println(e);
	}
}
