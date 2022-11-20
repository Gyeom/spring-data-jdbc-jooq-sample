package spring.data.jdbc.user.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jdbc.exception.ErrorCode;
import spring.data.jdbc.exception.RestApiException;
import spring.data.jdbc.user.domain.User;
import spring.data.jdbc.user.domain.UserRepository;
import spring.data.jdbc.user.dto.UserCreateRequest;
import spring.data.jdbc.user.dto.UserResponse;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse saveUser(UserCreateRequest userCreateRequest) {
        return UserResponse.from(userRepository.save(userCreateRequest.toEntity()));
    }

    public UserResponse getUser(final String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));
        return UserResponse.from(user);
    }
}
