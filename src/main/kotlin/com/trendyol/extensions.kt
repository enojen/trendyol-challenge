package com.trendyol


fun String.replaceTurkishChars(): String {
    var ret = this
    val turkishChars = charArrayOf(0x131.toChar(), 0x130.toChar(), 0xFC.toChar(), 0xDC.toChar(), 0xF6.toChar(), 0xD6.toChar(), 0x15F.toChar(), 0x15E.toChar(), 0xE7.toChar(), 0xC7.toChar(), 0x11F.toChar(), 0x11E.toChar())
    val englishChars = charArrayOf('i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G')
    for (i in turkishChars.indices) {
        ret = ret.replace(turkishChars[i], englishChars[i])
    }
    return ret
}