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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/exame")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Controller de exame")
public class ExameController {

	@Autowired
	private ExameRepository repository;
	
	
	@Operation(summary = "Busca todos os exames.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Sucesso em buscar todos os exames!", content = @Content),
			@ApiResponse(responseCode = "404", description = "Exames não encontrados.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping
	public ResponseEntity<List<Exame>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	
	@Operation(summary = "Busca um exame pelo id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Exame encontrado com sucesso!", content = {
					@Content(schema = @Schema(implementation = Exame.class)) }),
			@ApiResponse(responseCode = "404", description = "Exame não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<Exame> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	
	@Operation(summary = "Busca exames por nome de exame.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Exame encontrado com sucesso!", content = {
					@Content(schema = @Schema(implementation = Exame.class)) }),
			@ApiResponse(responseCode = "404", description = "Exame não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/nomeExame/{nomeDoExame}")
	public ResponseEntity<List<Exame>> getAllByNomeExame(@PathVariable String nomeExame){
		return ResponseEntity.ok(repository.findAllByNomeDoExameContainingIgnoreCase(nomeExame));
	}
	
	
	@Operation(summary = "Busca exames pela unidade medica.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Exame encontrado com sucesso!", content = {
					@Content(schema = @Schema(implementation = Exame.class)) }),
			@ApiResponse(responseCode = "404", description = "Exame não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/local/{localDoExame}")
	public ResponseEntity<List<Exame>> getAllByLocalDoExame(@PathVariable String localDoExame){
		return ResponseEntity.ok(repository.findAllByLocalDoExameContainingIgnoreCase(localDoExame));
	}
	
	
	@Operation(summary = "Busca exames por data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Exame encontrado com sucesso!", content = {
					@Content(schema = @Schema(implementation = Exame.class)) }),
			@ApiResponse(responseCode = "404", description = "Exame não encontrado", content = @Content),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@GetMapping("/dataDoExame/{dataDoExame}")
	public ResponseEntity<List<Exame>> getAllByData(@PathVariable String dataDoExame){
		return ResponseEntity.ok(repository.findAllByDataDoExameContainingIgnoreCase(dataDoExame));
	}
	
	
	@Operation(summary = "Cadastra um exame")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Exame cadastrado com sucesso!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Exame.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@PostMapping
	public ResponseEntity<Exame> postExame(@Valid @PathVariable Exame exame){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(exame));
	}
	
	
	@Operation(summary = "Atualiza um exame")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Exame atualizado com sucesso!", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Exame.class)) }),
			@ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação", content = @Content) })
	@PutMapping
	public ResponseEntity<Exame> puttExame(@Valid @PathVariable Exame exame){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(exame));
	}
	
	
	@Operation(summary = "Deleta um exame do banco de dados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso!", content = @Content) })
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
