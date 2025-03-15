package co.com.guardians.api.statistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StatisticsDtoResp {
    @JsonProperty("count_clue_found")
    private Long countClueFound;
    @JsonProperty("count_no_clue")
    private Long countNoClue;
    @JsonProperty("ratio")
    private BigDecimal ratio;
}
