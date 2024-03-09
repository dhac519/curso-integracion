package org.dharce.springcloud.msvccursos.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos_usuario")
public class CursoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "usuario_id") // Se elimino unique = true -> se debe crear un metodo para validar la asignacion de un usuario a un solo curso
    private Long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    //

    @Override
    public boolean equals(Object obj) {
       if(this==obj){
           return true;
       }
       if(!(obj instanceof  CursoUsuario)){
           return false;
       }
       CursoUsuario o = (CursoUsuario) obj;
       return this.usuarioId!=null && this.usuarioId.equals(o.usuarioId);
    }
}
