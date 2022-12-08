package com.giancarlo.haystacktest

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.leanback.app.VerticalGridSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.VerticalGridPresenter


class MainFragment : VerticalGridSupportFragment() {

    private val gridAdapter = ArrayObjectAdapter(CardPresenter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Haystack Test"

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
