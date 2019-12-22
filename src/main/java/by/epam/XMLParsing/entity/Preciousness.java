package by.epam.XMLParsing.entity;

public enum Preciousness {
    PRECIOUS, SEMIPRECIOUS;

    public static Preciousness choosePreciousness(String str){

        Preciousness preciousness = Preciousness.valueOf(str.toUpperCase());

        switch (preciousness){

            case PRECIOUS:
                return PRECIOUS;
            case SEMIPRECIOUS:
                return SEMIPRECIOUS;
        }

        return null;
    }
}
