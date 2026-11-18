package SENAI.ipiranga.com.StoreNatural.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import SENAI.ipiranga.com.StoreNatural.Model.Usuario;
import SENAI.ipiranga.com.StoreNatural.Model.UsuarioLogin;
import SENAI.ipiranga.com.StoreNatural.Repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository repository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	public Usuario cadastrarUsuario(Usuario usuario) {
		if (usuario == null) {
			throw new IllegalArgumentException("Os dados do usuário não foram informados.");
		}

		// 
		
		if (usuario.getSenha_id() == null || usuario.getSenha_id().isBlank()) {
			throw new IllegalArgumentException("A senha deve ser informada.");
		}

		if (repository.findByUsuario(usuario.getUsuario()).isPresent()) {
			throw new IllegalArgumentException("Este usuário já está cadastrado.");
		}

		String senhaEncoder = encoder.encode(usuario.getSenha_id());
		usuario.setSenha_id(senhaEncoder);

		return repository.save(usuario);
	}

	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {
		if (user == null || user.isEmpty()) {
			return Optional.empty();
		}

		UsuarioLogin usuarioLogin = user.get();

		if (usuarioLogin.getUsuario() == null || usuarioLogin.getUsuario().isBlank() ||
			usuarioLogin.getSenha() == null || usuarioLogin.getSenha().isBlank()) {
			return Optional.empty();
		}

		Optional<Usuario> usuarioEncontrado = repository.findByUsuario(usuarioLogin.getUsuario());

		if (usuarioEncontrado.isEmpty()) {
			return Optional.empty();
		}

		Usuario usuarioBanco = usuarioEncontrado.get();

		if (!encoder.matches(usuarioLogin.getSenha(), usuarioBanco.getSenha_id())) {
			return Optional.empty();
		}

		String auth = usuarioLogin.getUsuario() + ":" + usuarioLogin.getSenha();
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		String authHeader = "Basic " + encodedAuth;

		usuarioLogin.setToken(authHeader);
		usuarioLogin.setId(usuarioBanco.getId());
		usuarioLogin.setNome(usuarioBanco.getNome());
		usuarioLogin.setFoto(usuarioBanco.getFoto());
		usuarioLogin.setTipo(usuarioBanco.getTipo());
		usuarioLogin.setSenha(""); // Limpa a senha por segurança antes de retornar ao front-end

		return Optional.of(usuarioLogin);
	}
}