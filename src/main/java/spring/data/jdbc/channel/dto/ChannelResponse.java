package spring.data.jdbc.channel.dto;

import lombok.Value;
import spring.data.jdbc.channel.domain.Channel;

@Value
public class ChannelResponse {
    Long id;
    String name;
    String description;
    String writer;

    public static ChannelResponse from(final Channel channel) {
        return new ChannelResponse(channel.getId(), channel.getName(), channel.getDescription(), channel.getCreatedBy().getId());
    }
}
