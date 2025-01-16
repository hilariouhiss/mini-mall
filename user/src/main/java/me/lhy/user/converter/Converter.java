package me.lhy.user.converter;

import me.lhy.user.domain.dto.UserDTO;
import me.lhy.user.domain.po.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Converter {

    Converter INSTANCE = Mappers.getMapper(Converter.class);

    UserDTO toUserDTO(User user);

    User toUser(UserDTO userDTO);

}
