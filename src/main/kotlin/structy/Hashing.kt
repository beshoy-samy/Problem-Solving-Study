package structy

fun exclusiveItems(a: List<Int>, b: List<Int>): List<Int> {
    val set = mutableSetOf<Int>()
    a.forEach { set.add(it) }
    b.forEach {
        if (set.add(it).not()) {
            set.remove(it)
        }
    }
    return set.toList()
}

fun allUnique(items: List<String>): Boolean {
    val set = mutableSetOf<String>()
    items.forEach { if (set.add(it).not()) return false }
    return true
}

// List.of("q", "b", "m", "s", "s", "s"),
// List.of("s", "m", "s")
// -> ["m", "s", "s"]
fun intersectionWithDupes(listA: List<String>, listB: List<String>): List<String> {
    val intersection = mutableListOf<String>()

    val listACharsCountMap = mutableMapOf<String, Int>()
    listA.forEach {
        val count = listACharsCountMap.getOrDefault(it, 0)
        listACharsCountMap[it] = count.inc()
    }

    listB.forEach {
        val count = listACharsCountMap.getOrDefault(it, 0)
        if (count != 0) {
            intersection.add(it)
            listACharsCountMap[it] = count.dec()
        }
    }

    return intersection
}
