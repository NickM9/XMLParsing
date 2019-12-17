package by.epam.XMLParsing.entity;

public class Gem {

    private int id;
    private String name;
    private String preciousness;
    private String origin;
    private VisualParameters visualParameters;
    private int value;

    private Gem(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPreciousness() {
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

        private Gem newGem;

        public Builder(){
            newGem = new Gem();
        }

        public Builder setId(int id){
            newGem.id = id;
            return this;
        }

        public Builder setName(String name){
            newGem.name = name;
            return this;
        }

        public Builder setPreciousness(String preciousness){
            newGem.preciousness = preciousness;
            return this;
        }

        public Builder setOrigin(String origin){
            newGem.origin = origin;
            return this;
        }

        public Builder setVisualParameters(VisualParameters visualParameters){
            newGem.visualParameters = visualParameters;
            return this;
        }

        public Builder setValue(int value){
            newGem.value = value;
            return this;
        }

        public Gem build(){
            return newGem;
        }
    }



}
