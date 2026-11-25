package SENAI.ipiranga.com.StoreNatural.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.ipiranga.com.StoreNatural.Model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findByNomeContainingIgnoreCase(String nome);

}