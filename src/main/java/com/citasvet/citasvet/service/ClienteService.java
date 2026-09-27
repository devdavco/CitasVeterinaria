    package com.citasvet.citasvet.service;

    import com.citasvet.citasvet.model.Cliente;
    import com.citasvet.citasvet.repository.ClienteRepository;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class ClienteService {
        private final ClienteRepository clienteRepository;

        public ClienteService(ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
        }

        public Cliente registrar(Cliente cliente) {
            if(clienteRepository.existsByDocumento(cliente.getDocumento())){
                throw new IllegalArgumentException("Ya existe un cliente con el documento " + cliente.getDocumento());
            }
            return clienteRepository.save(cliente);
        }

        public List<Cliente> listarTodos(){
            return clienteRepository.findAll();
        }

        public Cliente buscarPorId(Long id){
            return clienteRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No existe el cliente con el id: " + id));
        }

        public Cliente actualizar(Long id, Cliente datos){
            Cliente existente = buscarPorId(id); //Llama al cliente existente por id
            existente.setNombre(datos.getNombre());
            existente.setApellido(datos.getApellido());
            existente.setTelefono(datos.getTelefono());
            existente.setFechaNacimiento(datos.getFechaNacimiento());
            existente.setDireccion(datos.getDireccion());
            existente.setEmail(datos.getEmail());

            return clienteRepository.save(existente);
        }
        public void eliminar(Long id){
            Cliente porBorrar = buscarPorId(id);
            clienteRepository.delete(porBorrar);
        }

    }
