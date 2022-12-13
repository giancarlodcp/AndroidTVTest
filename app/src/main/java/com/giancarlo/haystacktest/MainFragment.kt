package com.giancarlo.haystacktest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.VerticalGridPresenter
import com.bumptech.glide.Glide


class MainFragment : VerticalGridSupportFragment() {

    private val gridAdapter = ArrayObjectAdapter(CardPresenter())
    lateinit var imageView: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = requireActivity().findViewById(R.id.image_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Haystack Test"

        //val imageView:ImageView = requireActivity().findViewById(R.id.image_view)

        gridPresenter = VerticalGridPresenter()
        gridPresenter.numberOfColumns = 4
        searchAffordanceColor = ContextCompat.getColor(requireContext(), R.color.primary)

        adapter = gridAdapter

        val list = FlickrService().getList(20, "gatos")
        gridAdapter.addAll(0, list)

        setOnSearchClickedListener {
            val intent = Intent(context, SearchActivity::class.java)
            startActivityForResult(intent, 200)
        }

        setOnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            if(imageView.isVisible){
                val flickrPic = item as FlickrPic
                Glide.with(imageView).load(flickrPic.image).placeholder(R.mipmap.haystack)
                    .into(imageView)
            }
        }

        setOnItemViewClickedListener { itemViewHolder, item, rowViewHolder, row ->
            imageView.visibility = View.VISIBLE
            val flickrPic = item as FlickrPic
            Glide.with(imageView).load(flickrPic.image).placeholder(R.mipmap.haystack)
                .into(imageView)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 200) {
            if (resultCode == 200) {
                val query: String = data?.getStringExtra("query").toString()
                gridAdapter.clear()
                val list = FlickrService().getList(20, query)
                gridAdapter.addAll(0, list)

            }
        }

    }
}
