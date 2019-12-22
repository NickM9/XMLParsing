package by.epam.XMLParsing.parsers;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, SAXException {

        File file = new File("data/gems.xml");

        System.out.println(XSDValidation.isValid(file));

    }
}
