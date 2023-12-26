package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.medico.DadosListagemMedicos;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

//para o Spring carregar a classe, usa-se @RestController e @RequestMapping
@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository repository;
    //@PostMapping = se chegar uma requisicao para a url /pacientes, vai chamar o seguinte metodo
    @PostMapping
    @Transactional
    public void cadastrarPacientes(@RequestBody @Valid DadosCadastroPaciente dadospaciente){
         repository.save(new Paciente(dadospaciente));

    }

    @GetMapping
    public Page<DadosListagemPacientes> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findByAtivoTrue(paginacao).map(DadosListagemPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dadospaciente){
        var paciente = repository.getReferenceById(dadospaciente.id());
        paciente.atualizarInformacoes(dadospaciente);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }

}
