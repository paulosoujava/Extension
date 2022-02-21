package com.raywenderlich.android.handbook

import android.content.Context
import android.widget.Toast



fun String.validate() = this.length > 5

fun String.emailValidate() = this.contains("@")

fun Toast.myToast(ctx: Context, msg: String, time: Int = Toast.LENGTH_SHORT) = Toast.makeText(ctx, msg, time).duration

fun main() {


    println("Paulo".validate())
    println("p@p.com".emailValidate())
}