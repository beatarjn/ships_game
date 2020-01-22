package game;

public enum Ship {
    SHIP("S"),

    HIT("H"),

    MISSED("X"),

    EMPTY(" ");

    private String shipState;

    Ship(String shipState) {
        this.shipState = shipState;
    }

    public String getShipState() {
        return shipState;
    }

}
