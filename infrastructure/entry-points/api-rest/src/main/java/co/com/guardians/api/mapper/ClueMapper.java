package co.com.guardians.api.mapper;

import co.com.guardians.api.dto.ClueDto;
import co.com.guardians.api.dto.ClueDtoResp;
import co.com.guardians.model.clue.Clue;
import co.com.guardians.model.clue.ClueResp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClueMapper {

    ClueMapper MAPPER = Mappers.getMapper(ClueMapper.class);

    Clue toModel(ClueDto clueDto);

    ClueDtoResp toEntity(ClueResp clueResp);

}
