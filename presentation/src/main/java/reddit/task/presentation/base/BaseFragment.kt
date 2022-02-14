package reddit.task.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel
import timber.log.Timber
import kotlin.reflect.KClass

/*
    BaseFragment.kt has been built for all basic view (Fragment) functionalities;
    All state, effect collection and event submission handled by this base class;
 */

abstract class BaseFragment<State, Effect, Event, Binding : ViewBinding, ViewModel : BaseViewModel<State, Effect, Event>>
    : Fragment() {

    protected abstract val screenName: String
    protected abstract val vmClazz: KClass<ViewModel>
    protected abstract val bindingCallback: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    protected val viewModel: ViewModel by lazy { getViewModel(clazz = vmClazz) }

    protected lateinit var binding: Binding

    protected open val onBind: Binding.() -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingCallback.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind(binding)

        log("$screenName is shown currently")

        lifecycleScope.launch {
            viewModel.state.collect(::onStateChanged)
        }

        lifecycleScope.launch {
            viewModel.effect.collect(::onAffected)
        }
    }

    // optional override
    open fun onStateChanged(state: State) {

    }

    // optional override
    open fun onAffected(effect: Effect) {

    }

    protected fun log(msg: String) {
        Timber.d(msg)
    }

    protected fun onEvent(event: Event) {
        viewModel.onEvent(event)
    }

}