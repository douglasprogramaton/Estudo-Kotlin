package com.mercadolivro.service

import com.mercadolivro.enuns.BookStatus
import com.mercadolivro.enuns.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Suppress("UNUSED_VARIABLE")
@Service
class BookService (
    val bookRepository: BookRepository
        ) {
    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML1001.message.format(id), Errors.ML1001.code) }
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)

    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO

        }
        bookRepository.saveAll(books)
    }

    fun findAllByIds(bookIds: Set<Int>):List<BookModel> {
        return bookRepository.findAllById(bookIds).toList()
    }


}