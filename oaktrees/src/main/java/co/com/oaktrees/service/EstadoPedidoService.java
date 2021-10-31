package co.com.oaktrees.service;

import co.com.oaktrees.entity.EstadoPedido;
import co.com.oaktrees.repository.EstadoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstadoPedidoService {

    @Autowired
    EstadoPedidoRepository estadoPedidoRepository;

    public List<EstadoPedido> list(){
        return estadoPedidoRepository.findAll();
    }

    public Optional<EstadoPedido> getOne(int id){
        return estadoPedidoRepository.findById(id);
    }

    public Optional<EstadoPedido> getByNombre(String nombre){
        return estadoPedidoRepository.findByNombre(nombre);
    }

    public void save(EstadoPedido estadoPedido){
        estadoPedidoRepository.save(estadoPedido);
    }

    public void delete(int id){
        estadoPedidoRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return estadoPedidoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return estadoPedidoRepository.existsByNombre(nombre);
    }
}
