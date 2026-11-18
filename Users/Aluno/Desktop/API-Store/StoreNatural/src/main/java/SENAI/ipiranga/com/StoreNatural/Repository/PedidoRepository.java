package SENAI.ipiranga.com.StoreNatural.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.ipiranga.com.StoreNatural.Model.Pedido;

@Repository

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
	
	public List<Pedido> findBynomeContainingIgnoreCase(String nome);

	public Optional<Pedido> findByUsuario (String usuario_id );
	
	public Optional<Pedido> findByProduto (String produto_id );

	

}
