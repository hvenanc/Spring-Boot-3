package med.vol.api.service;

import med.vol.api.entities.Consulta;
import med.vol.api.entities.Medico;
import med.vol.api.entities.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.infra.ValidacaoException;
import med.vol.api.repository.ConsultaRepository;
import med.vol.api.repository.MedicoRepository;
import med.vol.api.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final ConsultaRepository consultaRepository;

    public AgendamentoService(PacienteRepository pacienteRepository, MedicoRepository medicoRepository, ConsultaRepository consultaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.consultaRepository = consultaRepository;
    }

    public void validarAgendamento(DadosAgendamentoConsulta dados) {

        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("O Paciente não está cadastrado no sistema");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("O médico não está cadastrado no sistema!");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var medico = medicoRepository.findById(dados.idMedico()).get();
        Consulta consulta = new Consulta(null,medico,paciente,dados.data());
        consultaRepository.save(consulta);

    }

    public Medico escolherMedico(DadosAgendamentoConsulta dados) {

        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("A especidade é obrigatória quando o médico não foi informado!");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}
