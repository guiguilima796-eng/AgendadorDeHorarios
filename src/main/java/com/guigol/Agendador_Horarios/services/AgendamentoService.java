package com.guigol.Agendador_Horarios.services;

import com.guigol.Agendador_Horarios.infrastructure.entity.Agendamento;
import com.guigol.Agendador_Horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento){
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

        Agendamento agendados = agendamentoRepository.findyByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(),horaAgendamento,horaFim);

        if(Objects.nonNull(agendados)){
            throw new RuntimeException("Horário já está preenchido");
        }

        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente){

        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento,cliente);


    }

    public Agendamento buscarAgendamentosDia(LocalDateTime data){
        LocalDateTime primeiraHoraDia = data.toLocalDate().atStartOfDay();
        LocalDateTime horaFinalDia = data.toLocalDate().atTime(23,59);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia,horaFinalDia);

    }

    public  Agendamento

}
