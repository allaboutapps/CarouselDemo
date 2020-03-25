package at.allaboutapps.epoxycarouseldemo.features.start.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import at.allaboutapps.epoxycarouseldemo.R
import at.allaboutapps.epoxycarouseldemo.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var controller: MainController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = MainController(onItemSnappedCallback = this::showItemName)
        viewModel = activityViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carousel.layoutManager = LinearLayoutManager(requireContext())
        carousel.setControllerAndBuildModels(controller)

        viewModel.currentlyActiveItemPosition.observe { position ->
            tvDummyObjectName.text = getString(R.string.selected_item_name, viewModel.items.value!![position].name)
        }

        viewModel.items.observe { items ->
            controller.items = items
        }
    }

    private fun showItemName(position: Int) {
        viewModel.currentlyActiveItemPosition.onNext(position)
    }
}