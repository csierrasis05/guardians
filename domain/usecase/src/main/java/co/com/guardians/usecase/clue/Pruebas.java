package co.com.guardians.usecase.clue;

import java.util.HashMap;
import java.util.Map;

public class Pruebas {
    public static boolean buscarCoincidenciasEnDiagonales(String[] array) {
        int n = array.length;

        // Validación para asegurarnos de que no estamos trabajando con un array vacío
        if (n == 0) {
            return false;
        }

        // Verificar todas las diagonales principales y secundarias
        return verificarDiagonales(array, n);
    }

    private static boolean verificarDiagonales(String[] array, int n) {
        // Verificar diagonales principales y secundarias
        for (int k = 0; k < n; k++) {
            if (verificarDiagonal(array, n, k, 0, true) || verificarDiagonal(array, n, 0, k, true) ||
                    verificarDiagonal(array, n, k, 0, false) || verificarDiagonal(array, n, 0, n - 1 - k, false)) {
                return true;
            }
        }
        return false;
    }

    private static boolean verificarDiagonal(String[] array, int n, int startX, int startY, boolean isPrimary) {
        Map<Character, Integer> contador = new HashMap<>();
        int x = startX, y = startY;

        while (x < n && y < n && x >= 0 && y >= 0) {
            char c = array[x].charAt(y);
            int count = contador.getOrDefault(c, 0) + 1;
            contador.put(c, count);

            if (count == 4) {
                return true;
            }

            if (isPrimary) {
                x++;
                y++;
            } else {
                x++;
                y--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Ejemplo 1: Sin repetición de 4 veces en las diagonales
        String[] array = {"abcd", "efgh", "ijkl", "mnop"};
        System.out.println(buscarCoincidenciasEnDiagonales(array));  // Salida esperada: false

        // Ejemplo 2: Con repetición de la letra 'j' 4 veces en la diagonal secundaria
        String[] arrayConDiagonal = {"RTHGQW","XDTORE","NARTRR","REVRTL","EGSILE","BRINDS"};
        System.out.println(buscarCoincidenciasEnDiagonales(arrayConDiagonal));  // Salida esperada: true

        // Ejemplo 3: Con repetición de la letra 'a' 4 veces en las cadenas
        String[] arrayConRepeticion = {"aaa", "bbaa", "ccdd", "aaaa"};
        System.out.println(buscarCoincidenciasEnDiagonales(arrayConRepeticion));  // Salida esperada: true
    }

}
