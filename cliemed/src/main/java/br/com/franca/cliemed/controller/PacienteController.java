package br.com.franca.cliemed.controller;

import br.com.franca.cliemed.entity.Endereco;
import br.com.franca.cliemed.entity.Paciente;
import br.com.franca.cliemed.usecase.PacienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Paciente", description = "API de pacientes")
@RestController
@RequestMapping("/v1/pacientes")
public class PacienteController {

    private final PacienteUseCase pacienteUseCase;

    @Autowired
    public PacienteController(PacienteUseCase pacienteUseCase) {
        this.pacienteUseCase = pacienteUseCase;
    }


    @Operation(summary = "Lista todos os pacientes cadastrados no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Paciente encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)) }),
        @ApiResponse(responseCode = "400", description = "ID inválido.", content = @Content),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado.", content = @Content) })
    @GetMapping("/listarPacientes")
    public List<Paciente> listarPacientes() {
        return pacienteUseCase.listarPacientes();
    }

    @Operation(summary = "Busca um paciente pelo seu id no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.", content = @Content) })
    @GetMapping("/buscarPacientePorId/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteUseCase.buscarPacientePorId(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cadastro de pacientes no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente cadastrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.", content = @Content) })
    @PostMapping("/salvarPaciente")
    public ResponseEntity<Paciente> salvarPaciente(@RequestBody Paciente paciente) {

        return ResponseEntity.ok(pacienteUseCase.salvarPaciente(paciente));

    }

    @Operation(summary = "Atualiza paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente atualizado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.", content = @Content) })
    @PutMapping("/atualizaPaciente/{id}")
    public ResponseEntity<Paciente> atualizaPaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetails) {
        Optional<Paciente> optionalPaciente = pacienteUseCase.buscarPacientePorId(id);
        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            paciente.setNome(pacienteDetails.getNome());
            paciente.setCpf(pacienteDetails.getCpf());
            paciente.setDataNascimento(pacienteDetails.getDataNascimento());
            paciente.setEmail(pacienteDetails.getEmail());
            paciente.setTelefone(pacienteDetails.getEmail());
            paciente.setCelular(pacienteDetails.getEmail());
            paciente.setNomePai(pacienteDetails.getEmail());
            paciente.setNomeMae(pacienteDetails.getEmail());
            paciente.setConvenio(pacienteDetails.getConvenio());
            paciente.setPlano(pacienteDetails.getPlano());
            paciente.setNumeroCarteiraConvenio(pacienteDetails.getNumeroCarteiraConvenio());
            paciente.setValidadeCarteiraConvenio(pacienteDetails.getValidadeCarteiraConvenio());

            return ResponseEntity.ok(pacienteUseCase.salvarPaciente(paciente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deleta paciente pelo id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente deletado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Paciente.class)) }),
            @ApiResponse(responseCode = "400", description = "ID inválido.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Paciente não encontrado.", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        if (pacienteUseCase.buscarPacientePorId(id).isPresent()) {
            pacienteUseCase.deletarPaciente(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
