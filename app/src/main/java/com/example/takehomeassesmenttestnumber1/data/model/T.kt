package com.example.takehomeassesmenttestnumber1.data.model

data class Product(
    val id: String,
    val name: String,
    val price: Price,
    val isFavourite: Boolean
) {
    // Value object
    data class Price(
        val nowPrice: Double,
        val wasPrice: Double
    ) {
        companion object {
            val EMPTY = Price(0.0, 0.0)
        }
    }
}
//عhttps://proandroiddev.com/the-real-repository-pattern-in-android-efba8662b754
fun test() {
    var product = Product("", price = Product.Price(100.0,100.0), name = "", isFavourite = false)

    val price = Product.Price(100.0, 100.0)
    Product.Price.EMPTY
}