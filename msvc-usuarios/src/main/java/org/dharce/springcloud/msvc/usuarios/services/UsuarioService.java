package org.dharce.springcloud.msvc.usuarios.services;

import org.dharce.springcloud.msvc.usuarios.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar(); //lista  user
    Optional<Usuario> porId(Long id); //te devuelve un solo valor
    Usuario guardar(Usuario usuario); //para gaurdar los datos o un user
    void eliminar(Long id);

    Optional<Usuario> porEmail(String email);

    //Metodos remotos

    List<Usuario> listaPorIds(Iterable<Long> ids);



}
