package spring.data.jdbc.question.domain;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Table("CONTENT_LIKE")
public class Like {

    @Id
    Long id;

    Long count;
}
