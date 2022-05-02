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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/plano")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Controller de plano")
public class PlanoController {

	@Autowired
	private PlanoRepository repository;

	
	@Operation(summary = "Busca todos os planos")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso em buscar todos os planos", content = @Content),
			@ApiResponse(responseCode = "404", description = "Planos não encontrados.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping
	public ResponseEntity<List<Plano>> getAllPlano() {
		return ResponseEntity.ok(repository.findAll());
	}
	

	@Operation(summary = "Busca um plano por id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Plano encontrado com sucesso!", content = @Content),
			@ApiResponse(responseCode = "404", description = "Plano não encontrado.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Plano> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}

	
	@Operation(summary = "Busca um plano por nome do plano")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Plano encontrado com sucesso!", content = @Content),
			@ApiResponse(responseCode = "404", description = "Plano não encontrado.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Plano>> getByNomePlano(@PathVariable String nome) {
		if (repository.findAllByNomeContainingIgnoreCase(nome).isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum plano com esse nome foi encontrado.");
		}
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	
	@Operation(summary = "Cadastra um Plano")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Plano cadastrado com sucesso!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Plano.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@PostMapping
	public ResponseEntity<Plano> postPlano(@Valid @RequestBody Plano plano) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(plano));
	}

	
	@Operation(summary = "Atualiza um Plano")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Plano atualizado com sucesso!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Plano.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@PutMapping
	public ResponseEntity<Plano> putPlano(@Valid @RequestBody Plano plano) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(plano));
	}

	
	@Operation(summary = "Deleta um Plano do banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Plano deletado com sucesso!", content = @Content) })
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
