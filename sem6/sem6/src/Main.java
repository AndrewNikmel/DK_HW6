import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {

    private static final int TOTAL_TRIALS = 1000;
    public static void main(String[] args) {
        Map<Integer, Boolean> results = new HashMap<>();
        Map<Integer, List<Integer>> moves = new HashMap<>();
        for (int i = 1; i <= TOTAL_TRIALS; i++) {
            boolean win = playMontyHallGame(moves, i);
            results.put(i, win);
        }
        for (int i = 1; i <= TOTAL_TRIALS; i++) {
            List<Integer> gameMoves = moves.get(i);
            if (gameMoves != null && gameMoves.size() >= 3) {
                int playerChoice = gameMoves.get(0);
                int goatDoor = gameMoves.get(1);
                int switchChoice = gameMoves.get(2);
                boolean win = results.get(i);
                String explanation = " Игрок выбрал дверь " + playerChoice + ", " +
                        " Ведущий открыл дверь " + goatDoor + ",";

                if (switchChoice > 0) {
                    if (switchChoice == playerChoice) {
                        explanation += " Игрок оставляет дверь ";
                    } else {
                        explanation += " Игрок выбрал дверь " + switchChoice;
                    }
                } else {
                    explanation += " Игрок решил оставить дверь ";
                }
                System.out.println("Игра " + i + ": " + explanation);
                if (win) {
                    System.out.println("Игрок победил))");
                } else {
                    System.out.println("Игрок проиграл((.");
                }
            } else {
                System.out.println("Ошибка при обработке хода игры " + i);
            }
        }
        int positiveResults = (int) results.values().stream().filter(Boolean::valueOf).count();
        int negativeResults = TOTAL_TRIALS - positiveResults;
        double positivePercentage = (double) positiveResults / TOTAL_TRIALS * 100;
        System.out.println("\nСтатистика:");
        System.out.println("Положительные результаты: " + positiveResults);
        System.out.println("Отрицательные результаты: " + negativeResults);
        System.out.println("Процент положительных результатов: " + positivePercentage + "%");
    }


    private static boolean playMontyHallGame(Map<Integer, List<Integer>> moves, int gameNumber) {
        Random random = new Random();
        int carDoor = random.nextInt(3) + 1;
        int playerChoice = random.nextInt(3) + 1;
        int goatDoor;
        do {
            goatDoor = random.nextInt(3) + 1;
        } while (goatDoor == carDoor || goatDoor == playerChoice);

        boolean switchChoice = random.nextBoolean();

        if (switchChoice) {
            do {
                playerChoice = random.nextInt(3) + 1;
            } while (playerChoice == goatDoor);
        }
        List<Integer> gameMoves = new ArrayList<>();
        gameMoves.add(playerChoice);
        gameMoves.add(goatDoor);
        gameMoves.add(
                switchChoice ? playerChoice : 0);
        moves.put(gameNumber, gameMoves);
        return playerChoice == carDoor;
    }
}