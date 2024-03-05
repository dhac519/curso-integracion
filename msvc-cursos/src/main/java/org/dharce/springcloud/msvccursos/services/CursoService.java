package org.dharce.springcloud.msvccursos.services;

import org.dharce.springcloud.msvccursos.models.Usuario;
import org.dharce.springcloud.msvccursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    //Metodos remotos relacionados al cliente Http
    //(Al ApiRest que cominicar con el otro msvc)
    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);


}
