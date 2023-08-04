package test;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Test {

	public static void main(String[] args) throws IOException{
		Book book = new Book();
		book.setIsbn("1234");
		
		System.out.println("원본 객체: "+book);
		try {
			saveObj(book);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			Object obj = loadObj("obj.dat");
			System.out.println("불러온 객체: "+obj);// +에서 toString을 해서 동적 바인딩에 의해 Book 문자열을 반환한다
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 객체를 파일로 저장
	 * @param Object obj-저장될 객체
	 * @throws IOException 
	 */
	private static void saveObj(Object obj) throws IOException {
		//FileOutputStream fos = new FileOutputStream("obj.dat");
		//ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		//속도 up
		FileOutputStream fos = new FileOutputStream("obj.dat"); //파일을 만든다?연다?
		BufferedOutputStream bos = new BufferedOutputStream(fos); //속도 up
		ObjectOutputStream oos = new ObjectOutputStream(bos); //객체를 직렬화된 데이터로 바꿔준다
		
		
		oos.writeObject(obj); //객체를 받아서 직렬화된 데이터로 바꾼 후 파일에 쓴다
							 //여기까지 쓰기
		
		//파일 닫을 때는 여는 순서 반대로
		oos.close();
		bos.close();
		fos.close();
	}
	
	
	/**
	 * 파일 읽어서 객체 불러오기
	 * @param Object obj-저장될 객체
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	
	private static Object loadObj(String path) throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(path); //파일에서 문자를 읽는다
		ObjectInputStream ois = new ObjectInputStream(fis); //읽어온 문자(데이터)를 객체로 변환한다
		
		Object obj = ois.readObject(); //파일에서 읽어온 문자를 객체로 변환하여 저장
		
		//닫을 때는 쓰는 순서 반대로
		ois.close();
		fis.close();
		return obj;
	}
}
