package co.com.guardians.model.clue.gateways;

import co.com.guardians.model.clue.Clue;
import co.com.guardians.model.clue.ClueResp;

public interface ClueRepository {

    ClueResp containsArtifactClue(Clue clue);

    boolean containsArtifactClue(String[] manuscript);

}
