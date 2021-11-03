package flickrapp.com.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {
    fun <R> async(
        onStart: suspend () -> R,
        onComplete: suspend (result: R) -> Unit = {},
        onError: (e: Exception) -> Unit = {},
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch {
            withContext(dispatcher) {
                try {
                    val result = onStart()
                    onComplete.invoke(result)
                } catch (e: Exception) {
                    onError.invoke(e)
                }
            }
        }
    }
}