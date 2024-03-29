package org.dharce.springcloud.msvc.usuarios.services;

import org.dharce.springcloud.msvc.usuarios.clients.CursoClienteRest;
import org.dharce.springcloud.msvc.usuarios.models.entity.Usuario;
import org.dharce.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired //para inyectar la dependencia de una clase con metodos
    private UsuarioRepository repository;

    @Autowired
    private CursoClienteRest cursoClienteRest;

    @Override
    @Transactional (readOnly = true) //springframework.transaction.annotation - solo de lectura
    public List<Usuario> listar() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Usuario> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
        cursoClienteRest.eliminarCursoUsuarioPorId(id);
    }
//    public void eliminar(Long id) {
//        repository.deleteById(id);
//    }

    @Override
    public Optional<Usuario> porEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listaPorIds(Iterable<Long> ids) {
        return (List<Usuario>) repository.findAllById(ids);
    }


}
