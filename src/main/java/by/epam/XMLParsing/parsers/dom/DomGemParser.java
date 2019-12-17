package by.epam.XMLParsing.parsers.dom;

import by.epam.XMLParsing.entity.Gem;
import by.epam.XMLParsing.entity.VisualParameters;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomGemParser {

    private static Logger logger = LogManager.getLogger();

    public List<Gem> parse(File file){

        logger.info("Start parsing DOM");
        DOMParser parser = new DOMParser();

        try {
            parser.parse(String.valueOf(file));
        } catch (SAXException | IOException e) {
            logger.warn(e);
            e.printStackTrace();
        }

        Document document = parser.getDocument();

        Element root = document.getDocumentElement();

        List<Gem> gems = new ArrayList<>();

        NodeList gemNodes = root.getElementsByTagName("gem");
        Gem gem = null;
        Gem.Builder gemBuilder = null;
        VisualParameters.Builder visualParametersBuilder = null;

        for (int i = 0; i < gemNodes.getLength(); i++){
            gemBuilder = new Gem.Builder();
            visualParametersBuilder = new VisualParameters.Builder();
            Element gemElement = (Element) gemNodes.item(i);
            logger.info("Gem found id: " + Integer.parseInt(gemElement.getAttribute("id")));
            gemBuilder.setId(Integer.parseInt(gemElement.getAttribute("id")));
            gemBuilder.setName(getSingleChild(gemElement, "name").getTextContent().trim());
            gemBuilder.setPreciousness(getSingleChild(gemElement, "preciousness").getTextContent().trim());
            gemBuilder.setOrigin(getSingleChild(gemElement, "origin").getTextContent().trim());
            gemBuilder.setValue(Integer.parseInt(getSingleChild(gemElement, "value").getTextContent().trim()));
            visualParametersBuilder.setColor(getSingleChild(gemElement, "color").getTextContent().trim());
            visualParametersBuilder.setTransparency(Integer.parseInt(getSingleChild(gemElement, "transparency").getTextContent().trim()));
            visualParametersBuilder.setCuttingMethod(Integer.parseInt(getSingleChild(gemElement, "cutting-method").getTextContent().trim()));

            gemBuilder.setVisualParameters(visualParametersBuilder.build());
            gem = gemBuilder.build();
            gems.add(gem);
        }

        logger.info("DOM parsing successfully completed\n");
        return gems;
    }

    private Element getSingleChild(Element element, String childName){
        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);
        return child;
    }

}
