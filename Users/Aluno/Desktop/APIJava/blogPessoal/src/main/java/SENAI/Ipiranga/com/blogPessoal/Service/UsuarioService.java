package SENAI.Ipiranga.com.blogPessoal.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;
import SENAI.Ipiranga.com.blogPessoal.Model.UsuarioLogin;
import SENAI.Ipiranga.com.blogPessoal.Repository.UsuarioRepository;

@Service // INFORMA QUE ESSA CLASSE É UM SERVIÇO, responsável por determinar as regras de negócio
public class UsuarioService {

	private UsuarioRepository repository; // vai receber os dados que serão enviados para o repositório

	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // criptografa a senha

	// Método construtor recebendo o repository
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}

	// Método para cadastrar o usuário e gravar no banco de dados
	public Usuario cadastrarUsuario(Usuario usuario) {

		if (usuario == null) {
			throw new IllegalArgumentException("Os dados do usuario não foram informados!");
		}

		if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
			throw new IllegalArgumentException("A senha deve ser informada!");
		}

		// Criptografa a senha digitada antes de salvar no banco
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return repository.save(usuario);
	}

	// Método para autenticar/logar o usuário
	public Optional<UsuarioLogin> logar(Optional<UsuarioLogin> user) {

		// Se não receber nada ou receber vazio
		
		if (user == null || user.isEmpty()) {
			return Optional.empty();
		}

		UsuarioLogin usuarioLogin = user.get();

		if (usuarioLogin.getUsuario() == null || usuarioLogin.getUsuario().isBlank()
				|| usuarioLogin.getSenha() == null || usuarioLogin.getSenha().isBlank()) {
			return Optional.empty();
		}

		// Busca o usuário no banco de dados pelo login informado
		Optional<Usuario> usuarioEncontrado = repository.findByUsuario(usuarioLogin.getUsuario());

		if (usuarioEncontrado.isEmpty()) {
			return Optional.empty();
		}

		Usuario usuarioBanco = usuarioEncontrado.get();

		// Verifica se a senha digitada bate com a senha criptografada do banco
		if (!encoder.matches(usuarioLogin.getSenha(), usuarioBanco.getSenha())) {
			return Optional.empty();
		}

		// Gera o token Basic Auth guardar o nome do usuario, 
		//adicionar o usuario e senha, que está salvo no banco de dados, 
		String auth = usuarioLogin.getUsuario() + ":" + usuarioLogin.getSenha();
		
		
		//pegar o usuario e a senha e codificar para ficar ilegivel, o base 64 é uma dependencia QUE SERVE PARA codificar e tambem pode descodificar 
		//usar 
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		//vai ser capaz de compreender assentos e Ç vai conseguir entender 
		
		
		String authHeader = "Basic " + encodedAuth; //essa informação via token para o basic, 

		usuarioLogin.setToken(authHeader);
		usuarioLogin.setId(usuarioBanco.getId());
		usuarioLogin.setNome(usuarioBanco.getName());
		usuarioLogin.setFoto(usuarioBanco.getFoto());
		usuarioLogin.setTipo(usuarioBanco.getTipo());

		return Optional.of(usuarioLogin); //vai salvar esses dados e vai enviar novamente para o 
	}
}