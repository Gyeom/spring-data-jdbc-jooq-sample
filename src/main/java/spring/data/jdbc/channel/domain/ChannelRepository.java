package spring.data.jdbc.channel.domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChannelRepository extends CrudRepository<Channel, Long>, ChannelJooqRepository {
    @Query("SELECT * FROM CHANNEL channel WHERE channel.id = :id FOR UPDATE")
    @Override
    Optional<Channel> findById(@Param("id") Long id);
}
