package at.allaboutapps.epoxycarouseldemo.features.start

import android.os.Bundle
import at.allaboutapps.epoxycarouseldemo.R
import at.allaboutapps.epoxycarouseldemo.base.BaseActivity
import at.allaboutapps.epoxycarouseldemo.features.start.ui.main.MainViewModel
import timber.log.Timber

class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = viewModel()
    }
}
