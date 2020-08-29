package muhas

import muhas.util.BaseConversion
import java.net.URL


val homeUrl = "https://www.trendyol.com/butik/liste/erkek"
val homeUrl2 = "https://www.trendyol.com/butik/liste/{sectionName}"
val search = "https://www.trendyol.com/tum--urunler?q=elbise"
val product = "https://www.trendyol.com/{BrandName-or-CategoryName}/{ProductName}-p-{ContentId}?boutiqueId={BoutiqueId}&merchantId={MerchantId}"


fun main2(){
    println(Long.MAX_VALUE)
    val a = BaseConversion.encode(123)

    println(a)

    val b = BaseConversion.decode(a)
    println(b)
}