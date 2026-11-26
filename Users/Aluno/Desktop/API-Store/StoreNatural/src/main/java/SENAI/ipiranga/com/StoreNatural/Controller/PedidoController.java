package SENAI.ipiranga.com.StoreNatural.Controller;

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

import SENAI.ipiranga.com.StoreNatural.Model.Pedido;
import SENAI.ipiranga.com.StoreNatural.Repository.PedidoRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository repository;

	@GetMapping
	public ResponseEntity<List<Pedido>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Pedido> post(@RequestBody Pedido pedido) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(pedido));
	}

	@PutMapping
	public ResponseEntity<Pedido> put(@RequestBody Pedido pedido) {
		return repository.findById(pedido.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(pedido)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		return repository.findById(id).map(resp -> {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}