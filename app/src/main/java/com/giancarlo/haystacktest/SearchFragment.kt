package com.giancarlo.haystacktest

import android.content.Intent
import android.os.Bundle
import androidx.leanback.app.SearchSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.ObjectAdapter


class SearchFragment : SearchSupportFragment(), SearchSupportFragment.SearchResultProvider {


    private var mRowsAdapter: ObjectAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSearchResultProvider(this)
        mRowsAdapter = ArrayObjectAdapter(ListRowPresenter())
    }

    override fun getResultsAdapter(): ObjectAdapter {
        return mRowsAdapter as ObjectAdapter
    }


    override fun onQueryTextChange(newQuery: String?): Boolean {

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        val search = query + ""
        val data = Intent()
        data.putExtra("query", search)
        activity?.setResult(200, data)
        activity?.finish()
        return true
    }
}