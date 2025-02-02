inline fun String.ifNotEmpty(transform: String.() -> String): String =
    if (this.isNotEmpty()) this.transform() else String()