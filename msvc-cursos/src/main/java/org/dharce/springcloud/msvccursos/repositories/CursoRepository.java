package org.dharce.springcloud.msvccursos.repositories;

import org.dharce.springcloud.msvccursos.models.entity.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso,Long> {

}
