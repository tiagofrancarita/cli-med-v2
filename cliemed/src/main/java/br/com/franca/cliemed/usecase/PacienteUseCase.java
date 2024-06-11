package br.com.franca.cliemed.usecase;

import br.com.franca.cliemed.entity.Endereco;
import br.com.franca.cliemed.entity.Paciente;
import br.com.franca.cliemed.repositories.EnderecoRepository;
import br.com.franca.cliemed.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class PacienteUseCase {

    private final PacienteRepository pacienteRepository;
    private final EnderecoRepository enderecoRepository;


    private final Logger log = Logger.getLogger(PacienteUseCase.class.getName());

    @Autowired
    public PacienteUseCase(PacienteRepository pacienteRepository, EnderecoRepository enderecoRepository) {
        this.pacienteRepository = pacienteRepository;
        this.enderecoRepository = enderecoRepository;
    }


    public List<Paciente> listarPacientes(){
        log.info("Listando pacientes");
        return pacienteRepository.findAll();
    }

    public Paciente salvarPaciente(Paciente paciente){
        log.info("Salvando paciente");

        for (int i = 0; i< paciente.getEnderecos().size(); i++) {
           paciente.getEnderecos().get(i).setPessoa(paciente);

        }

        paciente = pacienteRepository.save(paciente);

        return paciente;
    }

    public void deletarPaciente(Long id){
        log.info("Deletando paciente");
        pacienteRepository.deleteById(id);
    }

    public Paciente atualizarPaciente(Paciente paciente){
        log.info("Atualizando paciente");
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPacientePorId(Long id){
        log.info("Buscando paciente");
        return pacienteRepository.findById(id);
    }




}
