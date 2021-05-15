package xyz.linin.bookstore_backend.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.linin.bookstore_backend.entity.UserAuth;

public interface UserAuthRepository extends CrudRepository<UserAuth, Integer> {

}
