package parser;

import java.util.List;

public class WeatherMain {

	public static void main(String[] args) {
		
		//SAX파서 사용
		WeatherSaxParser parser = new WeatherSaxParser();
		List<Weather> list = parser.getWeather();
		
		list.stream().forEach(System.out::println);
		
		//Dom파서 사용
		WeatherDOMParser parser2 = new WeatherDOMParser();
		
		List<Weather> list2 = parser2.getWeather();
		
		list2.stream().forEach(System.out::println);

	}

}
