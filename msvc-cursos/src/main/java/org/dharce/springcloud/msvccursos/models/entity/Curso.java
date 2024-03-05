package org.dharce.springcloud.msvccursos.models.entity;

import jakarta.persistence.*;
import org.dharce.springcloud.msvccursos.models.Usuario;

import java.util.LinkedList;
import java.util.List;


@Entity
@Table (name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursoUsuario> cursoUsuarios;
    @Transient
    private List<Usuario> usuarios;

    public Curso(){
        cursoUsuarios = new LinkedList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<CursoUsuario> getCursoUsuarios() {
        return cursoUsuarios;
    }
    public void setCursoUsuarios(List<CursoUsuario> cursoUsuarios) {
        this.cursoUsuarios = cursoUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void addCursoUsuarios(CursoUsuario cursoUsuario){
        cursoUsuarios.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursoUsuario cursoUsuario){
        cursoUsuarios.remove(cursoUsuario);
    }
}
