package at.allaboutapps.epoxycarouseldemo.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import kotlinx.android.synthetic.main.item_dummy_object.view.*
import timber.log.Timber


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CustomSnappingCarousel @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Carousel(context, attrs, defStyleAttr) {

    var selectedPosition = 0

    fun setSnapHelperCallback(callback: (String) -> Unit) {
        val snapHelper = CustomLinearSnapHelper(callback)
        //workaround - do not remove
        //https://stackoverflow.com/questions/44043501/an-instance-of-onflinglistener-already-set-in-recyclerview/52850198
        this.onFlingListener = null
        snapHelper.attachToRecyclerView(this)
    }

    inner class CustomLinearSnapHelper(private val callback: ((String) -> Unit)?) : LinearSnapHelper() {

        override fun findSnapView(layoutManager: LayoutManager): View? {
            val view = super.findSnapView(layoutManager)

            if (view != null) {
                val name = view.tvDummyName.text.toString()
                val newPosition = layoutManager.getPosition(view)
                if (newPosition != selectedPosition) {
                    callback?.invoke(name) ?:
                    run {
                        Timber.e("No callback connected to SnapEvent")
                    }
                    selectedPosition = newPosition
                }

            }

            return view
        }
    }
}
