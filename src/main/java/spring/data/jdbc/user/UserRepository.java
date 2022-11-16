package spring.data.jdbc.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    @Override
    void deleteById(String id);

    @Override
    void delete(User entity);

    @Override
    void deleteAll(Iterable<? extends User> entities);

    @Override
    void deleteAll();
}
