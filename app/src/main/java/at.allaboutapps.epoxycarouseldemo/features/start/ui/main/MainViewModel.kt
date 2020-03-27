package at.allaboutapps.epoxycarouseldemo.features.start.ui.main

import androidx.lifecycle.ViewModel
import at.allaboutapps.epoxycarouseldemo.networking.model.Item
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    val items = BehaviorSubject.createDefault(itemsDefault)
    val currentActiveItemPosition = BehaviorSubject.createDefault(0)

    companion object {
        val itemsDefault: List<Item> = listOf(
                Item(1, "Item 1"),
                Item(2, "Item 2"),
                Item(3, "Item 3"),
                Item(4, "Item 4"),
                Item(5, "Item 5"))
    }
}

