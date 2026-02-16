package leetcode

fun lengthOfLongestSubstring(s: String): Int {
    var best = 0
    var left = 0
    var right = 0
    val substring = mutableSetOf<Char>()

    while (right < s.length) {
        if (substring.add(s[right])) {
            best = maxOf(best, substring.size)
            right++
        } else {
            substring.remove(s[left])
            left++
        }
    }

    return best
}


