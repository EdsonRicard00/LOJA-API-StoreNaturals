package SENAI.Ipiranga.com.blogPessoal.Controller;

import java.util.List;

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

import SENAI.Ipiranga.com.blogPessoal.Model.Tema;
import SENAI.Ipiranga.com.blogPessoal.Repository.TemaRepository;

	@RestController // é para informar que é um controle

	@CrossOrigin(origins = "*", allowedHeaders ="*") // esse é para permitir que outras acessos http pode acessar,

	@RequestMapping("/tema")

		public class TemaController {
			
			@Autowired
			private TemaRepository repository; 
			
			@GetMapping
			public ResponseEntity<List<Tema>> getAll(){
				return ResponseEntity.ok(repository.findAll());
			}
			
			// fazendo uma busca  pelo parametro, tema/descricao via json ou via htpps
			@GetMapping("/descricao/{descricao}")
			public ResponseEntity<List<Tema>> getAllByDescricaoContainingIgnoreCase(
					@PathVariable String descricao){
				return ResponseEntity.ok(repository.findByDescricaoContainingIgnoreCase(descricao));
				
			}
			@GetMapping("/{id}")
			public ResponseEntity<List<Tema>> GetById (@PathVariable long id){
				return ResponseEntity.ok(repository.findById(id));
			}
			
			@PostMapping
			public ResponseEntity<Tema> post (@RequestBody Tema tema){
				return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
				
			}
			
			@PutMapping
			public ResponseEntity<Tema> put (@RequestBody Tema tema){
				return ResponseEntity.ok(repository.save(tema));
				
			}
			
			@DeleteMapping("/{id}")
			public void delete(@PathVariable long id) {
				repository.deleteById(id);
			}
			
			}
	
