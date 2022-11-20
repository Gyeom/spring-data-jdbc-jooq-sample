package spring.data.jdbc.channel.domain;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import spring.data.jdbc.channel.dto.ChannelAndQuestionResponse;

import java.util.List;

import static spring.data.jdbc.tables.Channel.CHANNEL;
import static spring.data.jdbc.tables.Question.QUESTION;

@RequiredArgsConstructor
public class ChannelJooqRepositoryImpl implements ChannelJooqRepository {

    private final DSLContext dsl;

    @Override
    public List<ChannelAndQuestionResponse> findChannelById(final Long channelId) {
        return dsl.select(
                        CHANNEL.NAME,
                        QUESTION.TITLE.as("questionTitle"),
                        QUESTION.CREATED_BY.as("questionWriter")
                ).from(CHANNEL)
                .leftJoin(QUESTION).on(QUESTION.CHANNEL_ID.eq(CHANNEL.ID))
                .where(CHANNEL.ID.eq(channelId)).fetchInto(ChannelAndQuestionResponse.class);
    }
}
