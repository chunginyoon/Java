package test;
import java.util.Scanner;

class GenericBox<T> {
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public void toS(T value) {
		
		//Number의 기능을 사용 가능
	}
	
	<T> void method(T param) { //여기 안에서 메소드로도 사용 가능
		System.out.println(param.getClass().getSimpleName());
	}
	
}

class GenericMethod{
	<T> void method(T param) {
		System.out.println(param.getClass().getSimpleName());
	}
}


public class Test2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input=sc.next();
		
		//박스에 문자열 저장
		GenericBox<String> box = new GenericBox<String>(); //new GenericBox<>로 사용 가능
		box.setValue(input);
		
		
		//다른 코드를 돌리고 왔다고 가정
		//............
		
		
		
		//추후, 박스에 담긴 값을 꺼내서 사용
		Object obj = box.getValue();
		
		
		//null 및 타입 체크
		if(obj!=null && obj instanceof String) {
			String str = (String) obj;
			System.out.println(str.charAt(2));
		}
	}
}
