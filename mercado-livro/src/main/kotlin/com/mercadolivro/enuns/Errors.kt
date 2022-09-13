package com.mercadolivro.enuns

enum class Errors( val code:String, val message:String ) {
    ML001("ML-001", "Invalid Request"),
    ML1001("ML-1001","Book [ %s] not exists" ),
    ML1002("ML-1002","Cannot update book wth status[%s]"),
    ML1101("ML-1101","Book [ %s] not exists" )
}
