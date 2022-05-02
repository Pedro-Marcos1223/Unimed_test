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

import Unimed.unimedtestbackend.model.Cliente;
import Unimed.unimedtestbackend.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllCliente(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nomeCompleto}")
	public ResponseEntity<List<Cliente>> getAllByNome(@PathVariable String nomeCompleto){
		return ResponseEntity.ok(repository.findAllByNomeCompletoContainingIgnoreCase(nomeCompleto));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> postCliente (@PathVariable @Valid Cliente cliente){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
	}
	
	@PutMapping
	public ResponseEntity<Cliente> putCliente (@PathVariable @Valid Cliente cliente){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(cliente));
	}

	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
