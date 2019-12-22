package by.epam.XMLParsing.entity;

public class Gem {

    private int id;
    private String name;
    private Preciousness preciousness;
    private String origin;
    private VisualParameters visualParameters;
    private int value;

    private Gem(int id, String name, Preciousness preciousness, String origin, VisualParameters visualParameters, int value) {
        this.id = id;
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.visualParameters = visualParameters;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gem gem = (Gem) o;

        if (id != gem.id) return false;
        if (value != gem.value) return false;
        if (name != null ? !name.equals(gem.name) : gem.name != null) return false;
        if (preciousness != null ? !preciousness.equals(gem.preciousness) : gem.preciousness != null) return false;
        if (origin != null ? !origin.equals(gem.origin) : gem.origin != null) return false;
        return visualParameters != null ? visualParameters.equals(gem.visualParameters) : gem.visualParameters == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (preciousness != null ? preciousness.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (visualParameters != null ? visualParameters.hashCode() : 0);
        result = 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", preciousness='" + preciousness + '\'' +
                ", origin='" + origin + '\'' +
                ", visualParameters=" + visualParameters +
                ", value=" + value +
                '}';
    }

    public static class Builder{

        private int id;
        private String name;
        private Preciousness preciousness;
        private String origin;
        private VisualParameters visualParameters;
        private int value;

        public Builder withId(int id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withPreciousness(Preciousness preciousness){
            this.preciousness = preciousness;
            return this;
        }

        public Builder withOrigin(String origin){
            this.origin = origin;
            return this;
        }

        public Builder withVisualParameters(VisualParameters visualParameters){
            this.visualParameters = visualParameters;
            return this;
        }

        public Builder withValue(int value){
            this.value = value;
            return this;
        }

        public Gem build(){
            return new Gem(id, name, preciousness, origin, visualParameters, value);
        }
    }



}
