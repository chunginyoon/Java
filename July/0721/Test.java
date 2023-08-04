package test;
import java.util.Scanner;

class Box {
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
}

public class Test {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String input=sc.next();
		
		//박스에 문자열 저장
		Box box = new Box();
		box.setValue(input);
		
		
		//다른 코드를 돌리고 왔다고 가정
		//............
		
		
		
		//추후, 박스에 담긴 값을 꺼내서 사용
		Object obj = box.getValue();
		
		
		//null 및 타입 체크
		if(obj!=null && obj instanceof String) {
			String str = (String) obj;
			System.out.println(str.)
		}
	}

}
