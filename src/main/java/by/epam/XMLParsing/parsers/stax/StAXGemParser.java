package by.epam.XMLParsing.parsers.stax;

import by.epam.XMLParsing.entity.Gem;
import by.epam.XMLParsing.entity.GemTagName;
import by.epam.XMLParsing.entity.Preciousness;
import by.epam.XMLParsing.entity.VisualParameters;
import by.epam.XMLParsing.parsers.XSDValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StAXGemParser {

    private static Logger logger = LogManager.getLogger();

    public List<Gem> parse(File file) throws IOException, SAXException {
        List<Gem> gems = null;

        if (XSDValidation.isValid(file)) {

            try {
                logger.info("Start parsing StAX");
                XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                InputStream input = new FileInputStream(file);
                XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
                gems = process(reader);

            } catch (FileNotFoundException | XMLStreamException e) {
                logger.warn(e);
                e.printStackTrace();
            }
            logger.info("StAX parsing successfully completed\n");
        }
        return gems;
    }

    private List<Gem> process(XMLStreamReader reader) throws XMLStreamException {
        List<Gem> gemList = new ArrayList<>();
        Gem gem = null;
        Gem.Builder gemBuilder = null;
        VisualParameters.Builder visualParametersBuilder = null;
        GemTagName elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();

            switch (type) {

                case XMLStreamConstants.START_ELEMENT:
                    elementName = GemTagName.getElementTagName(reader.getLocalName());

                    switch (elementName) {
                        case GEM:
                            gemBuilder = new Gem.Builder();
                            visualParametersBuilder = new VisualParameters.Builder();
                            Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
                            gemBuilder.withId(id);
                            logger.info("Gem found id: " + id);
                            break;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();

                    if (text.isEmpty()) {
                        break;
                    }

                    switch (elementName) {

                        case NAME:
                            gemBuilder.withName(text);
                            break;
                        case PRECIOUSNESS:
                            gemBuilder.withPreciousness(Preciousness.choosePreciousness(text));
                            break;
                        case ORIGIN:
                            gemBuilder.withOrigin(text);
                            break;
                        case VALUE:
                            gemBuilder.withValue(Integer.parseInt(text));
                            break;

                        //VisualParameters
                        case COLOR:
                            visualParametersBuilder.withColor(text);
                            break;
                        case TRANSPARENCY:
                            visualParametersBuilder.withTransparency(Integer.parseInt(text));
                            break;
                        case CUTTING_METHOD:
                            visualParametersBuilder.withCuttingMethod(Integer.parseInt(text));
                            break;
                        default:
                            logger.warn("Tag " + elementName + " not found");
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = GemTagName.getElementTagName(reader.getLocalName());

                    switch (elementName){
                        case GEM:
                            gemBuilder.withVisualParameters(visualParametersBuilder.build());
                            gem = gemBuilder.build();
                            gemList.add(gem);
                    }
            }
        }

        return gemList;
    }
}
