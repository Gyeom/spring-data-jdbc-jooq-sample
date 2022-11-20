package spring.data.jdbc.user.dto;

import lombok.Value;
import spring.data.jdbc.user.domain.User;

@Value
public class UserCreateRequest {

    String id;
    String name;

    public User toEntity() {
        return User.builder()
                .id(id)
                .name(name)
                .build();
    }
}
