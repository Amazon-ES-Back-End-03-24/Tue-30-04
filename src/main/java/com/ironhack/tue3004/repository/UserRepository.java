package com.ironhack.tue3004.repository;

import com.ironhack.tue3004.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // JPA
    List<User> findAllByName(String name);

    // JPQL
    @Query(value = """
        SELECT u FROM User u WHERE u.name =:name
        """)
    User findByName(@Param("name") String name);

    // JPQL
    @Query(value = "SELECT u FROM User u WHERE u.name =:username")
    List<User> findByNameParam(@Param("username") String name);

    // Native SQL
    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    List<User> findByEmail(String email);
}
