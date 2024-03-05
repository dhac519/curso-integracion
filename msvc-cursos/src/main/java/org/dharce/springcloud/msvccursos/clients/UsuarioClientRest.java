package org.dharce.springcloud.msvccursos.clients;

import org.dharce.springcloud.msvccursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mavc-usuarios",url = "localhost:8001/api/usuario")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);
    @PutMapping()
    Usuario crear(@RequestBody Usuario usuario);
}
