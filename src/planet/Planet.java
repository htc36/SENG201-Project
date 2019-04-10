package planet;

public class Planet {

    private String name;
    private boolean hasShipPieces;

    public Planet(String name) {
        this.name = name;
        hasShipPieces = true;
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

}

