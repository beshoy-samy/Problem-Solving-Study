fun main(args: Array<String>) {
    zip(listOf(1, 3).toListNode(), listOf(5, 2, 9, 10).toListNode()).print()
}

fun calculatePrice(orderValue: Float, state: String): Float {
    if (orderValue < 0) {
        println("negative values are not allowed!")
        return 0F
    }

    val discount = calculateDiscountRate(orderValue)
    val discountedPrice = orderValue.minus(orderValue.times(discount))

    val tax = calculateTaxRate(state)
    val priceAfterTaxes = discountedPrice.plus(discountedPrice.times(tax))

    return priceAfterTaxes
}


fun testCalculatePriceWith10000OrderInUTState(){

    val result = calculatePrice(orderValue = 10000F, state = "UT")

    assert(result == 9616.5F)
}

private fun calculateDiscountRate(orderValue: Float): Float {
    if (orderValue < 0) {
        println("negative values are not allowed!")
        return 0F
    }
    return when (orderValue) {
        in 0F..999F -> 0F
        in 1000F..4999F -> 0.03F
        in 5000F..6999F -> 0.05F
        in 7000F..9999F -> 0.07F
        in 10000F..49999F -> 0.1F
        else -> 0.15F
    }
}

private fun calculateTaxRate(state: String): Float =
    when (state) {
        "UT", "ut" -> 0.0685F
        "NV", "nv" -> 0.08F
        "TX", "tx" -> 0.625F
        "AL", "al" -> 0.04F
        else -> 0.825F
    }