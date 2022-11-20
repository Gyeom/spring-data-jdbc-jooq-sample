package spring.data.jdbc.channel.dto;

import lombok.Value;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import spring.data.jdbc.channel.domain.Channel;

@Value
public class ChannelCreateRequest {
    String name;
    String description;
    String writer;

    public Channel toEntity() {
        return Channel.builder()
                .version(0L)
                .name(name)
                .description(description)
                .createdBy(AggregateReference.to(writer))
                .build();
    }
}
