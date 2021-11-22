package co.com.oaktrees.service;

import co.com.oaktrees.entity.Pedido;
import co.com.oaktrees.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository ;

    public List<Pedido> list(){
        return pedidoRepository.findAll();
    }

    public List<Pedido> listByCorreo(String idUsuario){
        return pedidoRepository.findAllByIdUsuario(idUsuario);
    }

    public Optional<Pedido> getOne(int id){
        return pedidoRepository.findByIdPedido(id);
    }

    public Optional<Pedido> getByIdUsuario(String idUsuario){
        return pedidoRepository.findByIdUsuario(idUsuario);
    }

    public void save(Pedido persona){
        pedidoRepository.save(persona);
    }

    public void delete(int id){
        pedidoRepository.deleteByIdPedido(id);
    }

    public boolean existsById(int id){
        return pedidoRepository.existsByIdPedido(id);
    }

    public boolean existsByIdUsuario(String idUsuario){
        return pedidoRepository.existsByIdUsuario(idUsuario);
    }
}
