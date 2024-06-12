package com.example.Api_Individual_Isabel.Service

import com.example.Api_Individual_Isabel.Model.Curso
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class CursoServiceTest {

    private lateinit var cursoService: CursoService

    @BeforeEach
    fun setUp() {
        cursoService = CursoService()
    }

    @Test
    fun `deve cadastrar um novo curso`() {
        val curso = Curso(1, "Kotlin Basics", "Aprenda os fundamentos do Kotlin", LocalDate.now(), LocalDate.now().plusDays(30))
        val novoCurso = cursoService.cadastrarCurso(curso)
        assertNotNull(novoCurso)
        assertEquals(1, cursoService.listarCursos().size)
    }

    @Test
    fun `deve listar todos os cursos`() {
        val cursos = cursoService.listarCursos()
        assertTrue(cursos.isEmpty())
    }

    @Test
    fun `deve buscar um curso por ID`() {
        val curso = Curso(1, "Kotlin Basics", "Aprenda os fundamentos do Kotlin", LocalDate.now(), LocalDate.now().plusDays(30))
        cursoService.cadastrarCurso(curso)
        val cursoEncontrado = cursoService.buscarCursoPorId(1)
        assertNotNull(cursoEncontrado)
    }

    @Test
    fun `deve atualizar um curso existente`() {
        val curso = Curso(1, "Kotlin Basics", "Aprenda os fundamentos do Kotlin", LocalDate.now(), LocalDate.now().plusDays(30))
        cursoService.cadastrarCurso(curso)
        val cursoAtualizado = Curso(1, "Kotlin Avançado", "Aprenda conceitos avançados do Kotlin", LocalDate.now(), LocalDate.now().plusDays(60))
        val atualizado = cursoService.atualizarCurso(1, cursoAtualizado)
        assertNotNull(atualizado)
        assertEquals("Kotlin Avançado", atualizado?.nome)
    }

    @Test
    fun `deve deletar um curso`() {
        val curso = Curso(1, "Kotlin Basics", "Aprenda os fundamentos do Kotlin", LocalDate.now(), LocalDate.now().plusDays(30))
        cursoService.cadastrarCurso(curso)
        val deletado = cursoService.deletarCurso(1)
        assertTrue(deletado)
        assertTrue(cursoService.listarCursos().isEmpty())
    }
}
