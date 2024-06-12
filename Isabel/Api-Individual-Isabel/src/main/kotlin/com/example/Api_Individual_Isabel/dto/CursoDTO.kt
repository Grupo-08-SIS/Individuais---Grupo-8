package com.example.Api_Individual_Isabel.dto

import java.time.LocalDate


data class CursoDTO(
    val id: Long? = null,
    var nome: String,
    var descricao: String,
    var dataInicio: LocalDate,
    var dataFim: LocalDate,
    val usuarios: List<UsuarioDTO> = listOf()
)

data class UsuarioDTO(
    val id: Long,
    val nome: String,
    val email: String
)
