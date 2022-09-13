package com.mercadolivro.model

import org.hibernate.Hibernate
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name="purchase")
data class PurchaseModel(
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

var id: Int?= null,
@ManyToOne
@JoinColumn(name = "customer_id")
val customer: CustomerModel,
@ManyToMany
@JoinTable(name="purchase_book",
    joinColumns = [JoinColumn(name="purchase_id")],
inverseJoinColumns = [JoinColumn(name="book_id")])
var books: MutableList<BookModel>,

val nfe:String?=null,

@Column
val price:BigDecimal,

@Column(name="created_at")
val createdAt:LocalDateTime = LocalDateTime.now()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as PurchaseModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

}