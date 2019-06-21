package net.chigita.illuminat.util

import android.content.Context
import android.widget.Toast

/**
 * Created by chigichan24 on 2019-06-21.
 */
fun onError(context: Context, e: Exception) {
  Toast.makeText(
      context,
      e.message,
      Toast.LENGTH_LONG
  ).show()
}