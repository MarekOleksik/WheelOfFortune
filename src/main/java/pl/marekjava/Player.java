package pl.marekjava;

public class Player {
    private String name;
    private int points;

    public Player(String name) {
        try {
            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("Empty value is passed.");
            }
        } catch (NullPointerException ex) {
            throw new IllegalArgumentException("Null value is passed.");
        }
        this.name = name;
        this.points = 0;
    }

    @Override
    public String toString() {
        return "[<" + name + ">]";
    }

    public String getName() {
        return name;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
