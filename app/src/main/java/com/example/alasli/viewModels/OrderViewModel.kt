import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.alasli.data.entities.OrderEntity
import com.example.alasli.data.repos.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OrderRepository(application)

    // Observe orders in UI
    val orders: Flow<List<OrderEntity>> = repository.getAllOrders()

    // Add order
    fun addOrder(order: OrderEntity) {
        viewModelScope.launch {
            Log.d("OrderViewModel", "Adding order: $order")
            repository.insertOrder(order)
            Log.d("OrderViewModel", "Order added successfully")
        }
    }

    // Delete order
    fun deleteOrder(order: OrderEntity) {
        viewModelScope.launch {
            Log.d("OrderViewModel", "Deleting order: ${order.id}")
            repository.deleteOrder(order)
            Log.d("OrderViewModel", "Order deleted successfully")
        }
    }
}