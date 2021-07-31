package pl.marekjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This is a main class of Wheel of Fortune game
public class App {
    private static Scanner scan = new Scanner(System.in);
    private static int ROUNDS = 4;

    public static void main(String[] args) {

        System.out.println("Witaj w Kole Fortuny");

        // Ask how many players will play
        Integer numberOfPlayers = getNumberOfPlayers();
        if (numberOfPlayers == null) return;

        // Add name of player to list of players
        List<Player> players = getNamesOfPlayers(numberOfPlayers);
        if (players == null) return;

        playGame(players);

        // close Scanner
        scan.close();
    }

    private static void playGame(List<Player> players) {
        for (int i = 1; i <= ROUNDS; i++) {
            System.out.println("Rozpoczęła się runda " + i);
            for (int j = 0; j < players.size(); j++) {
                System.out.println("Tura gracza " + players.get(j));
            }
        }
    }

    private static List<Player> getNamesOfPlayers(Integer numberOfPlayers) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Podaj imię gracza: ");
            String name = scan.nextLine();
            try {
                players.add(new Player(name));
            } catch (IllegalArgumentException ex) {
                System.out.println("Imię nie może być puste");
                return null;
            }
        }
        return players;
    }

    private static Integer getNumberOfPlayers() {
        int numberOfPlayers = 0;
        System.out.print("Podaj liczbę graczy: ");
        try {
            numberOfPlayers = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Ilość graczy musi być liczbą od 2 do 4.");
        }

        if (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.println("Ilość graczy powinna zmieścić się pomiędzy 2 a 4.");
            return null;
        }
        return numberOfPlayers;
    }
}
