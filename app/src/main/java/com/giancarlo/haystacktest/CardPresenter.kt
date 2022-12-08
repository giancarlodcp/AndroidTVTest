package com.giancarlo.haystacktest

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide

class CardPresenter : Presenter() {

    companion object {
        private const val CARD_WIDTH = 400
        private const val CARD_HEIGHT = 200
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val cardView = ImageCardView(parent?.context)
        cardView.isFocusable = true
        cardView.isFocusableInTouchMode = true
        parent?.context?.let {
            //cardView.setBackgroundColor(ContextCompat.getColor(it, R.color.primary))
            cardView.setInfoAreaBackgroundColor(ContextCompat.getColor(it, R.color.primary))
        }

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val flickrPic = item as FlickrPic
        val cardView = viewHolder?.view as ImageCardView

        cardView.titleText = flickrPic.title
        cardView.contentText = flickrPic.author
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
        Glide.with(cardView.mainImageView).load(flickrPic.image).placeholder(R.mipmap.haystack)
            .into(cardView.mainImageView)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        with(viewHolder?.view as ImageCardView) {
            mainImage = null
        }
    }

}