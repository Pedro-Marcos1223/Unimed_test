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

import Unimed.unimedtestbackend.model.Cliente;
import Unimed.unimedtestbackend.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Controller de Cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@Operation(summary = "Busca todos os clientes.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso em buscar todos os clientes!", content = @Content),
			@ApiResponse(responseCode = "404", description = "Clientes não encontrados.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllCliente() {
		return ResponseEntity.ok(repository.findAll());
	}
	

	@Operation(summary = "Busca um cliente pelo id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!", content = {
					@Content(schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	

	@Operation(summary = "Busca um cliente pelo cpf")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!", content = @Content),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/cpfcliente/{cpf}") 
	public ResponseEntity<Cliente> getByCpf(@PathVariable String cpf){
		return repository.findByCpfContainingIgnoreCase(cpf).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	
	@Operation(summary = "Busca um cliente pelo numero da carteirinha")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!", content = @Content),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/carteirinha/{numeroDaCarteirinha}") 
	public ResponseEntity<Cliente> getByNumeroDaCarteirinha(@PathVariable String numeroDaCarteirinha){
		return repository.findByNumeroDaCarteirinhaContainingIgnoreCase(numeroDaCarteirinha)
				.map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	
	@Operation(summary = "Busca um cliente pelo nome")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso!", content = @Content),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/nome/{nomeCompleto}")
	public ResponseEntity<List<Cliente>> getAllByNome(@PathVariable String nomeCompleto) {
		return ResponseEntity.ok(repository.findAllByNomeCompletoContainingIgnoreCase(nomeCompleto));
	}

	
	@Operation(summary = "Cadastra um cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@PostMapping
	public ResponseEntity<Cliente> postCliente(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(cliente));
	}

	
	@Operation(summary = "Atualiza um cliente cadastrado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@PutMapping
	public ResponseEntity<Cliente> putCliente(@RequestBody @Valid Cliente cliente) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(cliente));
	}
	
	
	@Operation(summary = "Deleta um cliente do banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso!", content = @Content) })
	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
