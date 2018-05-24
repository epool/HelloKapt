package com.nearsoft.hellokapt.processor

import com.google.auto.service.AutoService
import com.nearsoft.hellokapt.annotations.HelloKapt
import com.squareup.kotlinpoet.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.StandardLocation

@AutoService(Processor::class)
class HelloKaptProcessor : AbstractProcessor() {

    private lateinit var filer: Filer

    override fun init(environment: ProcessingEnvironment) {
        super.init(environment)
        filer = environment.filer
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> = mutableSetOf(HelloKapt::class.java.name)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latestSupported()

    override fun process(elements: MutableSet<out TypeElement>, environment: RoundEnvironment): Boolean {
        val annotatedClasses = environment.getElementsAnnotatedWith(HelloKapt::class.java)
        annotatedClasses
                .filter { it is TypeElement }
                .map { it as TypeElement }.forEach {
                    val helloKapt = it.getAnnotation(HelloKapt::class.java)
                    val packageName = processingEnv.elementUtils.getPackageOf(it).toString()
                    val className = it.simpleName.toString()
                    val generatedClassName = "${className}Builder"
                    val file = FileSpec.builder(packageName, generatedClassName)
                            .addType(
                                    TypeSpec.classBuilder(generatedClassName)
                                            .primaryConstructor(
                                                    FunSpec.constructorBuilder()
                                                            .addParameter(ParameterSpec.builder("message", String::class).defaultValue("\"${helloKapt.message}\"").build())
                                                            .build()
                                            )
                                            .addProperty(PropertySpec.builder("message", String::class)
                                                    .initializer("message")
                                                    .build())
                                            .build()
                            )
                            .build()
                    file.writeTo(filer)
                }
        return true
    }

    fun FileSpec.writeTo(filer: Filer) {
        val fileObject = filer.createResource(StandardLocation.SOURCE_OUTPUT, packageName, "$name.kt")
        fileObject.openWriter().use { writeTo(it) }
    }

}
