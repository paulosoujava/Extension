/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.handbook.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.raywenderlich.android.handbook.R
import com.raywenderlich.android.handbook.models.Hand
import com.raywenderlich.android.handbook.ui.BaseActivity
import com.raywenderlich.android.handbook.ui.OnBoardingActivity
import org.jetbrains.annotations.NotNull
import java.util.regex.Pattern

//DONE: 1 Add an extension function with ImageView as receiver to load image using glide
fun ImageView.loadImage(imageUrl: String) {
  Glide.with(this)
      .load(imageUrl)
      .into(this)
}

//DONE: 3 Add extension function for BaseActivity to show greeting message
fun BaseActivity.greet() {
  Toast.makeText(this, getString(R.string.welcome_base_activity), Toast.LENGTH_SHORT).show()
}

//DONE: 4 Add extension function for OnBoardingActivity to show greeting message
fun OnBoardingActivity.greet() {
  Toast.makeText(this, getString(R.string.welcome_onboarding_activity), Toast.LENGTH_SHORT).show()
}

//DONE: 8 Add startActivityAndClearStack function below
fun Context.startActivityAndClearStack(clazz: Class<*>, extras: Bundle?) {
  val intent = Intent(this, clazz)
  intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
  if (extras != null) {
    intent.putExtras(extras)
  }
  startActivity(intent)
}

//DONE: 11 Add extension function to validate and suggest alternate usernames
fun EditText.validateUsername(): Boolean {
  val username = text.toString()

  val pattern = Pattern.compile("^[a-zA-Z]+[0-9]+$")
  val matcher = pattern.matcher(username)

  val isValid = matcher.matches()

  if (!isValid) {
    error = context.getString(R.string.username_validation_error, username)
  }

  return isValid
}

//DONE: 13 Add extension property to get total fingers
val Hand?.totalFingers: String
  get() {
//    return (fingersCount + thumbsCount).toString()
    if (this == null) return "-"
    return (fingersCount + thumbsCount).toString()
  }