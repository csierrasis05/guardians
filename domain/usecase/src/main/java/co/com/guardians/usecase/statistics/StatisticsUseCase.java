package co.com.guardians.usecase.statistics;

import co.com.guardians.model.manuscriptinventory.gateways.ManuscriptInventoryGateway;
import co.com.guardians.model.statistics.StatisticsResp;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class StatisticsUseCase {
    private final ManuscriptInventoryGateway manuscriptInventoryGateway;

    public StatisticsResp getStatistics() {
//        manuscriptInventoryGateway.countItemsInTable();
//        return StatisticsResp.builder()
//                .totalManuscripts(manuscriptInventoryGateway.countManuscripts())
//                .totalClues(manuscriptInventoryGateway.countClues())
//                .build();
        return null;
    }
}
