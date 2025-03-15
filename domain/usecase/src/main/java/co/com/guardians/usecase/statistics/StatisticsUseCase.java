package co.com.guardians.usecase.statistics;

import co.com.guardians.model.manuscriptinventory.ManuscriptInventory;
import co.com.guardians.model.manuscriptinventory.gateways.ManuscriptInventoryGateway;
import co.com.guardians.model.statistics.StatisticsResp;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@RequiredArgsConstructor
public class StatisticsUseCase {
    private final ManuscriptInventoryGateway manuscriptInventoryGateway;

    public StatisticsResp getStatistics() {
         List<ManuscriptInventory> listManuscripts = manuscriptInventoryGateway.getAllManuscripts();
        long numberOfElements = listManuscripts.size();
        long countTrue = listManuscripts.stream()
                .filter(m -> "true".equals(m.getHiddenClue()))
                .count();

        return buildStatistics(countTrue, numberOfElements);
    }

    private static StatisticsResp buildStatistics(long countTrue, long numberOfElements) {
        return StatisticsResp.builder()
                .countClueFound(countTrue)
                .countNoClue(numberOfElements)
                .ratio(BigDecimal.valueOf(countTrue)
                        .divide(BigDecimal.valueOf(numberOfElements), MathContext.DECIMAL128)
                        .setScale(2, RoundingMode.HALF_UP))
                .build();
    }
}
