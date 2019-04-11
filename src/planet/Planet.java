package planet;

public class Planet {

    private String name;
    private boolean hasShipPieces;

    /**
     * <<auto generated javadoc comment>>
     * @param name <<Param Desc>>
     * @param hasShipPieces <<Param Desc>>
     */
    public Planet(String name, boolean hasShipPieces) {
        this.name = name;
        this.hasShipPieces = hasShipPieces;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return boolean <<Return Desc>>
     */
    public boolean stillHasShipPieces() {
        return hasShipPieces;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String getName() {
        return name;
    }

    /**
     * <<auto generated javadoc comment>>
     */
    public void extractShipPieces() {
        hasShipPieces = false;
    }

    /**
     * <<auto generated javadoc comment>>
     * @return String <<Return Desc>>
     */
    public String toString() {
        String template = "";
        template += name + " " + hasShipPieces;
        return template;
    }

}

