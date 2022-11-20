package spring.data.jdbc.channel.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.data.jdbc.channel.domain.Channel;
import spring.data.jdbc.channel.domain.ChannelRepository;
import spring.data.jdbc.channel.dto.ChannelAndQuestionResponse;
import spring.data.jdbc.channel.dto.ChannelCreateRequest;
import spring.data.jdbc.channel.dto.ChannelResponse;
import spring.data.jdbc.channel.dto.ChannelUpdateRequest;
import spring.data.jdbc.exception.ErrorCode;
import spring.data.jdbc.exception.RestApiException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    @Transactional
    public ChannelResponse saveChannel(ChannelCreateRequest channelCreateRequest) {
        return ChannelResponse.from(channelRepository.save(channelCreateRequest.toEntity()));
    }

    public List<ChannelResponse> findAllChannels() {
        Iterable<Channel> iterable = () -> channelRepository.findAll().iterator();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(ChannelResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }

    @Transactional
    public ChannelResponse modifyChannel(final Long channelId, final ChannelUpdateRequest channelUpdateRequest) {

        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new RestApiException(ErrorCode.RESOURCE_NOT_FOUND));

        channel.changeName(channelUpdateRequest.getName());
        channel.changeDescription(channelUpdateRequest.getDescription());

        return ChannelResponse.from(channelRepository.save(channel));
    }

    public List<ChannelAndQuestionResponse> findChannelAndQuestions(final Long channelId) {
        return channelRepository.findChannelById(channelId);
    }
}
