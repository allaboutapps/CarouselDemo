package at.allaboutapps.epoxycarouseldemo.features.start.ui.main

import at.allaboutapps.epoxycarouseldemo.custom.CustomSnappingCarouselModel_
import at.allaboutapps.epoxycarouseldemo.features.start.ui.main.models.ItemModel_
import at.allaboutapps.epoxycarouseldemo.networking.model.Item
import com.airbnb.epoxy.EpoxyController

class MainController(private val onItemSnappedCallback: (Int) -> Unit) : EpoxyController() {

    var items: List<Item> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
            if (value.isNotEmpty()) {
                onItemSnappedCallback(0)
            }
        }

    override fun buildModels() {
        val itemModels = items.map { item ->
            ItemModel_()
                    .id(item.id)
                    .item(item)
        }
        showItems(itemModels)
    }

    private fun showItems(itemModels: List<ItemModel_>) {

        val numberOfViewsOnScreen = if (items.size > 1) 1.1f else 1f

        val carouselModel = CustomSnappingCarouselModel_()
                .id("carousel")
                .numViewsToShowOnScreen(numberOfViewsOnScreen)
                .models(itemModels)
                .onBind { _, view, _ ->
                    view.setSnapHelperCallback {
                        onItemSnappedCallback(it)
                    }
                }

        carouselModel.addTo(this)
    }
}