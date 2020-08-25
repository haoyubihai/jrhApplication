package com.example.jrhapplication.flowtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.transform

/**
 ***************************************
 * 项目名称:jrhApplication
 * @Author jiaruihua
 * 邮箱：jiaruihua@ksjgs.com
 * 创建时间: 2020/8/21     3:20 PM
 * 用途:
 *
 *      viewModel.uiState.onEachEvent {
 *          when (it) {
 *              is UiState.ContentShow -> {
 *                  tvContent.text = it.content + "${System.currentTimeMillis()}"
 *              }
 *              is UiState.SubmitShow -> {
 *                  tvContent.text = "object---${System.currentTimeMillis()}"
 *              }
 *          }
 *      }.launchIn(lifecycleScope)
 *
 *

 ***************************************
 */
class FlowViewModel : ViewModel() {

    val uiState: StateFlow<Event<UiState>> get() = _state

    var _state = MutableStateFlow<Event<UiState>>(Event(UiState.None))


    var submitVersion = 0

    /**
     * 每次submitVersion 的值更新 ，会实时收到刷新
     */
    fun submit() {
        _state.value = Event(UiState.ContentShow("${++submitVersion}show-time"))
    }

    fun submitSame() {
        val state = UiState.ContentShow("${submitVersion}show-time")
        _state.value = Event(state)
    }

    fun submitObjectValue() {
        _state.value = Event(UiState.SubmitShow)
    }
}


sealed class UiState(var id: Long = System.currentTimeMillis()) {

    object None : UiState()
    object SubmitShow : UiState()
    data class ContentShow(val content: String) : UiState()
}


/**
 *  T 使用一次既失效
 */
open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false


    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}


/**
 *  为了捕获每次发送的event状态，扩展 ，
 *
 * Returns a flow which performs the given [action] on each value of the original flow's [Event].
 */
fun <T> Flow<Event<T?>>.onEachEvent(action: suspend (T) -> Unit): Flow<T> = transform { value ->
    value.getContentIfNotHandled()?.let {
        action(it)
        return@transform emit(it)
    }
}