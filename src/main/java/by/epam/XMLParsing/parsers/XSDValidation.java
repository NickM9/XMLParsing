package by.epam.XMLParsing.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XSDValidation {

    private static Logger logger = LogManager.getLogger();
    private static File schemaLocation = new File("src/main/resources/gems.xsd");

    public static boolean isValid(File file) throws SAXException, IOException {
        boolean res = false;

        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();

        Source source = new StreamSource(file);


        try{
            validator.validate(source);
            res = true;
            logger.info("XML is valid");
        } catch (SAXException ex){
            logger.warn("XML is not valid");
            logger.warn(ex.getMessage());
        }
        return res;
    }

}
