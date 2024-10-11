package com.example.pokedex.utils

fun transforTime(milisegundos: Long): String {
    val segundos = (milisegundos / 1000) % 60
    val minutos = (milisegundos / (1000 * 60) % 60)
    val horas = (milisegundos / (1000 * 60 * 60) % 24)
    val dias = (milisegundos / (1000 * 60 * 60 * 24))

    return "$dias días, $horas horas, $minutos minutos, $segundos segundos"
}