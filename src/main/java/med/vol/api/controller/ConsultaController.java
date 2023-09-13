package med.vol.api.controller;
import jakarta.validation.Valid;
import med.vol.api.entities.dto.consulta.DadosAgendamentoConsulta;
import med.vol.api.entities.dto.consulta.DadosDetalhamentoConsulta;
import med.vol.api.repository.ConsultaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private final ConsultaRepository repository;

    public ConsultaController(ConsultaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        System.out.println(dados);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
    }
}
