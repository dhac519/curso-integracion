package org.dharce.springcloud.msvccursos.services;

import org.dharce.springcloud.msvccursos.clients.UsuarioClientRest;
import org.dharce.springcloud.msvccursos.models.Usuario;
import org.dharce.springcloud.msvccursos.models.entity.Curso;
import org.dharce.springcloud.msvccursos.models.entity.CursoUsuario;
import org.dharce.springcloud.msvccursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class CursoServiceImpl  implements  CursoService{
    @Autowired
    private CursoRepository repository;
    @Autowired
    private UsuarioClientRest clientRest;
    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
    repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);
        if(o.isPresent()){
            Usuario usuarioMsvc = clientRest.detalle(usuario.getId());
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());
            curso.addCursoUsuarios(cursoUsuario);

            repository.save(curso);
            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);
        if(o.isPresent()){
            Usuario usuarioNewMsvc = clientRest.crear(usuario);
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNewMsvc.getId());
            curso.addCursoUsuarios(cursoUsuario);

            repository.save(curso);
            return Optional.of(usuarioNewMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        Optional<Curso> o = repository.findById(cursoId);
        if(o.isPresent()){
            Usuario usuarioMsvc = clientRest.detalle(usuario.getId());
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            curso.removeCursoUsuario(cursoUsuario);

            repository.save(curso);
            return Optional.of(usuarioMsvc);
        }

        return Optional.empty();
    }
}
