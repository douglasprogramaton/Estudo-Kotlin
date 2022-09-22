package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository


interface CustomerRepository : CrudRepository<CustomerModel, Int>, JpaSpecificationExecutor<CustomerModel> {
    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): CustomerModel?
}
