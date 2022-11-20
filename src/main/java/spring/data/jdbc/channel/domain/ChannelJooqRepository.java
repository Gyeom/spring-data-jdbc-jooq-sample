package spring.data.jdbc.channel.domain;

import spring.data.jdbc.channel.dto.ChannelAndQuestionResponse;

import java.util.List;

public interface ChannelJooqRepository {
    List<ChannelAndQuestionResponse> findChannelById(Long channelId);
}
