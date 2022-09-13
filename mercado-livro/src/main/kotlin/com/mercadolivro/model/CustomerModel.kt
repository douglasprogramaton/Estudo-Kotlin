package com.mercadolivro.model

import com.mercadolivro.enuns.CustomerStatus
import org.hibernate.Hibernate
import javax.persistence.*


@Entity(name="customer")
data class CustomerModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?=null,
    @Column
    var name:String,
    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus
  ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CustomerModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()


}