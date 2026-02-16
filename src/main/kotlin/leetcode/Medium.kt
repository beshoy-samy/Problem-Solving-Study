package leetcode

/**
 * Implement the RandomizedSet class:
 *
 * RandomizedSet() Initializes the RandomizedSet object.
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 */
class RandomizedSet() {
    private val myMap = mutableMapOf<Int, Int>()
    private val myList = mutableListOf<Int>()

    // insert 10 20 30 40 50
    // {10 to 0, 20 to 1, 30 to 2, 40 to 3, 50 to 4}
    fun insert(`val`: Int): Boolean {
        if (myMap.containsKey(`val`)) return false
        myList.add(`val`)
        myMap[`val`] = myList.lastIndex
        return true
    }

    // remove 20
    // [10 20 30 40 50]
    // {10 to 0, 30 to 2, 40 to 3, 50 to 4}
    // index[20] = 1
    // [10 50 30 40 50]
    // [10 50 30 40]
    // {10 to 0, 30 to 2, 40 to 3, 50 to 4}
    fun remove(`val`: Int): Boolean {
        if (myList.isEmpty()) return false
        if (myMap.containsKey(`val`).not()) return false

        val index = myMap.remove(`val`)!!
        val lastIndex = myList.lastIndex
        if (index != lastIndex) {
            myList[index] = myList[lastIndex]
            myMap[myList[lastIndex]] = index
        }
        myList.removeLast()
        return true
    }

    fun getRandom(): Int {
        if (myList.isEmpty()) return -1
        val randomIndex = (0..myList.lastIndex).random()
        return myList[randomIndex]
    }

}
