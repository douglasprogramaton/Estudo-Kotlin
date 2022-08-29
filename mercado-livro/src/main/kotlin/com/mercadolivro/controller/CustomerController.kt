package com.mercadolivro.controller

import Model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import request.PostCustomerRequest
import request.PutCustomerRequest

@RestController
@RequestMapping("Customer")
class CustomerController {

    val customers= mutableListOf<CustomerModel>()

    @GetMapping
    //? para dizer que a variavel pode ser null
    fun getAll(@RequestParam name: String? ): List<CustomerModel> {
        name?.let{
            return customers.filter{it.name.contains(name,ignoreCase = true)}
        }
    return customers

    }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {

       var id = if(customers.isEmpty()) {/* se a lista estiver avasia o id será 1, diferente na maioria
           das linguagens o if em Kotlin retorna um valor */
           1

       }else{
           customers.last().id.toInt()+1
       }.toString()//oque vier do valor do if que seja numero ou string sera trasnformado para string


        customers.add(CustomerModel( id, customer.name,customer.email))
      println(customer)

    }
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id:String): CustomerModel {
      return customers.filter{it.id==id}.first()// para encontrar o primeiro registro que estejá de acordo com o valor
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id:String,@RequestBody customer: PutCustomerRequest) {
        customers.filter { it.id == id }.first().let {
            it.name=customer.name
            it.email=customer.email
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id:String) {
       customers.removeIf{it.id==id
        }
    }
}
