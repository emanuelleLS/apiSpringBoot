package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
         //verifica se é nulo ou vazio
        @NotBlank
        String nome,
         @NotBlank
         @Email
        String email,
        @NotBlank
         String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // de 4 a 6 digitos para o crn
        String crm,
        @NotNull //nao usa NotBlank pois nao é String
        Especialidade especialidade,
        @NotNull
         @Valid
        DadosEndereco endereco) {

}
