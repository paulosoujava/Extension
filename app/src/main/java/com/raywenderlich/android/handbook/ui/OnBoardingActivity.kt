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
import com.raywenderlich.android.handbook.R
import com.raywenderlich.android.handbook.databinding.ActivityOnboardingBinding
import com.raywenderlich.android.handbook.utils.loadImage

class OnBoardingActivity : BaseActivity() {
  private lateinit var binding: ActivityOnboardingBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityOnboardingBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //DONE: 2 Replace the below code with extension function from Extensions.kt file
    binding.imageIcon.loadImage(getString(R.string.logo_url))

    binding.loginButton.setOnClickListener {
      startActivity(Intent(this, LoginActivity::class.java))
    }

    binding.registerButton.setOnClickListener {
      startActivity(Intent(this, RegisterActivity::class.java))
    }

    //DONE: 6 Call showGreetingMessage
    showGreetingMessage(this)
  }

  //DONE: 5 Add method below to use greeting extension function
  private fun showGreetingMessage(activity: BaseActivity) {
    activity.greet()
  }
}
