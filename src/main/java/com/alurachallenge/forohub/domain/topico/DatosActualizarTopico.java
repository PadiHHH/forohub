package com.alurachallenge.forohub.domain.topico;

public record DatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        String curso,
        StatusTopico status
) {}
