package Unimed.unimedtestbackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Unimed.unimedtestbackend.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public List<Cliente> findAllByNomeCompletoContainingIgnoreCase(String nomeCompleto);
	
	public Optional<Cliente> findByCpfContainingIgnoreCase(String cpf);
	
	public Optional<Cliente> findByNumeroDaCarteirinhaContainingIgnoreCase(String numeroDaCarteirinha);
}
