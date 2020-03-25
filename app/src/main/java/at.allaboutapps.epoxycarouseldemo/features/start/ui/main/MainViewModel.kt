package at.allaboutapps.epoxycarouseldemo.features.start.ui.main

import androidx.lifecycle.ViewModel
import at.allaboutapps.epoxycarouseldemo.networking.model.DummyObject
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    val items = BehaviorSubject.createDefault(itemsDefault)
    val currentActiveItemPosition = BehaviorSubject.createDefault(0)

    companion object {
        val itemsDefault: List<DummyObject> = listOf(
                DummyObject(1, "Item 1"),
                DummyObject(2, "Item 2"),
                DummyObject(3, "Item 3"),
                DummyObject(4, "Item 4"),
                DummyObject(5, "Item 5"))
    }
}

