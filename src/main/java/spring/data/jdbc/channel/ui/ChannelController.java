package spring.data.jdbc.channel.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.data.jdbc.channel.application.ChannelService;
import spring.data.jdbc.channel.dto.ChannelAndQuestionResponse;
import spring.data.jdbc.channel.dto.ChannelCreateRequest;
import spring.data.jdbc.channel.dto.ChannelResponse;
import spring.data.jdbc.channel.dto.ChannelUpdateRequest;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping("/channels")
    public ResponseEntity<ChannelResponse> createChannel(@RequestBody ChannelCreateRequest channelCreateRequest) {
        ChannelResponse channel = channelService.saveChannel(channelCreateRequest);
        return ResponseEntity.created(URI.create("/channels/" + channel.getId())).body(channel);
    }

    @GetMapping("/channels")
    public ResponseEntity<List<ChannelResponse>> getChannels() {
        return ResponseEntity.ok().body(channelService.findAllChannels());
    }

    @PutMapping("/channels/{channelId}")
    public ResponseEntity<ChannelResponse> modifyChannel(@RequestBody ChannelUpdateRequest channelUpdateRequest,
                                                         @PathVariable final Long channelId) {
        return ResponseEntity.ok(channelService.modifyChannel(channelId, channelUpdateRequest));
    }

    @GetMapping("/channels/{channelId}/questions")
    public ResponseEntity<List<ChannelAndQuestionResponse>> getChannelAndQuestion(@PathVariable final Long channelId) {
        return ResponseEntity.ok().body(channelService.findChannelAndQuestions(channelId));
    }
}
