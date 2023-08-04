package objarray;

import java.util.Arrays;

public class EmployeeManager2 {
	//사원 목록 정보 저장
	int size = 0; //관리중인 사원 수
	final int MAX_SIZE = 100;
	Employee[] employees = new Employee [MAX_SIZE];
	
	
	//사원 정보 등록
	public void regist(Employee employee) {
		//배열의 요소에 참조 할당 후 size 증가
		employees[size++] = employee;
	}
	
	
	//사원 전체 목록 반환
	public Employee[] getEmployeeList() {
		
		//사원 수만큼 배열 반환
		return Arrays.copyOf(employees, size);
	}
	
	
	//사번을 통한 사원 정보 조회
	public Employee searchByEn(/*사번*/String en) {
		
		//사번이 일치하는 사원 정보 반환
		for(int i = 0; i<size; i++) {
			//사원 정보 일치여부 판단
			//더이상 탐색할 사원이 없으므로 탐색 종료
			if(en.equals(employees[i].en)) {
				
				//사원 정보 반환
				return employees[i];
			}
		}
		//일치하는 사원을 찾지 못한 경우 null 반환
		return null;
	}
	
	//사번을 통한 사원 정보 삭제
	public void deleteByEn(/*사번*/ String en) {
		//1. 삭제될 사번이 저장된 인덱스 탐색
		int deleteIdx = -1;
		for(int i = 0; i<size; i++) {
			//사원 정보 일치여부 판단
			//더이상 탐색할 사원이 없으므로 탐색 종료
			if(en.equals(employees[i].en)) {
				deleteIdx = i;
				break;
				}
			}
		
		//3. 마지막 인덱스의 참조를 삭제할 인덱스의 요소에 갱신
		employees[deleteIdx] = employees[size - 1];
		
		
		//4. 마지막 인덱스의 요소를 null 처리
		employees[size - 1] = null;
		size--;
	}
}