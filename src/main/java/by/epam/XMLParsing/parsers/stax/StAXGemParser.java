package by.epam.XMLParsing.parsers.stax;

import by.epam.XMLParsing.entity.Gem;
import by.epam.XMLParsing.entity.GemTagName;
import by.epam.XMLParsing.entity.VisualParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StAXGemParser {

    private static Logger logger = LogManager.getLogger();

    public List<Gem> parse(File file){
        List<Gem> gems = null;
        try {
            logger.info("Start parsing StAX");
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(file);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            gems = process(reader);

        } catch (FileNotFoundException | XMLStreamException e){
            logger.warn(e);
            e.printStackTrace();
        }
        logger.info("StAX parsing successfully completed\n");
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
                            gemBuilder.setId(id);
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
                            gemBuilder.setName(text);
                            break;
                        case PRECIOUSNESS:
                            gemBuilder.setPreciousness(text);
                            break;
                        case ORIGIN:
                            gemBuilder.setOrigin(text);
                            break;
                        case VALUE:
                            gemBuilder.setValue(Integer.parseInt(text));
                            break;

                        //VisualParameters
                        case COLOR:
                            visualParametersBuilder.setColor(text);
                            break;
                        case TRANSPARENCY:
                            visualParametersBuilder.setTransparency(Integer.parseInt(text));
                            break;
                        case CUTTING_METHOD:
                            visualParametersBuilder.setCuttingMethod(Integer.parseInt(text));
                            break;
                        default:
                            logger.warn("Tag " + elementName + " not found");
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = GemTagName.getElementTagName(reader.getLocalName());

                    switch (elementName){
                        case GEM:
                            gemBuilder.setVisualParameters(visualParametersBuilder.build());
                            gem = gemBuilder.build();
                            gemList.add(gem);
                    }
            }
        }

        return gemList;
    }
}
