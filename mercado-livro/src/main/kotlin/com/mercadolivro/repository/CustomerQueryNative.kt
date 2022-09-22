package com.mercadolivro.repository

import com.mercadolivro.model.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CustomerQueryNative: JpaRepository<CustomerModel, Int> {

   @Query("SELECT * FROM name WHERE customer = ?1", nativeQuery = true)
   fun  findCustomer(customerModel: CustomerModel){
      var lista = listOf<CustomerModel>()


   }



}


