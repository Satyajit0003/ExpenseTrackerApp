package com.example.expensetrackerapp.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetrackerapp.data.ExpenseDataBase
import com.example.expensetrackerapp.data.dao.ExpenseDao

class StatsViewModel(val dao: ExpenseDao) : ViewModel(){

}

class StatsViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StatsViewModel::class.java)){
            val dao = ExpenseDataBase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return StatsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}