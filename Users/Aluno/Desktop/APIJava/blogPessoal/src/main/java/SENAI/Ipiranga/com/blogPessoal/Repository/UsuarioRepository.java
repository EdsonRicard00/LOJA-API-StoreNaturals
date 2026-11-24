package SENAI.Ipiranga.com.blogPessoal.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;

@Repository  //   é um componente que é a conexão da api com o banco de dados , essa interface que estamos criando é um repositorio
	
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public List<Usuario> findBynomeContainingIgnoreCase(String nome);
	
	public Optional <Usuario> findByUsuarioAndSenha(String usuario, String senha );
	
	public Optional <Usuario> findByUsuario(String usuario);
	
}

