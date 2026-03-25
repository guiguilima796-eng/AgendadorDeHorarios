package com.guigol.Agendador_Horarios.infrastructure.repository;

import com.guigol.Agendador_Horarios.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    Agendamento findyByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio,LocalDateTime dataHoraFinal);

    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento,String cliente);

    Agendamento findByDataHoraAgendamentoBetween(LocalDateTime primeiraHoraDia, LocalDateTime ultimaHoraDia);
}
