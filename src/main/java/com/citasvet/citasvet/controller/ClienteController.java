package com.citasvet.citasvet.controller;

import com.citasvet.citasvet.dto.request.ClienteActualizacionRequest;
import com.citasvet.citasvet.dto.request.ClienteRegistroRequest;
import com.citasvet.citasvet.dto.response.ClienteResponse;
import com.citasvet.citasvet.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    public ResponseEntity<ClienteResponse> registrar (@RequestBody ClienteRegistroRequest request) {
        ClienteResponse clienteResponse = clienteService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarTodos(){
        return ResponseEntity.ok(clienteService.listarTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(@PathVariable Long id,@RequestBody ClienteActualizacionRequest request){
        return ResponseEntity.ok(clienteService.actualizar(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/documento/{documento}")
    public ResponseEntity<ClienteResponse> buscarPorDocumento(@PathVariable String documento){
        return ResponseEntity.ok(clienteService.buscarPorDocumento(documento));
    }
}
