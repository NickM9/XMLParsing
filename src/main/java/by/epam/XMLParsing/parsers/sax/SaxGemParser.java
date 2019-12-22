package by.epam.XMLParsing.parsers.sax;

import by.epam.XMLParsing.entity.Gem;
import by.epam.XMLParsing.parsers.XSDValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SaxGemParser {

    private static Logger logger = LogManager.getLogger();
    private List<Gem> gemList;

    public List<Gem> parse (File file) throws IOException, SAXException {

        if (XSDValidation.isValid(file)) {

            logger.info("Start parsing SAX");
            try {
                XMLReader reader = XMLReaderFactory.createXMLReader();
                SaxGemHandler handler = new SaxGemHandler();
                reader.setContentHandler(handler);
                reader.parse(new InputSource("data\\gems.xml"));

                gemList = handler.getGemList();
            } catch (SAXException | IOException e) {
                logger.warn(e);
                e.printStackTrace();
            }

            logger.info("SAX parsing successfully completed\n");
            return gemList;
        }

        return null;
    }
}
