package com.romeo.core.presentation.list

/**
 * All fields that are shown should be implemented by this annotation
 * */
@Target(AnnotationTarget.FIELD)
annotation class Content


/**
 * All the fields that must be compared during comparing two
 * different list items should be annotated by this annotation.
 * */
@Target(AnnotationTarget.FIELD)
annotation class ListItemId

/**
 * Method for comparing two objects by the fields that
 * are annotated with the annotation witch is given in parameters
 *
 * @param annotationClass - annotation, by which objects should be compared
 * */
fun <T> T.compareAnnotatedFields(
    other: T,
    annotationClass: Class<out Annotation>
): Boolean {
    val inClass = other!!::class.java

    if (this!!::class.java != inClass) return false

    val contentFields = inClass.declaredFields.filter { field ->
        field.isAnnotationPresent(annotationClass)
    }

    if (contentFields.isNullOrEmpty()) return equals(other)

    contentFields.forEach { field ->
        field.isAccessible = true
        val thisField = field.get(this)
        val otherField = field.get(other)

        if (thisField != otherField) return false
    }

    return true
}