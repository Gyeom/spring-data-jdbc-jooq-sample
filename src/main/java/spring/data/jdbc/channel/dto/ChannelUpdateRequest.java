package spring.data.jdbc.channel.dto;

import lombok.Value;
import spring.data.jdbc.channel.domain.Channel;

@Value
public class ChannelUpdateRequest {
    String name;
    String description;

    public Channel toEntity() {
        return Channel.builder()
                .name(name)
                .description(description)
                .build();
    }
}
