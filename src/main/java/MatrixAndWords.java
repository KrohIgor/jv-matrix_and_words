import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixAndWords {

    public static String buildMatrixAndFindWord() {
        String matrixString = readStringMatrix();
        String word = readWord(matrixString.length());
        char[][] charMatrix = initializeCharMatrix(matrixString);
        List<Point> list = findLetterCells(word, charMatrix);
        String result = "";
        if (list.size() > 0) {
            result = buildResultString(list);
        } else {
            result = "Cell sequence not found!";
        }
        return result;
    }

    private static String buildResultString(List<Point> list) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != 0) {
                result.append(" -> ");
            }
            result.append('[').append(list.get(i).x).append(',').append(list.get(i).y).append(']');
        }
        return result.toString();
    }

    private static List<Point> findLetterCells(String word, char[][] charMatrix) {
        List<Point> list = new ArrayList<>();
        if (word.length() > 0) {
            char letter = word.charAt(0);
            for (int j = 0; j < charMatrix.length; j++) {
                for (int k = 0; k < charMatrix[j].length; k++) {
                    if (letter == charMatrix[j][k]) {
                        findLetterCellsInMatrix(word, list, charMatrix, 0, j, k);
                        if (list.size() == word.length()) {
                            return list;
                        } else {
                            list = new ArrayList<>();
                        }
                    }
                }
            }
        }
        return list;
    }

    private static void findLetterCellsInMatrix(String word, List<Point> list,
                                                char[][] charMatrix, int i, int x, int y) {
        Point point = new Point(x, y);
        list.add(point);
        if (i == word.length() - 1) {
            return;
        }
        char letter = word.charAt(++i);
        if (x - 1 >= 0 && charMatrix[x - 1][y] == letter) {
            Point pointNext = new Point(x - 1, y);
            if (!list.contains(pointNext)) {
                findLetterCellsInMatrix(word, list, charMatrix, i, x - 1, y);
            }
        }
        if (x + 1 < charMatrix.length && charMatrix[x + 1][y] == letter) {
            Point pointNext = new Point(x + 1, y);
            if (!list.contains(pointNext)) {
                findLetterCellsInMatrix(word, list, charMatrix, i, x + 1, y);
            }
        }
        if (y - 1 >= 0 && charMatrix[x][y - 1] == letter) {
            Point pointNext = new Point(x, y - 1);
            if (!list.contains(pointNext)) {
                findLetterCellsInMatrix(word, list, charMatrix, i, x, y - 1);
            }
        }
        if (y + 1 < charMatrix[x].length && charMatrix[x][y + 1] == letter) {
            Point pointNext = new Point(x, y + 1);
            if (!list.contains(pointNext)) {
                findLetterCellsInMatrix(word, list, charMatrix, i, x, y + 1);
            }
        }
        if (list.size() != word.length()) {
            list.remove(list.size() - 1);
        }
    }

    private static char[][] initializeCharMatrix(String matrixString) {
        int lengthMatrix = (int) Math.sqrt(matrixString.length());
        char[][] charMatrix = new char[lengthMatrix][lengthMatrix];
        for (int i = 0; i < matrixString.length(); i++) {
            charMatrix[i / lengthMatrix][i % lengthMatrix] = matrixString.charAt(i);
        }
        return charMatrix;
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
