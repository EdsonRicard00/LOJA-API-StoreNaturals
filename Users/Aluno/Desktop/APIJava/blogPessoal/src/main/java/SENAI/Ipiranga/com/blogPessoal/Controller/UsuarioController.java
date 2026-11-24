package SENAI.Ipiranga.com.blogPessoal.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import SENAI.Ipiranga.com.blogPessoal.Model.Usuario;
import SENAI.Ipiranga.com.blogPessoal.Model.UsuarioLogin;
import SENAI.Ipiranga.com.blogPessoal.Repository.UsuarioRepository;
import SENAI.Ipiranga.com.blogPessoal.Service.UsuarioService;

		@RestController  // no caso esse server para informar que é um controller está classe 

		@CrossOrigin(origins="*", allowedHeaders="*") // quer dizer pemitir que outras portas acessam consegue acessar, 
		// é a porta para outras apis acessar um método htpp //allowedHeaders="*" quer dizer que  qual tarefa pode entrar 
		
	@RequestMapping("/usuario")	//quando o front quiser acessar o controller quando ele for fazer uma reuisição para o controller o / usuario.
		
		
			
		public class UsuarioController {
			
			//são as injetando a dependencia da funcionalidades que estão em outras classes como >>
			
			@Autowired
			private UsuarioRepository repository;
			
			
			@Autowired
			private UsuarioService usuarioService;
				
@GetMapping   // todos os getmapping é para ir nos banco de dados fazer uma busca e trazer para o front,  para leitura e consulta
			
			
		public ResponseEntity<List<Usuario>> getAll(){return ResponseEntity.ok(repository.findAll());}
			


@GetMapping("/{id}") // aqui ele está chamando o pelo nome, o id está entre {} por que vai ser enviado dentro do endereço htpp

	
//nesse  map(resp->ResponseEntity.ok(resp))  quer dizer que vai buscar e vai voltar com um resultado. 

	public ResponseEntity<Usuario> getById(@PathVariable long id ) {
	return repository.findById(id).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	
}

@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> post(@RequestBody Usuario usuario){
	return ResponseEntity.status(HttpStatus.CREATED)
			.body(usuarioService.cadastrarUsuario(usuario));
}
@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autentication(@RequestBody Optional<UsuarioLogin>user){
	return usuarioService.logar(user).map(resp->ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

}
	@PutMapping
	public ResponseEntity<Usuario>put (@RequestBody Usuario usuario){
	return ResponseEntity.ok(repository.save(usuario));
	
}
		@DeleteMapping("/{id}")
		public void delete(@PathVariable long id) {
			repository.deleteById(id);
}
		}
