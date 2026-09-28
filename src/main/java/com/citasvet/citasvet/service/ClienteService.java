    package com.citasvet.citasvet.service;

    import com.citasvet.citasvet.model.Cliente;
    import com.citasvet.citasvet.repository.ClienteRepository;
    import com.citasvet.citasvet.repository.MascotaRepository;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class ClienteService {
        private final ClienteRepository clienteRepository;
        private final MascotaRepository mascotaRepository;


        public ClienteService(ClienteRepository clienteRepository, MascotaRepository mascotaRepository) {
            this.clienteRepository = clienteRepository;
            this.mascotaRepository = mascotaRepository;
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
            if(mascotaRepository.existsByClienteId(id)){
                throw  new IllegalStateException("No se puede eliminar cliente con mascotas");

            }
            clienteRepository.delete(porBorrar);
        }

    }
