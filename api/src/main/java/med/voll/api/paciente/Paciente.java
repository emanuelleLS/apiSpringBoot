package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosAtualizacaoMedico;

@Table(name="pacientes")
@Entity(name= "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String CPF;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Paciente(DadosCadastroPaciente dadospaciente) {
        this.nome = dadospaciente.nome();
        this.email = dadospaciente.email();
        this.telefone = dadospaciente.telefone();
        this.CPF = dadospaciente.cpf();
        this.endereco = new Endereco(dadospaciente.endereco());
        this.ativo = true;
    }

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dadospaciente) {

            if(dadospaciente.nome()!= null){
                this.nome = dadospaciente.nome();
            }
            if (dadospaciente.telefone() != null) {
                this.telefone = dadospaciente.telefone();
            }
            if(dadospaciente.endereco() != null){
                this.endereco.atualizarInformacoes(dadospaciente.endereco());
            }
        }


}
