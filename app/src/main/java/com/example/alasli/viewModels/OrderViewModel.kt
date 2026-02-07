package com.example.alasli.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alasli.data.daos.OrderDao
import com.example.alasli.data.entities.OrderEntity
import kotlinx.coroutines.launch

class OrderViewModel(private val orderDao: OrderDao) : ViewModel() {

    fun addOrder(order: OrderEntity) {
        viewModelScope.launch() {
            orderDao.insert(order)
        }
    }
}