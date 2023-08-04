package objarray;

import java.util.Arrays;

public class EmployeeManager {
	//사원 목록 정보 저장
	int size = 0; //관리중인 사원 수
	final int MAX_SIZE = 100;
	Employee[] employees = new Employee [MAX_SIZE]; //null로 자동 초기화
	
	
	//사원 정보 등록
	public void regist(Employee employee) { //매개변수로 객체가 담긴 주소값(참조)을 사용한다
		
		//1. 빈자리 탐색
		for(int i = 0; i<employees.length; i++)
			if(employees[i] == null) {
				//2. 해당 인덱스에 주소값 저장
				employees[i] = employee; //주소값 저장
				break;
			}
		
		
	}
	
	//사원 전체 목록 반환
	public Employee[] getEmployeeList() {//주소값 담긴 배열로 반환
		
		//1.관리 중인 사원의 숫자 체크
		int size = 0; //관리중인 사원의 숫자 체크
		for(int i = 0; i<employees.length; i++)
			if(employees[i] ==null) {
				size = i;
				break;
			}
		
		//2. 관리중인 사원 배열 생성하여 반환
		return Arrays.copyOf(employees, size); //사원 수 들어있는 만큼만 배열 복사후 반환
	}
	
	
	//사번을 통한 사원 정보 조회
	public Employee searchByEn(/*사번*/String en) { //사원 정보 하나만 반환해서 주소값 1개만 반환
		
		//사번이 일치하는 사원 정보 반환
		for(int i = 0; i<employees.length; i++) {
			//사원 정보 일치여부 판단
			//더이상 탐색할 사원이 없으므로 탐색 종료
			if(employees[i] == null) break;
			if(en.equals(employees[i].en)) { //매개변수 en 이랑 class 안의 en 비교
				
				//사원 정보 반환
				return employees[i]; //주소값
			}
		}
		//일치하는 사원을 찾지 못한 경우 null 반환
		return null;
	}
	
	//사번을 통한 사원 정보 삭제
	public void deleteByEn(/*사번*/ String en) {
		//1. 삭제될 사번이 저장된 인덱스 탐색
		int deleteIdx = -1;
		for(int i = 0; i<employees.length; i++) {
			//사원 정보 일치여부 판단
			//더이상 탐색할 사원이 없으므로 탐색 종료
			if(employees[i] == null) break;
			
			if(en.equals(employees[i].en)) {
				deleteIdx = i;
				break;
				}
			}
		
		//2. 마지막 사원이 저장된 인덱스 탐색
		int lastIdx = -1;
		for(int i = 0; i<employees.length; i++) {
			if(employees[i] ==null) {
				lastIdx = i-1;
				break;
			}
		}
		
		//3. 2번 인덱스의 참조를 1번 인덱스의 요소에 갱신
		employees[deleteIdx] = employees[lastIdx];
		
		
		//4. 2번 인덱스의 요소를 null 처리
		employees[lastIdx] = null;
	}
}