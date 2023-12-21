package br.com.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.os.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
	Pessoa findByCpf(@Param("cpf")String cpf);

}
