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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Unimed.unimedtestbackend.model.Exame;
import Unimed.unimedtestbackend.repository.ExameRepository;

@RestController
@RequestMapping("/exame")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExameController {

	@Autowired
	private ExameRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Exame>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Exame> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nomeExame/{nomeDoExame}")
	public ResponseEntity<List<Exame>> getAllByNomeExame(@PathVariable String nomeExame){
		return ResponseEntity.ok(repository.findAllByNomeDoExameContainingIgnoreCase(nomeExame));
	}
	
	@GetMapping("/local/{localDoExame}")
	public ResponseEntity<List<Exame>> getAllByLocalDoExame(@PathVariable String localDoExame){
		return ResponseEntity.ok(repository.findAllByLocalDoExameContainingIgnoreCase(localDoExame));
	}
	
	@GetMapping("/dataDoExame/{dataDoExame}")
	public ResponseEntity<List<Exame>> getAllByData(@PathVariable String dataDoExame){
		return ResponseEntity.ok(repository.findAllByDataDoExameContainingIgnoreCase(dataDoExame));
	}
	
	@PostMapping
	public ResponseEntity<Exame> postExame(@Valid @PathVariable Exame exame){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(exame));
	}
	
	@PutMapping
	public ResponseEntity<Exame> puttExame(@Valid @PathVariable Exame exame){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(exame));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
