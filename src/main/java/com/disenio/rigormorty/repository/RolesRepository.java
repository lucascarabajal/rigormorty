package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {

    Integer countAllByIdIsNotNull();
}
