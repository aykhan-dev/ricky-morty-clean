package reddit.task.presentation.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrollEnded(block: () -> Unit) {
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                block()
            }
        }
    })
}