package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.k_xml_ui.parse.dto.BoxOffice;

public class WeatherSaxParser extends DefaultHandler {
	
	//날씨 데이터 담을 리스트
	private List<Weather> list = new ArrayList<>();
	
	Weather weather;	//<data> 태그내의 데이터를 담기위한 객체의 참조변수
	
	String content;		//중간 데이터를 저장하기 위한 문자열
	
	//날씨 데이터 리스트 반환 메소드
	public List<Weather> getWeather(){
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		try {
		SAXParser parser = factory.newSAXParser();
			parser.parse("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3017058600", this);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	// @@TODOBLOCK: 필요한 매서드를 재정의 하여 boxOffice.xml을 파싱하시오.
    @Override
    public void startDocument() throws SAXException {
        return;
    }

    @Override
    public void endDocument() throws SAXException {
        return;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // 방금 읽은 태그가 student라면 --> 새로운 Student 생성
        if (qName.equals("data")) {
            weather = new Weather();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.content = new String(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("data")) {
            list.add(weather);
            weather = null;
        } else if (qName.equals("hour")) {
            weather.setHour(content);
        } else if (qName.equals("temp")) {
            weather.setTemp(content);
        } else if (qName.equals("wfKor")) {
            weather.setWfKor(content);
        } else if (qName.equals("reh")) {
            weather.setReh(content);
        }
    }
    // @@END:
	
}
