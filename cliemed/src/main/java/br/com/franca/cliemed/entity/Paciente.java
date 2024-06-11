package br.com.franca.cliemed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paciente")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Paciente extends Pessoa{

    private String Convenio;

    private String Plano;

    private String numeroCarteiraConvenio;

    private String validadeCarteiraConvenio;



}