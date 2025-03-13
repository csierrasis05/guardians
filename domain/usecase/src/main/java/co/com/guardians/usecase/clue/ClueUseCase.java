package co.com.guardians.usecase.clue;

//import co.com.guardians.model.clue.gateways.ClueRepository;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ClueUseCase {

//    private final ClueRepository clueRepository;

    public boolean containsArtifactClue(String[] manuscript) {
        char[][] matrix = toMatrix(manuscript);
        return false;
    }

    private char[][] toMatrix(String[] manuscript) {
        // Crear una matrix de caracteres con el tamaño del arreglo y la longitud de la cadena más larga
        int maxLength = Arrays.stream(manuscript)
                .mapToInt(String::length)
                .max()
                .orElse(0);

        char[][] matrix = new char[manuscript.length][maxLength];

        // Rellenar la matrix con los caracteres de las cadenas
        IntStream.range(0, manuscript.length)
                .forEach(i -> {
                    char[] chars = manuscript[i].toCharArray();
                    System.arraycopy(chars, 0, matrix[i], 0, chars.length);
                });

        return matrix;
    }


}
