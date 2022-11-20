package spring.data.jdbc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Configuration
public class JdbcConfig extends AbstractJdbcConfiguration {

    @Bean
    @Order
    BeforeSaveCallback<?> validateBeforeSave(Validator validator) {
        return (aggregate, change) -> {
            Set<ConstraintViolation<Object>> violations = validator.validate(aggregate);

            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            return aggregate;
        };
    }
}
