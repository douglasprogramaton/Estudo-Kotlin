package com.mercadolivro.extension

import Model.CustomerModel
import request.PostCustomerRequest

fun PostCustomerRequest.toCustomerModel():CustomerModel {
    return CustomerModel(name=this.name, email = this.email)
}
fun String.primeiraletra(): Char {
    return this.get(0)
}