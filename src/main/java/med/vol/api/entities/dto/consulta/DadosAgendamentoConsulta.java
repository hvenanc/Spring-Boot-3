package med.vol.api.entities.dto.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.entities.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
