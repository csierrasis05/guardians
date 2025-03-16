package co.com.guardians.usecase.clue;

import co.com.guardians.model.clue.ClueResp;
import co.com.guardians.model.manuscriptinventory.ManuscriptInventory;
import co.com.guardians.model.manuscriptinventory.gateways.ManuscriptInventoryGateway;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ClueUseCase {
    private final String trueValue = "true";
    private final String falseValue = "false";

    private final ManuscriptInventoryGateway manuscriptInventoryGateway;

    public ClueResp containsArtifactClue(String[] manuscript) {
        if (manuscript == null || manuscript.length < 4) {
            return ClueResp.builder().clue(false).build();
        }

        String ms = String.join("-", manuscript);
        ManuscriptInventory manus = ManuscriptInventory.builder()
                .manuscript(ms)
                .hiddenClue(falseValue)
                .build();

        ManuscriptInventory manuscriptInventory = manuscriptInventoryGateway.save(manus);

        if(validateStringArray(manuscript) || validateVertical(manuscript) || validateAllDiagonals(manuscript, manuscript.length)) {
            return ClueResp.builder().clue(Boolean.parseBoolean(manuscriptInventoryGateway.save(ManuscriptInventory.builder()
                            .manuscript(ms)
                            .hiddenClue(trueValue)
                            .build()
                    ).getHiddenClue()))
                    .build();
        }

        return ClueResp.builder().clue(Boolean.parseBoolean(manuscriptInventoryGateway.save(ManuscriptInventory.builder()
                        .manuscript(ms)
                        .hiddenClue(falseValue)
                        .build()
                ).getHiddenClue()))
                .build();
    }

    private static boolean validateStringArray(String[] array) {
        for (String s : array) {
            if (s == null || s.isEmpty()) {
                continue; // Ignorar strings vacíos o nulos
            }

            char currentChar = s.charAt(0);
            int consecutiveCount = 1;

            for (int i = 1; i < s.length(); i++) {
                char character = s.charAt(i);

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
        }
        return false;
    }

    private static boolean validateVertical(String[] array) {
        int maxLength = array.length;
        for (int i = 0; i < maxLength; i++) {
            char currentChar = '\0';
            int consecutiveCount = 0;

            for (String word : array) {
                if (word == null || i >= word.length()) {
                    break; // Evita el acceso fuera de rango
                }

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
        }
        return false;
    }

    private static boolean validateAllDiagonals(String[] manuscript, int n) {
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
            if (x >= manuscript.length || y >= manuscript[x].length() || y < 0) {
                break; // Evita accesos fuera de rango
            }

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