package com.connectedReads.repositories;

import com.connectedReads.entities.User;
import com.connectedReads.entities.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByRole(Role role);
    Optional<User> findByUserName(String userName);
}
