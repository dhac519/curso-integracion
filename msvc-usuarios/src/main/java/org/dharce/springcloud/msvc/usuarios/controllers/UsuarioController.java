package org.dharce.springcloud.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import org.dharce.springcloud.msvc.usuarios.models.entity.Usuario;
import org.dharce.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping ("/api/usuario") //
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService service;


    @GetMapping
    public List<Usuario> listar(){
     return service.listar();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){ //Enviar Valores primitivos
        Optional<Usuario> usuarioOptional = service.porId(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crea(@Valid  @RequestBody Usuario usuario, BindingResult result){ //Enviar Objetos


        if(service.porEmail(usuario.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("Arce Cotrina","Ya existe un usuario con ese e-mail"));
        }
        if(result.hasErrors()){
            Map<String, String> errores=new HashMap<>();
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), "Arce Cotrina " + err.getField() +
                        " " + err.getDefaultMessage());
            });
            return    ResponseEntity.badRequest().body(errores);

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));

    }



    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){


        if(result.hasErrors()){
          return  validar(result);
        }

        Optional<Usuario> op = service.porId(id);
        if(op.isPresent()){
            Usuario usuarioDB = op.get();
            if (!usuario.getEmail().equalsIgnoreCase(usuarioDB.getEmail()) && service.porEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.badRequest().body(Collections.singletonMap("Arce Cotrina","Ya existe el email en la data"));
            }
            usuarioDB.setNombre(usuario.getNombre());
            usuarioDB.setEmail(usuario.getEmail());
            usuarioDB.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDB));//:)
        }

        return ResponseEntity.notFound().build();
    }
    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores=new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "Arce Cotrina " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> op = service.porId(id);
        if(op.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids){
        return  ResponseEntity.ok(service.listaPorIds(ids));

    }
}
