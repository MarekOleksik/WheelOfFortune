package pl.marekjava;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    private static Scanner scan = new Scanner(System.in);
    final static int ROUNDS = 4;
    private static PasswordManager pm = new PasswordManager();
    final static int POINTS_PER_GUESS = 10;

    public static void main(String[] args) {

        System.out.println("Witaj w Kole Fortuny");
        System.out.println();

        int numberOfPlayers = 0;
        try {
            numberOfPlayers = getNumberOfPlayers();
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            return;
        }

        List<Player> players = getNamesOfPlayers(numberOfPlayers);
        if (players == null) return;

        playGame(players);

        scan.close();
    }

    private static void playGame(List<Player> players) {
        boolean isRoundContinue;
        Map<Player, Integer> sumOfPoints = new HashMap<>();

        for (int i = 1; i <= ROUNDS; i++) {
            System.out.println();
            System.out.println("Rozpoczęła się runda " + i);
            isRoundContinue = true;
            String password = pm.getRandomPassword();
            while (isRoundContinue) {
                isRoundContinue = playerTurn(players, sumOfPoints, password);
            }
        }

        Map<Player, Integer> newMapSortedByValue = getReversedSortedMap(sumOfPoints);
        System.out.println();
        printResults(newMapSortedByValue);
    }

    private static boolean playerTurn(List<Player> players, Map<Player, Integer> sumOfPoints, String password) {
        boolean isRoundContinue = true;
        for (Player player : players) {
            System.out.println();
            System.out.println("Tura gracza " + player);
            System.out.println(pm.getObscuredPassword());
            String input = scan.nextLine();
            if (input.length() == 1) {
                guessLetter(password, input, player);
            } else {
                isRoundContinue = !guessPassword(input, player);
            }
            sumOfPoints.put(player, player.getPoints());
            if (!isRoundContinue) break;
            isRoundContinue = !checkPassword();
            if (!isRoundContinue) break;
        }
        return isRoundContinue;
    }

    private static void printResults(Map<Player, Integer> newMapSortedByValue) {
        newMapSortedByValue.forEach((k, v) -> {
            System.out.println(k + " : " + v + " points.");
        });
    }

    private static Map<Player, Integer> getReversedSortedMap(Map<Player, Integer> sumOfPoints) {
        Map<Player, Integer> newMapSortedByValue = sumOfPoints.entrySet().stream()
                .sorted(Map.Entry.<Player, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return newMapSortedByValue;
    }

    private static boolean checkPassword() {
        if (pm.checkPassword()) {
            System.out.println("Hasło odgadnięte");
            return true;
        } else {
            return false;
        }
    }

    private static boolean guessPassword(String input, Player player) {
        System.out.println("Zgaduję hasło");
        if (pm.guessPassword(input)) {
            System.out.println("Hasło odgadnięte");
            char[] obscuredPassword = pm.getObscuredPassword().toCharArray();
            int remainingLetters = 0;
            for (char c : obscuredPassword) {
                if (c == '-') {
                    remainingLetters++;
                }
            }
            player.addPoints(remainingLetters);
            return true;
        } else {
            System.out.println("Niepoprawne hasło");
            return false;
        }
    }

    private static void guessLetter(String password, String input, Player player) {
        System.out.println("Zgaduję literę");
        if (password.toLowerCase().contains(input.toLowerCase())) {
            System.out.println("Zgadnięta");
            int countOfLetters = pm.guessLetter(input.charAt(0));
            player.addPoints(countOfLetters * POINTS_PER_GUESS);
        } else {
            System.out.println("Taka litera nie występuje w haśle");
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

    private static int getNumberOfPlayers() {
        int numberOfPlayers = 0;
        System.out.print("Podaj liczbę graczy: ");
        try {
            numberOfPlayers = Integer.parseInt(scan.nextLine());

        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Ilość graczy musi być liczbą od 2 do 4.");
        }

        if (numberOfPlayers < 2 || numberOfPlayers > 4) {
            throw new IllegalArgumentException("Ilość graczy powinna zmieścić się pomiędzy 2 a 4.");
        }
        return numberOfPlayers;
    }
}
