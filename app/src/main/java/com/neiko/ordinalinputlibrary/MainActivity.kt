package com.neiko.ordinalinputlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neiko.ordinalinputlibrary.OriginalInput.OriginalInput
import com.neiko.ordinalinputlibrary.OriginalInput.OriginalInputType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val originalInput = OriginalInput(
            views = arrayOf(
                field_1_txt,
                field_2_txt,
                field_3_txt,
                field_4_txt
            ),
            type = OriginalInputType.WITHOUT_PREVIOUS
        )

        nextBtn.setOnClickListener {
            originalInput.toNext()
        }
    }



}
