package br.com.franca.cliemed.repositories;

import br.com.franca.cliemed.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
