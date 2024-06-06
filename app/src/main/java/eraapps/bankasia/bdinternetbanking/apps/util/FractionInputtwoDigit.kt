package com.emon.raihan.dynamicutility.util

import android.widget.EditText

class FractionInputtwoDigit {
    companion object {

        fun String.removeAfter2Decimal(et: EditText) {
            return if (this.isEmpty() || this.isBlank() || this.lowercase() == "null") {
                //
            } else {
                if (this.contains(".")) {
                    var lastPartOfText = this.split(".")[this.split(".").size - 1]

                    if (lastPartOfText.count() > 2) {
                        try {
                            lastPartOfText = this.substring(0, this.indexOf(".") + 3)
                            et.setText(lastPartOfText)
                            et.setSelection(lastPartOfText.length)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    } else {

                    }
                } else {

                }
            }
        }


    }
}