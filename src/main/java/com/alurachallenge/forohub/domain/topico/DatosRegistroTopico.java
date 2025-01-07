package com.alurachallenge.forohub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroTopico(
        @NotBlank(message = "Título es obligatorio")
        String titulo,
        @NotBlank(message = "Mensaje es obligatorio")
        String mensaje,
        @NotBlank(message = "Autor es obligatorio")
        String autor,
        @NotBlank(message = "Curso es obligatorio")
        String curso
) {}