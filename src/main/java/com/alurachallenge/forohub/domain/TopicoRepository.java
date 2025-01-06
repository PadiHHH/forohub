package com.alurachallenge.forohub.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("""
            SELECT t FROM Topico t
            WHERE t.activo = true
            AND (:curso IS NULL OR t.curso = :curso)
            """)
    Page<DatosRespuestaTopico> listar(String curso, Pageable paginacion);
}