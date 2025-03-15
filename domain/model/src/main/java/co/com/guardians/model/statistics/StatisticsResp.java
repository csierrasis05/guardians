package co.com.guardians.model.statistics;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StatisticsResp {
    private Long countClueFound;
    private Long countNoClue;
    private BigDecimal ratio;
}
