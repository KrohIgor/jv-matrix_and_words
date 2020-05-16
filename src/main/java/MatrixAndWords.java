import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixAndWords {
    private static List<Point> list = new ArrayList<>();
    private static char[][] charMatrix;

    public static void main(String[] args) {
        String matrixString = readStringMatrix();
        String word = readWord(matrixString.length());
        initializationCharMatrix(matrixString);
        sequenceOfCellsMakingUpAGivenWord(word, charMatrix);
        if (list.size() > 0) {
            System.out.println(buildResultString());
        } else {
            System.out.println("Cell sequence not found!");
            ;
        }
    }

    private static String buildResultString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                result.append(" -> ");
            }
            result.append('[').append(list.get(i).x).append(',').append(list.get(i).y).append(']');
        }
        return result.toString();
    }

    private static void sequenceOfCellsMakingUpAGivenWord(String word, char[][] charMatrix) {
        if (word.length() > 0) {
            char letter = word.charAt(0);
            for (int j = 0; j < charMatrix.length; j++) {
                for (int k = 0; k < charMatrix[j].length; k++) {
                    if (letter == charMatrix[j][k]) {
                        findCellsOfLettersOfTheWord(word, 0, j, k);
                        if (list.size() == word.length()) {
                            return;
                        } else {
                            list = new ArrayList<>();
                        }
                    }
                }
            }
        }
    }

    private static void findCellsOfLettersOfTheWord(String word, int i, int x, int y) {
        Point point = new Point(x, y);
        list.add(point);
        if (i == word.length() - 1) {
            return;
        }
        char letter = word.charAt(++i);
        if (x - 1 >= 0 && charMatrix[x - 1][y] == letter) {
            Point pointNext = new Point(x - 1, y);
            if (!list.contains(pointNext)) {
                findCellsOfLettersOfTheWord(word, i, x - 1, y);
            }
        }
        if (x + 1 < charMatrix.length && charMatrix[x + 1][y] == letter) {
            Point pointNext = new Point(x + 1, y);
            if (!list.contains(pointNext)) {
                findCellsOfLettersOfTheWord(word, i, x + 1, y);
            }
        }
        if (y - 1 >= 0 && charMatrix[x][y - 1] == letter) {
            Point pointNext = new Point(x, y - 1);
            if (!list.contains(pointNext)) {
                findCellsOfLettersOfTheWord(word, i, x, y - 1);
            }
        }
        if (y + 1 < charMatrix[x].length && charMatrix[x][y + 1] == letter) {
            Point pointNext = new Point(x, y + 1);
            if (!list.contains(pointNext)) {
                findCellsOfLettersOfTheWord(word, i, x, y + 1);
            }
        }
        if (list.size() != word.length()) {
            list.remove(list.size() - 1);
        }
    }

    private static void initializationCharMatrix(String matrixString) {
        int lengthMatrix = (int) Math.sqrt(matrixString.length());
        charMatrix = new char[lengthMatrix][lengthMatrix];
        for (int i = 0; i < matrixString.length(); i++) {
            charMatrix[i / lengthMatrix][i % lengthMatrix] = matrixString.charAt(i);
        }
    }

    private static String readWord(int length) {
        System.out.println("Input Word");
        Scanner scanner = new Scanner(System.in);
        String word = "";
        while (scanner.hasNext()) {
            word = scanner.nextLine();
            int wordLength = word.length();
            if (wordLength <= length) {
                break;
            }
            System.out.println("Word too long");
        }
        return word;
    }

    private static String readStringMatrix() {
        System.out.println("Input a string of size N ^ 2 describing "
                + "the square matrix of characters N * N");
        Scanner scanner = new Scanner(System.in);
        String matrixString = "";
        while (scanner.hasNext()) {
            matrixString = scanner.nextLine();
            int length = matrixString.length();
            if ((int) Math.sqrt(length) * Math.sqrt(length) == length) {
                break;
            }
            System.out.println("Incorrect string length " + length);
        }
        return matrixString;
    }
}
