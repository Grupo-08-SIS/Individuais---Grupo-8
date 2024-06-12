package com.example.Api_Individual_Isabel.Service


import com.example.Api_Individual_Isabel.Model.Curso
import com.example.Api_Individual_Isabel.Model.Usuario
import org.springframework.stereotype.Service

@Service
class CursoService {

    private val listaCursos: MutableList<Curso> = mutableListOf()

    fun cadastrarCurso(curso: Curso): Curso {
        val proximoId = Curso.obterProximoId(listaCursos)
        val novoCurso = curso.copy(id = proximoId)
        listaCursos.add(novoCurso)
        return novoCurso
    }

    fun listarCursos(): List<Curso> = listaCursos

    fun buscarCursoPorId(id: Long): Curso? = listaCursos.find { it.id == id }

    fun atualizarCurso(id: Long, cursoAtualizado: Curso): Curso? {
        val cursoEncontrado = listaCursos.find { it.id == id } ?: return null
        val indiceCurso = listaCursos.indexOf(cursoEncontrado)
        listaCursos[indiceCurso] = cursoAtualizado.copy(id = id)
        return listaCursos[indiceCurso]
    }

    fun deletarCurso(id: Long): Boolean {
        val cursoEncontrado = listaCursos.find { it.id == id } ?: return false
        listaCursos.remove(cursoEncontrado)
        return true
    }

    fun adicionarUsuarioAoCurso(idCurso: Long, usuario: Usuario): Boolean {
        val cursoEncontrado = listaCursos.find { it.id == idCurso } ?: return false
        cursoEncontrado.usuarios.add(usuario)
        return true
    }
}
