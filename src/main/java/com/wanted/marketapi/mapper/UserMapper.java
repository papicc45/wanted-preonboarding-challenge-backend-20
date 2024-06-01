package com.wanted.marketapi.mapper;

import com.wanted.marketapi.domain.User;
import com.wanted.marketapi.dto.UserRequestDTO;
import com.wanted.marketapi.dto.UserResponseDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface UserMapper {
    UserResponseDTO userToDTO(User user);

    @Mapping(target = "id", ignore = true)
    User dtoToUser(UserRequestDTO userRequestDTO);
}
