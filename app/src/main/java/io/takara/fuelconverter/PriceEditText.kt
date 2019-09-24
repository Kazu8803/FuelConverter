package io.takara.fuelconverter

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText

class PriceEditText : EditText {

    constructor( context: Context,
                 attrs: AttributeSet,
                 defStyle: Int) :
            super(context, attrs, defStyle)

    constructor(context: Context,
                attrs: AttributeSet) :
            super(context, attrs)

    constructor(context: Context) : super(context)


    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        setSelection(this.length())
    }
}
