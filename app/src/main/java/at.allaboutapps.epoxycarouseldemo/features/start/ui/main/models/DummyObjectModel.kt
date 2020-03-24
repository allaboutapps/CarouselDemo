package at.allaboutapps.epoxycarouseldemo.features.start.ui.main.models

import at.allaboutapps.epoxycarouseldemo.R
import at.allaboutapps.epoxycarouseldemo.base.BaseViewHolder
import at.allaboutapps.epoxycarouseldemo.networking.model.DummyObject
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_dummy_object.*

@EpoxyModelClass(layout = R.layout.item_dummy_object)
abstract class DummyObjectModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    lateinit var item: DummyObject

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        with(holder) {
            tvDummyName.text = item.name
        }
    }
}