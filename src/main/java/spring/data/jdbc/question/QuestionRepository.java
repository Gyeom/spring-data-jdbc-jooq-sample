package spring.data.jdbc.question;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
    List<Question> findQuestionsByChannelId(Long channelId);

    List<Question> findByTitleLikeAndStatus(String titleStartAt, Status status, Pageable pageable);

    @Query("SELECT COUNT(*) FROM QUESTION WHERE title LIKE :titleStartAt AND status = :status")
    long countByTitleLikeAndStatus(@Param("titleStartAt") String titleStartAt, @Param("status") Status status);

    @Modifying
    @Query("UPDATE QUESTION SET VERSION = VERSION + 1, STATUS = :status WHERE ID = :id")
    boolean changeStatus(@Param("id") String Long, @Param("status") Status status);
}
