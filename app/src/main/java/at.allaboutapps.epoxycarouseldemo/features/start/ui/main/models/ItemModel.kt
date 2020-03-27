package at.allaboutapps.epoxycarouseldemo.features.start.ui.main.models

import at.allaboutapps.epoxycarouseldemo.R
import at.allaboutapps.epoxycarouseldemo.base.BaseViewHolder
import at.allaboutapps.epoxycarouseldemo.networking.model.Item
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item.*

@EpoxyModelClass(layout = R.layout.item)
abstract class ItemModel : EpoxyModelWithHolder<BaseViewHolder>() {

    @EpoxyAttribute
    lateinit var item: Item

    override fun bind(holder: BaseViewHolder) {
        super.bind(holder)
        with(holder) {
            tvName.text = item.name
        }
    }
}