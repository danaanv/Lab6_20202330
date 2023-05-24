package com.example.lab6_sol.repository;

import com.example.lab6_sol.entity.Curso;
import com.example.lab6_sol.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByRolid(int rol);

    @Query(value = "INSERT INTO registro_db.registro (usuarioid, cursoid, fecharegistro) \n" +
            "VALUES (?1,?2,now());", nativeQuery = true)
    void crearRegistro(int id_usuario, int id_curso);

    @Query(value = "SELECT * FROM registro_db.registro WHERE usuarioid=?1",nativeQuery = true)
    List<Curso> listarCursos(int id_usuario);
}
