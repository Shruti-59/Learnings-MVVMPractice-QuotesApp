package com.example.quotesapp.utilities

import com.example.quotesapp.data.FakeDatabase
import com.example.quotesapp.data.QuoteRepository
import com.example.quotesapp.data.QuotesViewModelFactory

object InjectorUtils {
    // This will be called from QuotesActivity
    fun provideQuotesViewModelFactory(): QuotesViewModelFactory {
        // ViewModelFactory needs a repository, which in turn needs a DAO from a database
        // The whole dependency tree is constructed right here, in one place
        val quoteRepository = QuoteRepository.getInstance(FakeDatabase.getInstance().quoteDao)
        return QuotesViewModelFactory(quoteRepository)
    }
}