package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CustomerQueryNative: JpaRepository<CustomerModel, Int> {

   @Query(
      value = "SELECT * FROM customer WHERE id = :userId ",
      nativeQuery = true
   )
   fun listByCustomerId(@Param("userId") userId: String): CustomerModel

   }






