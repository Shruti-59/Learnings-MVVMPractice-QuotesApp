package com.example.quotesapp.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.quotesapp.R

import com.example.quotesapp.data.Quote
import com.example.quotesapp.data.QuotesViewModel
import com.example.quotesapp.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_quotes.*

class QuotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("QuotesActivity", "onCreate: ")
        setContentView(R.layout.activity_quotes)
        initializeUi()
    }

    private fun initializeUi() {
        // Get the QuotesViewModelFactory with all of it's dependencies constructed
        val factory = InjectorUtils.provideQuotesViewModelFactory()
        // Use ViewModelProviders class to create / get already created QuotesViewModel
        // for this view (activity)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(QuotesViewModel::class.java)

        // Observing LiveData from the QuotesViewModel which in turn observes
        // LiveData from the repository, which observes LiveData from the DAO ☺
        viewModel.getQuotes().observe(this, Observer { quotes ->
            val stringBuilder = StringBuilder()
            quotes.forEach { quote ->
                stringBuilder.append("$quote\n\n")
            }
           textView_quotes.text = stringBuilder.toString()
        })

        // When button is clicked, instantiate a Quote and add it to DB through the ViewModel
      button_add_quote.setOnClickListener {
            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
            viewModel.addQuote(quote)
          editText_quote.setText("")
          editText_author.setText("")
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("QuotesActivity", "onStart: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("QuotesActivity", "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i("QuotesActivity", "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i("QuotesActivity", "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i("QuotesActivity", "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("QuotesActivity", "onDestroy: ")
    }
}