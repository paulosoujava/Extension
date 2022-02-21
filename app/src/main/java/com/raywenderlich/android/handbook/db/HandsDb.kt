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

package com.raywenderlich.android.handbook.db

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.google.gson.Gson
import com.raywenderlich.android.handbook.models.Hand

class HandsDb(context: Context) {
  private val gson = Gson()
  private val preferences = context
      .getSharedPreferences("hands_db", MODE_PRIVATE)

  companion object {
    private const val LOGGED_IN_HAND_KEY = "logged_in_hand"
  }

  fun getLoggedInHand(): Hand? {
    val handString = preferences.getString(LOGGED_IN_HAND_KEY, null) ?: return null
    return gson.fromJson(handString, Hand::class.java)
  }

  private fun checkForExistingUsername(username: String): Boolean {
    val hand = preferences.getString("hand_${username}", null)
    return hand != null
  }

  fun performLogin(username: String, password: String): LoginState {
    val handString = preferences.getString("hand_${username}", null)
        ?: return LoginState.HAND_NOT_REGISTERED

    val hand = gson.fromJson(handString, Hand::class.java)
    return if (hand.password == password) {
      val editor = preferences.edit()
      editor.putString(LOGGED_IN_HAND_KEY, handString)
      editor.apply()
      LoginState.SUCCESS
    } else {
      LoginState.WRONG_PASSWORD
    }
  }

  fun registerHand(hand: Hand): RegistrationState {
    if (checkForExistingUsername(hand.userName)) {
      return RegistrationState.HAND_ALREADY_EXIST
    }

    val handString = gson.toJson(hand)
    val editor = preferences.edit()
    editor.putString("hand_${hand.userName}", handString)
    editor.putString(LOGGED_IN_HAND_KEY, handString)
    editor.apply()

    return RegistrationState.SUCCESS
  }

  fun logoutHand(): LogoutState {
    getLoggedInHand() ?: return LogoutState.NOT_LOGGED_IN

    val editor = preferences.edit()
    editor.remove(LOGGED_IN_HAND_KEY)
    editor.apply()

    return LogoutState.SUCCESS
  }
}