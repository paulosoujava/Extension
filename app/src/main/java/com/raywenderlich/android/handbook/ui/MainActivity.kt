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

package com.raywenderlich.android.handbook.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.raywenderlich.android.handbook.R
import com.raywenderlich.android.handbook.databinding.ActivityMainBinding
import com.raywenderlich.android.handbook.models.Hand
import com.raywenderlich.android.handbook.utils.totalFingers

class MainActivity : BaseActivity() {
  private lateinit var binding: ActivityMainBinding

  //DONE: 15 Change the Hand type to nullable
  private var currentHand: Hand? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //DONE: 16 Call getLoggedInHand without non-null assertion
    currentHand = handsDb.getLoggedInHand()

    showDescription(currentHand)

    binding.logoutButton.setOnClickListener {
      handsDb.logoutHand()
      val mainIntent = Intent(this, SplashActivity::class.java)
      mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      startActivity(mainIntent)
    }

    //DONE: 19 Call showGreeting on currentHand
    currentHand.showGreeting()
  }

  private fun showDescription(hand: Hand?) {
    binding.welcomeTv.text = getString(
      R.string.welcome_username,
      hand?.userName ?: "-"
    )

    //DONE 14: Update the above code to use total fingers extension property
//    binding.userDescriptionTv.text = getString(
//      R.string.user_description,
//      hand.bio, hand.fingersCount, hand.thumbsCount
//    )

    //DONE: 17 Change the code to use nullable Hand
    binding.userDescriptionTv.text = getString(R.string.user_description_total_fingers,
      hand?.bio ?: "-", hand?.totalFingers)
  }

  //DONE: 18 Add showGreeting method with scope of MainActivity
  private fun Hand?.showGreeting() {
    if (this == null) {
      Toast.makeText(this@MainActivity, getString(R.string.greeting_anonymous),
          Toast.LENGTH_SHORT).show()
    } else {
      Toast.makeText(this@MainActivity, getString(R.string.greeting_user, userName),
          Toast.LENGTH_SHORT).show()
    }
  }
}
