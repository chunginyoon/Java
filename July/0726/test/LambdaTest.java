package test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaTest {
	
	static Book[] books= new Book[100];
	
	public static void forEach(Consumer<Book> c) {
		for(int i = 0; i<books.length; i++)
			c.accept(books[i]);
	}

	public static void main(String[] args) {
		List<String> list = Arrays.asList("1", "2", "3", "4");
		
		list.forEach((o)->System.out.println("출력:"+o));
		//forEach((o) ->System.out.println(o));
		
		list.stream()
		.map((o) -> Integer.parseInt(o))
		.forEach(o -> System.out.println(o+o.getClass().getSimpleName()));
		
		list.stream()
		.map((o) -> Integer.parseInt(o))
		.filter(o ->{
			return true;
		})
		.forEach(System.out::println);
		
		list.stream()
		.map((o) -> Integer.parseInt(o))
		.filter(o ->o%2==0)
		.forEach(System.out::println);
	}

}
