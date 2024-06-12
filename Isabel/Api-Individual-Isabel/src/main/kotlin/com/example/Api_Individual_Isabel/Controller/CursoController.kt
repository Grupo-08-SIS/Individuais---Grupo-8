package com.example.Api_Individual_Isabel.Controller


import com.example.Api_Individual_Isabel.Model.Curso
import com.example.Api_Individual_Isabel.Model.Usuario
import com.example.Api_Individual_Isabel.Service.CursoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/curso")
class CursoController(private val cursoService: CursoService) {

    @PostMapping
    fun cadastrarCurso(@RequestBody curso: Curso): ResponseEntity<Curso> {
        val novoCurso = cursoService.cadastrarCurso(curso)
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso)
    }

    @GetMapping
    fun listarCursos(): ResponseEntity<List<Curso>> {
        val cursos = cursoService.listarCursos()
        return if (cursos.isEmpty()) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } else {
            ResponseEntity.status(HttpStatus.OK).body(cursos)
        }
    }

    @GetMapping("/{id}")
    fun buscarCursoPorId(@PathVariable id: Long): ResponseEntity<Curso> {
        val curso = cursoService.buscarCursoPorId(id)
        return if (curso != null) {
            ResponseEntity.status(HttpStatus.OK).body(curso)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PutMapping("/{id}")
    fun atualizarCurso(@PathVariable id: Long, @RequestBody curso: Curso): ResponseEntity<Curso> {
        val cursoAtualizado = cursoService.atualizarCurso(id, curso)
        return if (cursoAtualizado != null) {
            ResponseEntity.status(HttpStatus.OK).body(cursoAtualizado)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("/{id}")
    fun deletarCurso(@PathVariable id: Long): ResponseEntity<Void> {
        return if (cursoService.deletarCurso(id)) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @PatchMapping("/{id}/adicionar-usuario")
    fun adicionarUsuarioAoCurso(@PathVariable id: Long, @RequestBody usuario: Usuario): ResponseEntity<Void> {
        return if (cursoService.adicionarUsuarioAoCurso(id, usuario)) {
            ResponseEntity.status(HttpStatus.OK).build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
