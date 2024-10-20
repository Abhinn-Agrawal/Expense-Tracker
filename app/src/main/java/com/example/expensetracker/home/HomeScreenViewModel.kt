package com.example.expensetracker.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensetracker.R
import com.example.expensetracker.widget.Utils
import com.example.expensetracker.data.ExpenseDatabase
import com.example.expensetracker.data.ExpenseDao
import com.example.expensetracker.data.ExpenseEntity

class HomeScreenViewModel(dao: ExpenseDao):ViewModel() {
    val expenses = dao.getAllExpenses()

    fun getBalance(list:List<ExpenseEntity>):String{
        var total = 0.00
        list.forEach{
            if(it.type == "Income")
                total += it.amount
            else
                total -= it.amount
        }
        return Utils.formatToDecimalValue(total)
    }

    fun getTotalExpense(list:List<ExpenseEntity>):String{
        var total = 0.00
        list.forEach{
            if(it.type == "Expense")
                total -= it.amount
        }
        return Utils.formatToDecimalValue(total)
    }

    fun getTotalIncome(list:List<ExpenseEntity>):String{
        var total = 0.00
        list.forEach{
            if(it.type == "Income")
                total += it.amount
        }
        return Utils.formatToDecimalValue(total)
    }

}

class HomeViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
            val dao = ExpenseDatabase.getDatabase(context).expenseDao()
            @Suppress("UNCHECKED_CAST")
            return HomeScreenViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
