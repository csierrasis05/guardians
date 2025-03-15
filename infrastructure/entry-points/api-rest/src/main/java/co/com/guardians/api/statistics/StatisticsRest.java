package co.com.guardians.api.statistics;

import co.com.guardians.api.clue.dto.ClueDto;
import co.com.guardians.api.clue.dto.ClueDtoResp;
import co.com.guardians.api.clue.mapper.ClueMapper;
import co.com.guardians.api.statistics.dto.StatisticsDtoResp;
import co.com.guardians.api.statistics.mapper.StatisticsMapper;
import co.com.guardians.usecase.statistics.StatisticsUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class StatisticsRest {
    private final ObjectMapper mapper = new ObjectMapper();
    private final StatisticsUseCase statisticsUseCase;
    @GetMapping
    public ResponseEntity<StatisticsDtoResp> getStatistics() {
        return ResponseEntity.ok(StatisticsMapper.MAPPER.toEntity(statisticsUseCase.getStatistics()));
    }
}
