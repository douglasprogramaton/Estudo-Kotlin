package com.mercadolivro.service

import com.mercadolivro.enuns.CustomerStatus
import com.mercadolivro.enuns.Errors
import com.mercadolivro.enuns.Profile
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(

    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getAll(name:String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel){
        val customerCopy=customer.copy(

    roles= setOf(Profile.CUSTOMER)
)
       customerRepository.save(customerCopy)
    }

    fun findById(id:Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML1101.message.format(id),Errors.ML1101.code) }
    }



    fun update(customer: CustomerModel) {
    if(!customerRepository.existsById(customer.id!!)){
        throw Exception()
    }
        customerRepository.save(customer)
    }


    fun delete(id:Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status=CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {

       return !customerRepository.existsByEmail(email)
    }

}

