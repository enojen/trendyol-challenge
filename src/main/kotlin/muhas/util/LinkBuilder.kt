package muhas.util

import java.lang.StringBuilder

class LinkBuilder(val host: String) {
    private val paths = mutableListOf<String>()
    private val params = mutableMapOf<String, String>()

    fun addPath(path: String): LinkBuilder {
        paths.add(path)
        return this
    }

    fun addParam(param: Pair<String, String>): LinkBuilder {
        params.put(param.first, param.second)
        return this
    }

    fun build(): String {
        val sb = StringBuilder()
        sb.append(host)
        sb.append(paths.joinToString(separator = "/"))

        var first = true
        if (params.isNotEmpty()) {
            if (first) {
                sb.append("?")
            }
            params.forEach { k, v ->
                if (first) {
                    sb.append(k).append("=").append(v)
                    first = false
                } else {
                    sb.append("&").append(k).append("=").append(v)
                }
            }
        }

        return sb.toString()
    }
}