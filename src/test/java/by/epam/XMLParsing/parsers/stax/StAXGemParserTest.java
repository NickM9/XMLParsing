package by.epam.XMLParsing.parsers.stax;

import by.epam.XMLParsing.entity.Gem;
import by.epam.XMLParsing.entity.VisualParameters;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class StAXGemParserTest {

    private static final File file = new File("data\\gems.xml");

    @Test
    public void parseTest(){
        Gem expected = new Gem.Builder()
                .setId(1)
                .setName("Aquamarine")
                .setPreciousness("Semiprecious")
                .setOrigin("Madagascar")
                .setVisualParameters(new VisualParameters.Builder()
                        .setColor("Blue")
                        .setTransparency(40)
                        .setCuttingMethod(4)
                        .build())
                .setValue(10)
                .build();

        StAXGemParser parser = new StAXGemParser();
        List<Gem> gemList = parser.parse(file);
        Gem actual = gemList.get(0);
        Assert.assertEquals(expected, actual);
    }
}
