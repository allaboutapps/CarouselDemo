package at.allaboutapps.epoxycarouseldemo.features.start.ui.main

import at.allaboutapps.epoxycarouseldemo.custom.CustomSnappingCarouselModel_
import at.allaboutapps.epoxycarouseldemo.features.start.ui.main.models.DummyObjectModel_
import at.allaboutapps.epoxycarouseldemo.networking.model.DummyObject
import com.airbnb.epoxy.EpoxyController

class MainController(private val onItemSnappedCallback: (Int) -> Unit) : EpoxyController() {

    var items: List<DummyObject> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
            if (value.isNotEmpty()) {
                onViewSnapped(0)
            }
        }

    override fun buildModels() {
        val itemModels = items.map { dummyObject ->
            DummyObjectModel_()
                    .id(dummyObject.id)
                    .item(dummyObject)
        }
        showItems(itemModels)
    }

    private fun showItems(dummyObjectModels: List<DummyObjectModel_>) {

        val numberOfViewsOnScreen = if (items.size > 1) 1.1f else 1f

        val carouselModel = CustomSnappingCarouselModel_()
                .id("dummyObjectCarousel")
                .numViewsToShowOnScreen(numberOfViewsOnScreen)
                .models(dummyObjectModels)
                .onBind { _, view, _ ->
                    view.setSnapHelperCallback {
                        onViewSnapped(it)
                    }
                }

        carouselModel.addTo(this)
    }

    private fun onViewSnapped(position: Int) {
        onItemSnappedCallback.invoke(position)
    }
}