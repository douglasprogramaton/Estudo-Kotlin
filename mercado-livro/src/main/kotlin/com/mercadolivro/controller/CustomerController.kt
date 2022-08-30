package com.mercadolivro.controller

import Model.CustomerModel
import com.mercadolivro.Service.CustomerService
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

class CustomerController (
    /** Gerando a dependencia para Service "customerService"*/
    val customerService: CustomerService
        ){

/**O controller recebe a requisição  chama o customerService e o customerService retorna
 * a resposta para o usuário */
    @GetMapping
    fun getAll(@RequestParam name: String? ): List<CustomerModel> {
     return customerService.getAll(name)
    }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
       customerService.createCustomer(customer.toCustomerModel())
    }
    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id:String): CustomerModel {
      return customerService.getCustomer(id)
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id:String,@RequestBody customer: PutCustomerRequest) {
    customerService.update(id,customer)    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id:String) {
   customerService.delete(id)
        }

}
