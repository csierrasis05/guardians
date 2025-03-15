package co.com.guardians.usecase.clue;

import co.com.guardians.model.clue.ClueResp;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@RequiredArgsConstructor
public class ClueUseCase {
    private final boolean trueValue = true;
    private final boolean falseValue = false;

    public ClueResp containsArtifactClue(String[] manuscript) {
        if (manuscript == null || manuscript.length < 4) {
            return ClueResp.builder().clue(falseValue).build();
        }
        if(validateStringArray(manuscript) || validateVertical(manuscript) || validateAllDiagonals(manuscript, manuscript.length)){
            return ClueResp.builder().clue(trueValue).build();
        }
        return ClueResp.builder().clue(falseValue).build();
    }
    private static boolean validateStringArray(String[] array) {
        int[] cont = new int[128]; // Asumiendo caracteres ASCII

        for (String s : array) {
            // Limpiar Contador
            Arrays.fill(cont, 0);

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                cont[c]++;
                if (cont[c] == 4) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean validateVertical(String[] array) {
           // Recorrer Array
        for (int i = 0; ; i++) {
            //Validar cantidad de elementos disponibles
            if(array.length - i < 4){
                return false;
            }
            char currentChar = '\0';
            int consecutiveCount = 0;
            boolean positionValid = false;

            // Comparar los caracteres en la misma posición en todos los strings
            for (String word : array) {
                if (word == null || i >= word.length()) {
                    continue; // Ignorar strings más cortos
                }

                positionValid = true;
                char character = word.charAt(i);

                if (character == currentChar) {
                    consecutiveCount++;
                    if (consecutiveCount == 4) {
                        return true;
                    }
                } else {
                    currentChar = character;
                    consecutiveCount = 1;
                }
            }
            // Si no hay strings válidos en esta posición, detener la búsqueda
            if (!positionValid) {
                break;
            }
        }

        return false; // No se encontraron 4 coincidencias consecutivas
    }

    private static boolean validateAllDiagonals(String[] manuscript, int n) {
        // Verificar diagonales principales y secundarias
        for (int k = 0; k < 2 * n - 1; k++) {
            if (validateDiagonal(manuscript, n, k, true) || validateDiagonal(manuscript, n, k, false)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validateDiagonal(String[] manuscript, int size, int diagonal, boolean principal) {
        int[] cont = new int[128];
        int x = Math.max(0, diagonal - size + 1);
        int y = principal ? Math.max(0, size - 1 - diagonal) : Math.min(diagonal, size - 1);

        while (x < size && y < size && y >= 0) {
            char c = manuscript[x].charAt(y);
            cont[c]++;
            if (cont[c] == 4) {
                return true;
            }
            x++;
            if (principal) {
                y++;
            } else {
                y--;
            }
        }
        return false;
    }
}