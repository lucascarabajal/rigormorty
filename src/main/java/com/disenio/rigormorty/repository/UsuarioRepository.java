package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByUsername(String username);
    Integer countAllByUsernameNotNull();

    List<Usuario> getAllByActivoIsTrue();

    @Query("FROM Usuario WHERE rol.id = 1 and activo = true")
    List<Usuario> getAdmins();
}
