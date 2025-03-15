package co.com.guardians.api.clue;

import co.com.guardians.api.clue.ClueRest;
import co.com.guardians.api.clue.dto.ClueDto;
import co.com.guardians.api.clue.dto.ClueDtoResp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class ClueRestTest {

    private JsonNode bodyNode;
    private ObjectMapper objectMapper;
    @InjectMocks
    private ClueRest clueRest;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        MockitoAnnotations.openMocks(this);

        objectMapper = new ObjectMapper();
        bodyNode = objectMapper.readTree("{\"manuscript\":[\"RRRRR\",\"RTHGQTTT\",\"XRLOTE\",\"NARTRR\",\"RETAAL\",\"EGSILE\",\"BRINDS\"]}");

    }
    @Test
    void analyzeTest() {
        var response = clueRest.analyze(bodyNode);
        assertNotNull(response);
        assertTrue(response.getBody().isClue());

    }
}
