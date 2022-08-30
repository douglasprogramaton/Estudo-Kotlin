package com.mercadolivro.Service

import Model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import request.PutCustomerRequest

/**@Servive para avisar o spring que a classe CustomerService é um serviço*/
@Service
class CustomerService {

    val customers= mutableListOf<CustomerModel>()

    //? para dizer que a variavel pode ser null
    fun getAll(name: String? ): List<CustomerModel> {
        name?.let{
            return customers.filter{it.name.contains(name,ignoreCase = true)}
        }
        return customers

    }

    fun createCustomer(customer: CustomerModel) {

        var id = if(customers.isEmpty()) {/* se a lista estiver avasia o id será 1, diferente na maioria
           das linguagens o if em Kotlin retorna um valor */
            1

        }else{
            customers.last().id!!.toInt()+1
        }.toString()//oque vier do valor do if que seja numero ou string sera trasnformado para string


        customers.add(customer)
        println(customer)

    }
    fun getCustomer(id:String): CustomerModel {
        return customers.filter{it.id==id}.first()// para encontrar o primeiro registro que estejá de acordo com o valor
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(id:String,@RequestBody customer: PutCustomerRequest) {
        customers.filter { it.id == id }.first().let {
            it.name=customer.name
            it.email=customer.email
        }
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(id:String) {
        customers.removeIf{it.id==id
        }
    }
}