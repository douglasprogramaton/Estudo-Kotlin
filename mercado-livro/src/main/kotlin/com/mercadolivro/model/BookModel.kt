package com.mercadolivro.model

import com.mercadolivro.enuns.BookStatus
import com.mercadolivro.enuns.Errors
import com.mercadolivro.exception.BadRequestException
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity
data class BookModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int?=null,
    @Column
    var name:String,
    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn
    var customer: CustomerModel?=null

        ) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                throw BadRequestException(Errors.ML1002.message.format(field),Errors.ML1002.code)
            }
            field = value
        }

    constructor(
        id: Int? = null,
        name: String,
        price: BigDecimal,
        customer: CustomerModel? = null,
        status: BookStatus?):this(id,name,price,customer){
            this.status=status
        }



}

