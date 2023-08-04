package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ssafy.k_xml_ui.parse.dto.BoxOffice;

public class WeatherDOMParser {

	
	//날씨 데이터들을 담을 리스트
	private List<Weather> list = new ArrayList<>();
	
	public List<Weather> getWeather(){
		
		//문서 읽어오기
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			//DOM 받아 오기
			Document doc = builder.parse("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3017058600");
			
			//최상위 element
			Element root = doc.getDocumentElement();
			
			//weather 데이터 만드는 작업
			parse(root);
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	
	private void parse(Element root) {
		// @@TODOBLOCK: root에서 data를 추출한 후 Weather를 생성해 list에 저장하시오.
        NodeList weather = root.getElementsByTagName("data");
        for (int i = 0; i < weather.getLength(); i++) {
            Node child = weather.item(i);
            list.add(getWeather(child));
        }
        // @@END:
	}
	
	private static Weather getWeather(Node node) {
		Weather weather = new Weather();
        // @@TODOBLOCK: node 정보를 이용해서 Weather를 구성하고 반환하시오.

        NodeList subNodes = node.getChildNodes();
        for (int j = 0; j < subNodes.getLength(); j++) {
            Node sub = subNodes.item(j);
            if (sub.getNodeName().equals("hour")) {
            	weather.setHour(sub.getTextContent());
            } else if (sub.getNodeName().equals("temp")) {
            	weather.setTemp(sub.getTextContent());
            } else if (sub.getNodeName().equals("wfKor")) {
            	weather.setWfKor(sub.getTextContent());
            } else if (sub.getNodeName().equals("reh")) {
            	weather.setReh(sub.getTextContent());
            }
        }
        // @@END:
        return weather;
    }
	
}
