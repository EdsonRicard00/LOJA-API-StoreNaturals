package SENAI.ipiranga.com.StoreNatural.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SENAI.ipiranga.com.StoreNatural.Model.Usuario;

@Repository
		public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
		public List<Usuario> findByContainingIgonoreCase(String nome);
		
		public Optional<Usuario> findByUsuarioAndSenha(String usuario,String senha_id,String email);
		
		public Optional<Usuario> findByUsuario (String usuario);
		
		
	

}
