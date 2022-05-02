package Unimed.unimedtestbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Unimed.unimedtestbackend.model.Exame;

@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

	public List<Exame> findAllByNomeDoExameContainingIgnoreCase(String nomeDoExame);
	
	public List<Exame> findAllByLocalDoExameContainingIgnoreCase(String localDoExame);
	
	public List<Exame> findAllByDataDoExameContainingIgnoreCase(String dataDoExame);

}
