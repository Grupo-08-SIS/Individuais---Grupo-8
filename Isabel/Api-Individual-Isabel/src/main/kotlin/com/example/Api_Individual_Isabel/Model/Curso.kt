package com.example.Api_Individual_Isabel.Model


import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate

data class Curso(
    val id: Long,
    var nome: String,
    var descricao: String,
    var dataInicio: LocalDate,
    var dataFim: LocalDate,
    val usuarios: MutableList<Usuario> = mutableListOf()
) {
    init {
        validarCampos()
    }

    fun validarCampos() {
        if (nome.isBlank()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome do curso é obrigatório")
        }
        if (descricao.isBlank()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Descrição do curso é obrigatória")
        }
        if (dataInicio.isAfter(dataFim)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de início deve ser antes da data de fim")
        }
    }

    companion object {
        fun obterProximoId(listaCursos: List<Curso>): Long {
            return if (listaCursos.isEmpty()) {
                1
            } else {
                listaCursos.last().id + 1
            }
        }
    }
}
