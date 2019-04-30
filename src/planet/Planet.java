package planet;

public class Planet {

    private String name;
    private boolean hasShipPieces;

    /**
     * Constructor for a Planet
     * @param name Name of the planet
     * @param hasShipPieces True if has ship piece, false otherwise
     */
    public Planet(String name, boolean hasShipPieces) {
        this.name = name;
        this.hasShipPieces = hasShipPieces;
    }

    /**
     * Returns whether a planet still has ship piece
     * @return boolean True if planet has ship piece, false otherwise
     */
    public boolean stillHasShipPieces() {
        return hasShipPieces;
    }

    /**
     * Returns name of planet
     * @return String Name of planet
     */
    public String getName() {
        return name;
    }

    /**
     * Extracts the ship piece from the planet, 
     * removing it
     */
    public void extractShipPieces() {
        hasShipPieces = false;
    }

    /**
     * Representation of a planet
     * @return String String repr of a planet
     */
    public String toString() {
        String template = "";
        template += name + " " + hasShipPieces;
        return template;
    }

}

