package spring.data.jdbc.channel.dto;

import lombok.Value;

@Value
public class ChannelAndQuestionResponse {
    String name;
    String questionTitle;
    String questionWriter;
}
