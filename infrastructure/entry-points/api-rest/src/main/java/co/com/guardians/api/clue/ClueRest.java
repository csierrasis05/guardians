package co.com.guardians.api.clue;
import co.com.guardians.api.clue.dto.ClueDto;
import co.com.guardians.api.clue.dto.ClueDtoResp;
import co.com.guardians.api.clue.mapper.ClueMapper;
import co.com.guardians.usecase.clue.ClueUseCase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clue", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClueRest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final ClueUseCase clueUseCase;

    @PostMapping
    public ResponseEntity<ClueDtoResp> analyze(@RequestBody JsonNode bodyNode) {
        ClueDto bodyValue = mapper.convertValue(bodyNode, ClueDto.class);
        ClueDtoResp response = ClueMapper.MAPPER.toEntity(clueUseCase.containsArtifactClue(bodyValue.getManuscript()));

        return ResponseEntity.status(response.isClue() ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(response);
    }
}
