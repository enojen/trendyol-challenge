package muhas.model

data class DeepLink(val url: String) {
    val params = url.replace("ty://?", "").split("&").map {
        Pair(it.split("=")[0], it.split("=")[1])
    }
}