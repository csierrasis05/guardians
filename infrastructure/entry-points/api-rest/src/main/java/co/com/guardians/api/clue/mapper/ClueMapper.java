package co.com.guardians.api.clue.mapper;

import co.com.guardians.api.clue.dto.ClueDto;
import co.com.guardians.api.clue.dto.ClueDtoResp;
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
