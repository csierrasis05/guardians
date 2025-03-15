package co.com.guardians.api;
import co.com.guardians.api.dto.ClueDtoResp;
import co.com.guardians.api.mapper.ClueMapper;
import co.com.guardians.usecase.clue.ClueUseCase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public ClueDtoResp analyze(@RequestBody JsonNode bodyNode) {
        ObjectMapper mapper = new ObjectMapper();
        String[] manuscriptArray = mapper.convertValue(bodyNode.get("manuscript"), new TypeReference<String[]>() {});
//        return ClueDtoResp.builder()
//                        .clue(clueUseCase.containsArtifactClue(manuscriptArray).isClue())
//                        .build();

        return ClueMapper.MAPPER.toEntity(clueUseCase.containsArtifactClue(manuscriptArray));
    }
}
