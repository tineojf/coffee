package com.cavoshcoffee.backend.repository;

import com.cavoshcoffee.backend.entity.Usuario;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

}
