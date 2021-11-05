package flickrapp.com.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    fun <R> async(
        onStart: suspend () -> R,
        onComplete: suspend (result: R) -> Unit = {},
        onError: (e: Exception) -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(context = dispatcher) {
            try {
                val result = onStart()
                viewModelScope.launch(context = Dispatchers.Main) {
                    onComplete.invoke(result)
                }
            } catch (e: Exception) {
                viewModelScope.launch(context = Dispatchers.Main) {
                    onError.invoke(e)
                }
            }
        }
    }
}