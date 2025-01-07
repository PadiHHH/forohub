package com.alurachallenge.forohub.controller;

import com.alurachallenge.forohub.domain.topico.*;
import com.alurachallenge.forohub.infra.errores.RecursoNoEncontrado;
import com.alurachallenge.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(
            @RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriBuilder) {

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionDeIntegridad("No se permite registrar tópicos duplicados");
        }

        var topico = new Topico(datos);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(
            @RequestParam(required = false) String curso,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        var page = topicoRepository.listar(curso, paginacion);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("Tópico no encontrado"));

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("Tópico no encontrado"));

        topico.actualizarInformacion(datos);

        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontrado("Tópico no encontrado"));

        topico.desactivar();
        System.out.println("Valor de activo antes de guardar: " + topico.getActivo());
        topicoRepository.save(topico);

        return ResponseEntity.noContent().build();
    }
}