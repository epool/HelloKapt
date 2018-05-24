package com.nearsoft.hellokapt.annotations

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class HelloKapt(val message: String = "Hello Kapt!")
