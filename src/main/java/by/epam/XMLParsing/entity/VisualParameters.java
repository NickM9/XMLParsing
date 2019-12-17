package by.epam.XMLParsing.entity;

public class VisualParameters {

    private String color;
    private int transparency;
    private int cuttingMethod;

    private VisualParameters(){}

    public String getColor() {
        return color;
    }

    public int getTransparency() {
        return transparency;
    }

    public int getCuttingMethod() {
        return cuttingMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisualParameters that = (VisualParameters) o;

        if (transparency != that.transparency) return false;
        if (cuttingMethod != that.cuttingMethod) return false;
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + transparency;
        result = 31 * result + cuttingMethod;
        return result;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "color='" + color + '\'' +
                ", transparency=" + transparency +
                ", cuttingMethod=" + cuttingMethod +
                '}';
    }

    public static class Builder{

        private VisualParameters newVisualParameters;

        public Builder(){
            newVisualParameters = new VisualParameters();
        }

        public Builder setColor(String color){
            newVisualParameters.color = color;
            return this;
        }

        public Builder setTransparency(int transparency){
            newVisualParameters.transparency = transparency;
            return this;
        }

        public Builder setCuttingMethod(int cuttingMethod){
            newVisualParameters.cuttingMethod = cuttingMethod;
            return this;
        }

        public VisualParameters build(){
            return newVisualParameters;
        }

    }

}
