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
fun test() {
    val price = Product.Price(100.0, 100.0)
    Product.Price.EMPTY
}