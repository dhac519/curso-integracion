package org.dharce.springcloud.msvccursos.controllers;

import org.dharce.springcloud.msvccursos.models.Usuario;
import org.dharce.springcloud.msvccursos.models.entity.Curso;
import org.dharce.springcloud.msvccursos.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @GetMapping
    public List<Curso> listar(){
        return service.listar();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){ //Enviar Valores primitivos
        Optional<Curso> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crea(@RequestBody Curso curso){ //Enviar Objetos
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> op = service.porId(id);
        if(op.isPresent()){
            Curso cursoDB = op.get();
            cursoDB.setNombre(curso.getNombre());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cursoDB));//:)
        }
        return ResponseEntity.notFound().build();
    }

    //Metodos remotos
    @PutMapping
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario,@PathVariable Long id){
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> op = service.porId(id);
        if(op.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
