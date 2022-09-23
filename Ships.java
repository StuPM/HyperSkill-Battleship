package battleship;

public enum Ships {
    AIRCRAFTCARRIER(5, "Aircraft Carrier"),
    BATTLESHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    final int size;
    final String battleshipName;

    Ships(int size, String battleshipName) {
        this.size = size;
        this.battleshipName = battleshipName;
    }
}
