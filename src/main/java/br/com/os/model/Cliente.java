package br.com.os.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> ordensSevicos = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OrdemServico> getOrdensSevicos() {
		return ordensSevicos;
	}

	public void setOrdensSevicos(List<OrdemServico> ordensSevicos) {
		this.ordensSevicos = ordensSevicos;
	}

}
