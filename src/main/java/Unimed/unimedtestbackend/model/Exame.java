package Unimed.unimedtestbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_exame")
public class Exame {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nomeDoExame;
	
	@NotNull
	private String localDoExame;
	
	@NotNull
	private String dataDoExame;
	
	@ManyToOne
	@JsonIgnoreProperties("exame")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeDoExame() {
		return nomeDoExame;
	}

	public void setNomeDoExame(String nomeDoExame) {
		this.nomeDoExame = nomeDoExame;
	}

	public String getLocalDoExame() {
		return localDoExame;
	}

	public void setLocalDoExame(String localDoExame) {
		this.localDoExame = localDoExame;
	}

	public String getDataDoExame() {
		return dataDoExame;
	}

	public void setDataDoExame(String dataDoExame) {
		this.dataDoExame = dataDoExame;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
