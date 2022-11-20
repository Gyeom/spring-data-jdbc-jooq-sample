package spring.data.jdbc.question.domain;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long>, QuestionJooqRepository {
    List<Question> findQuestionsByChannelId(Long channelId);

    @Modifying
    @Query("UPDATE QUESTION SET VERSION = VERSION + 1, STATUS = :status WHERE ID = :id")
    boolean changeStatus(@Param("id") Long id, @Param("status") Status status);
}
