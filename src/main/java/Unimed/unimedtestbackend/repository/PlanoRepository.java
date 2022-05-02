package Unimed.unimedtestbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Unimed.unimedtestbackend.model.Plano;

import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {

	public List<Plano> findAllByNomeContainingIgnoreCase(String nome);
}
