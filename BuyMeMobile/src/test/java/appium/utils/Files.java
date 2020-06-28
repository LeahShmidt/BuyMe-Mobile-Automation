package appium.utils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Files {

    public  String readFile(String tagName) throws IOException, ParserConfigurationException, org.xml.sax.SAXException {


        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\lshmidt\\IdeaProjects\\BuyMeMobile\\src\\test\\resources\\params.xml"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            System.out.println(everything);
        }
//        String param = getParamByTagName(tagName) ;

        return getParamByTagName(tagName);

    }

    private  String getParamByTagName(String tag) throws ParserConfigurationException, IOException, org.xml.sax.SAXException {

        File configXmlFile = new File("C:\\Users\\lshmidt\\IdeaProjects\\BuyMeMobile\\src\\test\\resources\\params.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(configXmlFile);

        if (doc != null) {
            doc.getDocumentElement().normalize();
        }
        assert doc != null;
        return doc.getElementsByTagName(tag).item(0).getTextContent();
    }
}
