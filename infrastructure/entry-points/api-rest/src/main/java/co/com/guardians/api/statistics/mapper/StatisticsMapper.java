package co.com.guardians.api.statistics.mapper;


import co.com.guardians.api.statistics.dto.StatisticsDtoResp;
import co.com.guardians.model.statistics.StatisticsResp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatisticsMapper {
    StatisticsMapper MAPPER = Mappers.getMapper(StatisticsMapper.class);
    StatisticsDtoResp toEntity(StatisticsResp statisticsResp);
}
