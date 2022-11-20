package spring.data.jdbc.user.dto;

import lombok.Getter;
import lombok.Value;
import spring.data.jdbc.user.domain.User;

@Getter
@Value
public class UserResponse {
    String id;
    String name;

    public static UserResponse from(final User user) {
        return new UserResponse(user.getId(), user.getName());
    }
}
