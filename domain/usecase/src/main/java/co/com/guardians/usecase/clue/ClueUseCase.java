package co.com.guardians.usecase.clue;

//import co.com.guardians.model.clue.gateways.ClueRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ClueUseCase {

    //    private final ClueRepository clueRepository;

    public boolean containsArtifactClue(String[] manuscript) {
        char[][] matrix = toMatrix(manuscript);
        printMatrix(matrix);
        char[][] transmatrix = transposeMatrix(matrix);
        printMatrix(transmatrix);
        String diagonal = getDiagonal(matrix);
        System.out.println(diagonal);
        validate(matrix);
        //validate(diagonal);
        return false;
    }

    private char[][] toMatrix(String[] manuscript) {
        // Crear una matrix de caracteres con el tamaño del arreglo y la longitud de la cadena más larga
        int maxLength = Arrays.stream(manuscript)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        return Arrays.stream(manuscript)
                .map(s -> {
                    char[] row = new char[maxLength];
                    System.arraycopy(s.toCharArray(), 0, row, 0, s.length());
                    return row;
                })
                .toArray(char[][]::new);
    }

    private char[][] transposeMatrix(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        return IntStream.range(0, cols)
                .mapToObj(col -> IntStream.range(0, rows)
                        .mapToObj(row -> matrix[row][col])
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString().toCharArray())
                .toArray(char[][]::new);
    }

    private boolean validate(char[][] manuscriptMatrix) {
        return Arrays.stream(manuscriptMatrix)
                .anyMatch(row -> {
                    long match = coincidence(row);
                    if (match >= 3) {
                        System.out.println("iguales");
                        return true;
                    } else {
                        System.out.println("diferentes");
                        return false;
                    }
                });
    }

    private static long coincidence(char[] row) {
        return IntStream.range(0, row.length - 1)
                .filter(i -> row[i] == row[i + 1])
                .count();
    }

    private String getDiagonal(char[][] manuscriptMatrix) {
        return IntStream.range(0, Math.min(manuscriptMatrix.length, manuscriptMatrix[0].length))
                .mapToObj(i -> String.valueOf(manuscriptMatrix[i][i]))
                .collect(Collectors.joining());
    }

    private  void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

}
