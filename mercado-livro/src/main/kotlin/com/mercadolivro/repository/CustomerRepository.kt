package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.*


interface CustomerRepository : CrudRepository<CustomerModel, Int>, JpaSpecificationExecutor<CustomerModel> {
    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): CustomerModel?
    @Query(
        value = "SELECT * FROM customer WHERE id = :id ",
        nativeQuery = true
    )
    fun listByCustomerId(@Param("id") id: Int): Optional<CustomerModel>

}
