package at.allaboutapps.epoxycarouseldemo.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import at.allaboutapps.epoxycarouseldemo.networking.model.DummyObject
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import kotlinx.android.synthetic.main.item_dummy_object.view.*
import timber.log.Timber


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CustomSnappingCarousel @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Carousel(context, attrs, defStyleAttr) {

    fun setSnapHelperCallback(callback: (Int) -> Unit) {
        this.onFlingListener = null
        (this as RecyclerView).attachSnapHelperWithListener(LinearSnapHelper(), SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL, object : OnSnapPositionChangeListener {
            override fun onSnapPositionChange(position: Int) {
                callback.invoke(position)
            }
        })
    }
}

interface OnSnapPositionChangeListener {
    fun onSnapPositionChange(position: Int)
}


class SnapOnScrollListener(
        private val snapHelper: SnapHelper,
        var behavior: Behavior = Behavior.NOTIFY_ON_SCROLL,
        var onSnapPositionChangeListener: OnSnapPositionChangeListener? = null
) : RecyclerView.OnScrollListener() {

    enum class Behavior {
        NOTIFY_ON_SCROLL,
        NOTIFY_ON_SCROLL_STATE_IDLE
    }

    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL) {
            maybeNotifySnapPositionChange(recyclerView)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE
                && newState == RecyclerView.SCROLL_STATE_IDLE) {
            maybeNotifySnapPositionChange(recyclerView)
        }
    }

    private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            onSnapPositionChangeListener?.onSnapPositionChange(snapPosition)
            this.snapPosition = snapPosition
        }
    }
}

fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
    val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
    val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
    return layoutManager.getPosition(snapView)
}

fun RecyclerView.attachSnapHelperWithListener(
        snapHelper: SnapHelper,
        behavior: SnapOnScrollListener.Behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,
        onSnapPositionChangeListener: OnSnapPositionChangeListener) {
    snapHelper.attachToRecyclerView(this)
    val snapOnScrollListener = SnapOnScrollListener(snapHelper, behavior, onSnapPositionChangeListener)
    addOnScrollListener(snapOnScrollListener)
}