package me.lhy.user.converter;

import me.lhy.user.domain.dto.UserDto;
import me.lhy.user.domain.po.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Converter {

    Converter INSTANCE = Mappers.getMapper(Converter.class);

    UserDto toUserDTO(User user);

    User toUser(UserDto userDTO);

}
