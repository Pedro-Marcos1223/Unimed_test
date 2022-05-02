package Unimed.unimedtestbackend.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import Unimed.unimedtestbackend.model.Plano;
import Unimed.unimedtestbackend.repository.PlanoRepository;

@RestController
@RequestMapping("/plano")
@CrossOrigin(origins = "*", allowedHeaders = "*" )
public class PlanoController {
	
	@Autowired
	private PlanoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Plano>> getAllPlano(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Plano> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Plano>> getByNomePlano(@PathVariable String nome){
				if(repository.findAllByNomeContainingIgnoreCase(nome).isEmpty()) {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum plano com esse nome foi encontrado.");
				}
				return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Plano> postPlano(@Valid @RequestBody Plano plano){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(plano));
	}
	
	@PutMapping
	public ResponseEntity<Plano> putPlano(@Valid @RequestBody Plano plano){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(plano));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
