package com.sconzo.hackathon_park_mobile

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import moxy.MvpAppCompatActivity
import ru.yandex.money.android.sdk.ColorScheme

 //Fragment Extend

fun Fragment.hideKeyboard() {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Fragment.getColorScheme(): ColorScheme {
    return ColorScheme(requireContext().getColor(R.color.colorPrimary))
}

fun Fragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


// Activity Extend

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager =
        getSystemService(MvpAppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val actNw =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}


// View Extend

fun View?.showToast(message: String) {
    Toast.makeText(this?.context, message, Toast.LENGTH_SHORT).show()
}

fun View?.showSnackbar(message: String) {
    this?.let {
        Snackbar.make(it, message, Snackbar.LENGTH_SHORT)
            .setAction("OK") {}
            .show()
    } ?: this.showToast(message)
}


// EditText Extend

fun EditText.setErrorListener(layout: TextInputLayout) {
    doAfterTextChanged {
        if (!layout.error.isNullOrEmpty()) {
            layout.error = ""
        }
    }
}

fun EditText.doFocusGone(listener: EditText.() -> Unit) {
    setOnFocusChangeListener { _, b ->
        if (!b) {
            listener()
        }
    }
}


// TextView Extend

fun TextView.animateTextColor(
    startColor: Int = -1,
    endColor: Int,
    duration: Long = 1000,
    reverse: Boolean = false
) {
    val start = if (startColor == -1) currentTextColor else startColor
    ObjectAnimator.ofInt(this, "textColor", start, endColor).apply {
        setEvaluator(ArgbEvaluator())
        this.duration = duration
//        repeatCount = 1
//        repeatMode = ValueAnimator.REVERSE
        if (reverse) reverse() else start()
    }
}

fun TextView.animateTextColor(
    startColor: String = "",
    endColor: String,
    duration: Long = 1000,
    reverse: Boolean = false
) {
    val start = if (startColor.isNotEmpty()) Color.parseColor(startColor) else currentTextColor
    val end = Color.parseColor(endColor)
    animateTextColor(start, end, duration, reverse)
}

fun TextView.reverseAnimTextColor(startColor: String) {
    val start = startColor.toColorInt()
    ObjectAnimator.ofInt(this, "textColor", start, currentTextColor).apply {
        setEvaluator(ArgbEvaluator())
        duration = 500
        start()
    }
}

fun TextView.reverseAnimTextColor(@ColorRes startColor: Int) {
    val start = ContextCompat.getColor(context, startColor)
    val startParse = String.format("#%06X", 0xFFFFFF and start)
    reverseAnimTextColor(startParse)
}

fun TextView.animDecrease() {
    reverseAnimTextColor(R.color.colorDecrease)
}

fun TextView.animIncrease() {
    reverseAnimTextColor(R.color.colorIncrease)
}

fun TextView.animText(diff: Int) {
    when (diff) {
        1 -> {
            animIncrease()
        }
        -1 -> {
            animDecrease()
        }
    }
}


// ImageView Extensions

fun ImageView.animateReveal(duration: Long = 200) {
    val revealAnim = ScaleAnimation(
        0f, 1f, 0f, 1f,
        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
    ).apply {
        this.duration = duration
        this.fillAfter = true
    }

    startAnimation(revealAnim)
}