package pl.marekjava;

public class Player {
    private String name;

    public Player(String name) {
        if (name.isEmpty()) throw new IllegalArgumentException("Empty value is passed.");
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
