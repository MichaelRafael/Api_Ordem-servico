package br.com.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.os.enuns.Prioridade;
import br.com.os.enuns.Status;
import br.com.os.model.Cliente;
import br.com.os.model.OrdemServico;
import br.com.os.model.Tecnico;
import br.com.os.repository.ClienteRepository;
import br.com.os.repository.OrdemServicoRepository;
import br.com.os.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public void instanciaBD() {

		Tecnico t1 = new Tecnico(null, "Michael Rafael", "041.316.384-95", "(81) 98701-2423");
		Tecnico t2 = new Tecnico(null, "Bruno Henrique", "686.935.330-10", "(81) 96587-1236");
		Cliente c1 = new Cliente(null, "Betina Campos", "044.480.044-14", "(81) 98605-5885");

		OrdemServico os1 = new OrdemServico(null, Prioridade.BAIXA, "Teste create OD", Status.ANDAMENTO, t1, c1);
		
		t1.getOndensServicos().add(os1);
		c1.getOrdensSevicos().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));

	}

}
