package by.epam.XMLParsing.parsers.dom;

import by.epam.XMLParsing.entity.Gem;
import by.epam.XMLParsing.entity.Preciousness;
import by.epam.XMLParsing.entity.VisualParameters;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DomGemParserTest {

    private static final File file = new File("data\\gems.xml");

    @Test
    public void parseTest() throws IOException, SAXException {
        Gem expected = new Gem.Builder()
                .withId(1)
                .withName("Aquamarine")
                .withPreciousness(Preciousness.SEMIPRECIOUS)
                .withOrigin("Madagascar")
                .withVisualParameters(new VisualParameters.Builder()
                        .withColor("Blue")
                        .withTransparency(40)
                        .withCuttingMethod(4)
                        .build())
                .withValue(10)
                .build();

        DomGemParser parser = new DomGemParser();
        List<Gem> gemList = parser.parse(file);
        Gem actual = gemList.get(0);
        Assert.assertEquals(expected, actual);
    }
}
