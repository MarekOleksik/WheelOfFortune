package pl.marekjava;

// Class Player, which represents a player of Wheel of Fortune game
public class Player {
    private String name;

    public Player(String name) {
        try {
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("Empty value is passed.");
            }
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Null value is passed.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "[<" + name + ">]";
    }

    public String getName() {
        return name;
    }
}
