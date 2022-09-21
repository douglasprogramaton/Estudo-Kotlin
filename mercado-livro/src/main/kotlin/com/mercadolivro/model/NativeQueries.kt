package com.mercadolivro.model

import javax.persistence.EntityManager

class NativeQueries(
    entityManager: EntityManager,
    ){
    val query= entityManager.createNativeQuery(
        "select * from customer")

}