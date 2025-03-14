package co.com.guardians.usecase.clue;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@RequiredArgsConstructor
public class ClueUseCase {

    public static void procesarArrayDeCadenas(String[] array) {
        //Validacion Array no vacio
        if (array == null || array.length < 4) {
            System.out.println("false");
            //return;
        }
        // Validar Horizontal
        if (validateStringArray(array)) {
            System.out.println("true HORIZONTAL");
            //return;
        } else {
            System.out.println("false HORIZONTAL");
        }

        // Validar Vertical
        if (validateVertical(array)) {
            System.out.println("true VERTICAL");
            //return;
        } else {
            System.out.println("false VERTICAL");
        }

        //Validate Diagonal
        if (validateAllDiagonals(array, array.length)) {
            System.out.println("true DIAGONAL");
        } else {
            System.out.println("false DIAGONAL");
        }
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

    //VERSION 2
    private static boolean validateAllDiagonals(String[] array, int n) {
        // Verificar diagonales principales y secundarias
        for (int k = 0; k < 2 * n - 1; k++) {
            if (validateDiagonal(array, n, k, true) || validateDiagonal(array, n, k, false)) {
                return true;
            }
        }
        return false;
    }

    //VERSION 2
    private static boolean validateDiagonal(String[] array, int n, int diagonal, boolean isPrimary) {
        int[] cont = new int[128]; // Asumiendo caracteres ASCII
        int x = Math.max(0, diagonal - n + 1);
        int y = isPrimary ? Math.max(0, n - 1 - diagonal) : Math.min(diagonal, n - 1);

        while (x < n && y < n && y >= 0) {
            char c = array[x].charAt(y);
            cont[c]++;
            if (cont[c] == 4) {
                return true;
            }

            x++;
            if (isPrimary) {
                y++;
            } else {
                y--;
            }
        }
        return false;
    }
    public static void main(String[] args) {
//        String[] array = {"abcd", "efgh", "ijkl", "mnop"};
//        procesarArrayDeCadenas(array);  // Salida esperada: false

        String[] arrayv = {"telly", "tyllu", "hyllo",  "tyzzjujknbu", "hyllo"};
        procesarArrayDeCadenas(arrayv);

       System.out.println("-------------------------------------------------");
       System.out.println("EJEMPLO DIAGONAL");
        String[] arrayConDiagonal = {"RTPGQW","XDOLWE","NASWLR","REWSTL","EGSISE","BRINDR"};

        procesarArrayDeCadenas(arrayConDiagonal);  // Salida esperada: true

    }
}