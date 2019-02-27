package com.neiko.ordinalinputlibrary.OriginalInput

import android.view.View
import android.view.animation.LinearInterpolator

/*
 * Project: OriginalInputLibrary
 * Package: com.neiko.ordinalinputlibrary.OriginalInput
 * Authod: Neikovich Sergey
 * Date: 29.01.2019
 */

class OriginalInput(val views: Array<View>, val type: OriginalInputType) {

    val TAG_SHOW = "SHOW"
    val TAG_HIDDEN = "HIDDEN"

    init {
        views.forEach {
            it.visibility = View.GONE
        }
        views[0].visibility = View.VISIBLE
        views[0].tag = TAG_SHOW
    }

    /**
     * Show next field
     */
    fun toNext() {

        for (i in 0 until views.size) {
            val cur_view = views[i]

            when (type) {
                OriginalInputType.WITHOUT_PREVIOUS -> {
                    if (i < views.size - 1 && cur_view.visibility == View.VISIBLE && cur_view.tag == TAG_SHOW) {

                        val nextFieldAnim = views[i + 1].animate().apply {
                            duration = 250
                            alpha(1f)
                        }

                        cur_view.animate().apply {
                            duration = 250
                            alpha(0f)
                            withEndAction {
                                cur_view.tag = TAG_HIDDEN
                                cur_view.alpha = 1f
                                cur_view.visibility = View.GONE

                                views[i + 1].visibility = View.VISIBLE
                                views[i + 1].tag = TAG_SHOW
                                views[i + 1].alpha = 0f
                                nextFieldAnim.start()
                            }
                            start()
                        }
                        return
                    }
                }

                OriginalInputType.WITH_PREVIOUS -> {
                    if (i < views.size - 1 && cur_view.tag == TAG_SHOW) {
                        cur_view.tag = TAG_HIDDEN
                        views[i + 1].visibility = View.VISIBLE
                        views[i + 1].tag = TAG_SHOW
                        views[i + 1].alpha = 0f
                        views[i + 1].animate().apply {
                            duration = 250
                            interpolator = LinearInterpolator()
                            alpha(1f)
                        }
                        return
                    }
                }
            }
        }
    }
}