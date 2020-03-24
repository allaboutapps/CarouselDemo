package at.allaboutapps.epoxycarouseldemo.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import at.allaboutapps.epoxycarouseldemo.di.Injectable
import at.allaboutapps.epoxycarouseldemo.di.viewmodel.ViewModelFactory
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

/**
 * Base class to use for this application
 */
abstract class BaseFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected var clearOnDestroyViewDisposeOnDestroy = CompositeDisposable()

    /**
     * Request a ViewModel from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> viewModel() = ViewModelProviders.of(this, viewModelFactory).get(T::class.java)

    /**
     * Request a ViewModel scoped to the Activity from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> activityViewModel() = ViewModelProviders.of(requireActivity(), viewModelFactory).get(T::class.java)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.requestApplyInsets()
    }

    /**
     * Subscribe until `onDestroy` event
     */
    protected inline fun <T> Observable<T>.observe(crossinline action: (T) -> Unit) {
        clearOnDestroyViewDisposeOnDestroy += subscribe { action(it) }
    }

    override fun onDestroyView() {
        clearOnDestroyViewDisposeOnDestroy.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        clearOnDestroyViewDisposeOnDestroy.dispose()
        super.onDestroy()
    }
}