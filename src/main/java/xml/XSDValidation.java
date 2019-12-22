package xml;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XSDValidation {
    public static void main(String[] args) throws SAXException, IOException {

        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        File schemaLocation = new File("src/main/resources/gems.xsd");
        Schema schema = factory.newSchema(schemaLocation);

        Validator validator = schema.newValidator();

        Source source = new StreamSource("data/gems.xml");

        try{
            validator.validate(source);
            System.out.println("is valid.");
        } catch (SAXException ex){
            System.out.println("is not valid because ");
            System.out.println(ex.getMessage());
        }

    }
}
