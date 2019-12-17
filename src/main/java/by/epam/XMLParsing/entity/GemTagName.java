package by.epam.XMLParsing.entity;

public enum GemTagName {
    GEMS, GEM, NAME, PRECIOUSNESS, ORIGIN, VISUAL_PARAMETERS, COLOR, TRANSPARENCY, CUTTING_METHOD, VALUE;

    public static GemTagName getElementTagName(String element){

        switch (element){

            case "gems":
                return GEMS;
            case "gem":
                return GEM;
            case "name":
                return NAME;
            case "preciousness":
                return PRECIOUSNESS;
            case "origin":
                return ORIGIN;
            case "visual-parameters":
                return VISUAL_PARAMETERS;
            case "color":
                return COLOR;
            case "transparency":
                return TRANSPARENCY;
            case "cutting-method":
                return CUTTING_METHOD;
            case "value":
                return VALUE;
            default:
                throw new EnumConstantNotPresentException(GemTagName.class, element);
        }
    }
}
