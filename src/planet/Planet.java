package planet;

public class Planet {

    private String name;
    private boolean hasShipPieces;

    public Planet(String name, boolean hasShipPieces) {
        this.name = name;
        this.hasShipPieces = hasShipPieces;
    }

    public boolean stillHasShipPieces() {
        return hasShipPieces;
    }

    public String getName() {
        return name;
    }

    public void extractShipPieces() {
        hasShipPieces = false;
    }

    public String toString() {
        String template = "";
        template += name + " " + hasShipPieces;
        return template;
    }

}

