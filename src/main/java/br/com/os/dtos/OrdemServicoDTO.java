package br.com.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import br.com.os.enuns.Prioridade;
import br.com.os.enuns.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.os.model.OrdemServico;

public class OrdemServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAFechamento;

	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBSERVAÇOES é querido!")
	private String observacoes;
	
	private Integer status;
	
	private Long tecnico;
	
	private Long cliente;
	
	public OrdemServicoDTO() {
		super();
	}

	public OrdemServicoDTO(OrdemServico obj) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataAFechamento = obj.getDataAFechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacoes = obj.getObservacoes();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataAFechamento() {
		return dataAFechamento;
	}

	public void setDataAFechamento(LocalDateTime dataAFechamento) {
		this.dataAFechamento = dataAFechamento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getTecnico() {
		return tecnico;
	}

	public void setTecnico(Long tecnico) {
		this.tecnico = tecnico;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}
}
