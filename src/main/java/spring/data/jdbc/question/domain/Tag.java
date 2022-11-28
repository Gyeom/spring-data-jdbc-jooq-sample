package spring.data.jdbc.question.domain;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
@Builder
public class Tag {

    @Id
    Long id;

    String name;
}
