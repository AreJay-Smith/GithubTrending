package com.example.data.mapper

interface EntityMappe<E, D> {

    fun mapFromEntity(enitity: E): D

    fun mapToEntity(domain: D): E

}