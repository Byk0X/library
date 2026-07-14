package com.example.library.repository;

import com.example.library.RolesEnum;
import com.example.library.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByRole(RolesEnum role);

}
