package co.com.guardians.api;
import co.com.guardians.usecase.clue.ClueUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clue", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClueRest {


    private final ClueUseCase clueUseCase;


    @GetMapping
    public String commandName() {
//      return useCase.doAction();
        return "Hello World";
    }

    @PostMapping
    public String analyze(@RequestBody JsonNode bodyNode) {
//      return clueUseCase.containsArtifactClue(bodyNode.get("manuscript").asText()).getClue();

        return "Hello World";
    }
}
