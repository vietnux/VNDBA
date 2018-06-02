package com.booboot.vndbandroid.ui.vndetails

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import com.booboot.vndbandroid.R
import com.booboot.vndbandroid.model.vndb.VN
import com.booboot.vndbandroid.ui.base.BaseActivity
import com.booboot.vndbandroid.ui.slideshow.SlideshowActivity
import com.booboot.vndbandroid.ui.slideshow.SlideshowAdapter
import com.booboot.vndbandroid.util.Logger
import kotlinx.android.synthetic.main.vn_details_activity.*

class VNDetailsActivity : BaseActivity(), SlideshowAdapter.Listener {
    private lateinit var vnDetailsViewModel: VNDetailsViewModel
    private lateinit var slideshowAdapter: SlideshowAdapter
    private lateinit var tabsAdapter: VNDetailsTabsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar_Translucent)
        setContentView(R.layout.vn_details_activity)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val vnId = intent.getIntExtra(EXTRA_VN_ID, 0)
        val vnImage = intent.getStringExtra(EXTRA_SHARED_ELEMENT_COVER)
        val vnImageNsfw = intent.getBooleanExtra(EXTRA_SHARED_ELEMENT_COVER_NSFW, false)

        tabsAdapter = VNDetailsTabsAdapter(supportFragmentManager)
        viewPager.adapter = tabsAdapter
        tabLayout.setupWithViewPager(viewPager)

        slideshowAdapter = SlideshowAdapter(this, this, scaleType = ImageView.ScaleType.CENTER_CROP)
        slideshow.adapter = slideshowAdapter
        if (vnImage != null) slideshowAdapter.images = mutableListOf(vnImage)

        vnDetailsViewModel = ViewModelProviders.of(this).get(VNDetailsViewModel::class.java)
        vnDetailsViewModel.loadingData.observe(this, Observer { showLoading(it) })
        vnDetailsViewModel.vnData.observe(this, Observer { showVn(it) })
        vnDetailsViewModel.errorData.observe(this, Observer { showError(it) })
        vnDetailsViewModel.loadVn(vnId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> supportFinishAfterTransition()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showVn(vn: VN?) {
        if (vn == null) return
        Logger.log(vn.toString())
        toolbar.title = vn.title

        val screens = if (vn.image != null) mutableListOf(vn.image!!) else mutableListOf()
        screens.addAll(vn.screens.map { it.image })
        slideshowAdapter.images = screens.toList()

        tabsAdapter.vn = vn

        val titles = listOf("Summary", "Characters", "Releases", "Staff", "Tags")
        if (tabLayout.tabCount <= 0) { // INIT
            titles.forEach { tabLayout.addTab(tabLayout.newTab().setText(it)) }
        } else { // UPDATE
            for (i in 0 until tabLayout.tabCount) tabLayout.getTabAt(i)?.text = titles[i]
        }
    }

    override fun onImageClicked(position: Int, images: List<String>) {
        val intent = Intent(this, SlideshowActivity::class.java)
        intent.putExtra(SlideshowActivity.INDEX_ARG, position)
        intent.putCharSequenceArrayListExtra(SlideshowActivity.IMAGES_ARG, ArrayList(images))
        startActivity(intent)
    }

    companion object {
        const val EXTRA_VN_ID = "EXTRA_VN_ID"
        const val EXTRA_SHARED_ELEMENT_COVER = "EXTRA_SHARED_ELEMENT_COVER"
        const val EXTRA_SHARED_ELEMENT_COVER_NSFW = "EXTRA_SHARED_ELEMENT_COVER_NSFW"
    }
}