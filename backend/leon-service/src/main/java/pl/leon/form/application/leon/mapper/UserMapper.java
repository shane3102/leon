package pl.leon.form.application.leon.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.leon.form.application.leon.model.response.UserResponse;
import pl.leon.form.application.leon.repository.entities.UserEntity;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse mapToResponse(UserEntity userEntity);
}
