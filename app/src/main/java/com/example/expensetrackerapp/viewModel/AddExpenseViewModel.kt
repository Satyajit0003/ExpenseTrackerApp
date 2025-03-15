package com.example.expensetrackerapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetrackerapp.data.ExpenseDataBase
import com.example.expensetrackerapp.data.dao.ExpenseDao
import com.example.expensetrackerapp.data.model.ExpenseEntity

class AddExpenseViewModel(val dao: ExpenseDao) : ViewModel(){

    suspend fun addExpense(expenseEntity: ExpenseEntity): Boolean {
        try {
            dao.insertExpense(expenseEntity)
            return true
        } catch (ex: Throwable) {
            return false
        }
    }
}

class AddExpenseViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddExpenseViewModel::class.java)){
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            return AddExpenseViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}