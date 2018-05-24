package com.nearsoft.hellokapt.app.models

import com.nearsoft.hellokapt.annotations.HelloKapt

@HelloKapt(message = "Why I can NOT use my generated Kotlin class inside of a java class as a class member!?!?")
class AnnotatedClass() {
    // TODO: Builder works fine here as class property
    val builder = AnnotatedClassBuilder()
}