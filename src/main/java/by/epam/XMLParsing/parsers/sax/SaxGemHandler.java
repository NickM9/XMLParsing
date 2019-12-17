package by.epam.XMLParsing.parsers.sax;

import by.epam.XMLParsing.entity.Gem;
import by.epam.XMLParsing.entity.GemTagName;
import by.epam.XMLParsing.entity.VisualParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaxGemHandler extends DefaultHandler {

    private static Logger logger = LogManager.getLogger();

    private List<Gem> gemList = new ArrayList<>();
    private Gem.Builder gemBuilder;
    private VisualParameters.Builder visualParametersBuilder;
    private Gem gem;

    private StringBuilder text;

    public List<Gem> getGemList() {
        return gemList;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing started");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing ended");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text.append(ch, start, length);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        text = new StringBuilder();
        if (qName.equals("gem")) {
            gemBuilder = new Gem.Builder();
            visualParametersBuilder = new VisualParameters.Builder();

            gemBuilder.setId(Integer.parseInt(attributes.getValue("id")));
            logger.info("Gem found id: " + attributes.getValue("id"));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        GemTagName tagName = GemTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        switch (tagName) {
            case NAME:
                gemBuilder.setName(text.toString());
                break;
            case PRECIOUSNESS:
                gemBuilder.setPreciousness(text.toString());
                break;
            case ORIGIN:
                gemBuilder.setOrigin(text.toString());
                break;
            case VALUE:
                gemBuilder.setValue(Integer.parseInt(text.toString()));
                break;

            // VisualParameters
            case COLOR:
                visualParametersBuilder.setColor(text.toString());
                break;
            case TRANSPARENCY:
                visualParametersBuilder.setTransparency(Integer.parseInt(text.toString()));
                break;
            case CUTTING_METHOD:
                visualParametersBuilder.setCuttingMethod(Integer.parseInt(text.toString()));
                break;

            // closing tags
            case VISUAL_PARAMETERS:
                break;
            case GEMS:
                break;

            case GEM:
                gemBuilder.setVisualParameters(visualParametersBuilder.build());
                gem = gemBuilder.build();
                gemList.add(gem);

                gem = null;
                gemBuilder = null;
                visualParametersBuilder = null;

                break;

            default:
                logger.warn("Tag " + tagName + " not found");
        }
    }
}
