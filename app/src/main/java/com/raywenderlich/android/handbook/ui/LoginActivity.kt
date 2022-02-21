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

import android.os.Bundle
import android.widget.Toast
import com.raywenderlich.android.handbook.R
import com.raywenderlich.android.handbook.databinding.ActivityLoginBinding
import com.raywenderlich.android.handbook.db.LoginState
import com.raywenderlich.android.handbook.utils.startActivityAndClearStack

class LoginActivity : BaseActivity() {
  private lateinit var binding: ActivityLoginBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.loginButton.setOnClickListener { loginHand() }
  }

  private fun loginHand() {
    val loginStatus = handsDb.performLogin(
        binding.usernameInput.text.toString(),
        binding.passwordInput.text.toString()
    )

    when (loginStatus) {
      LoginState.SUCCESS -> {
        //DONE: 9 Change the code below to use startActivityAndClearStack function
        startActivityAndClearStack(MainActivity::class.java, null)
      }
      LoginState.WRONG_PASSWORD -> {
        Toast.makeText(this@LoginActivity, getString(R.string.wrong_password),
            Toast.LENGTH_SHORT).show()
      }
      else -> {
        Toast.makeText(this@LoginActivity, getString(R.string.username_not_found),
            Toast.LENGTH_SHORT).show()
      }
    }
  }
}
