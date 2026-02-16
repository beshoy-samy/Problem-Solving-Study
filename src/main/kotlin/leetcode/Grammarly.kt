package leetcode

import GNode
import Node
import isLeaf
import java.lang.classfile.Attributes.code
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow


/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * @see <a href = "https://leetcode.com/problems/word-break/description/" >139.Medium</a>
 */
fun wordBreak(
    s: String,
    wordDict: List<String>,
    i: Int = 0,
    memory: MutableMap<Int, Boolean> = mutableMapOf()
): Boolean {
    if (i >= s.length) return true
    if (memory.containsKey(i)) return memory[i]!!

    fun isValid(word: String, i: Int): Boolean {
        val endIndex = word.length.plus(i)
        return endIndex <= s.length && word == s.substring(i, endIndex)
    }

    wordDict.forEach { word ->
        if (isValid(word, i) && wordBreak(s, wordDict, i = i.plus(word.length), memory = memory)) {
            memory[i] = true
            return true
        }
    }

    memory[i] = false
    return false
}

/**
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 * @see <a href = "https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/description/">1047.Easy</a>
 */
fun removeDuplicates(s: String): String {
    val myStack = ArrayDeque<Char>()

    s.forEach {
        if (it != myStack.lastOrNull()) {
            myStack.add(it)
        } else {
            myStack.removeLast()
        }
    }

    return myStack.joinToString(separator = "")
}

/**
 * Given an array of intervals where intervals{i} = {starti, endi}, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *  * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 *  * Output: [[1,6],[8,10],[15,18]]
 *  * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * @see <a href = "https://leetcode.com/problems/merge-intervals/description/">"56.Medium" Merge Intervals</a>
 */
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    intervals.sortBy { it.first() } // sort by start time
    val result = mutableListOf(intervals.first())
    var i = 1
    var j = 0

    fun mergeable(range1: IntArray, range2: IntArray) =
        range1[0] == range2[0] || range1[1] >= range2[0]

    while (i < intervals.size) {
        val original = intervals[i]
        val new = result[j]
        if (mergeable(new, original)) {
            result[j] = intArrayOf(min(original[0], new[0]), max(original[1], new[1]))
            i++
        } else {
            result.add(original)
            i++
            j++
        }
    }
    return result.toTypedArray()
}

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is
 * ```
 * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].
 * ```
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 * @see <a href = "https://leetcode.com/problems/search-in-rotated-sorted-array/">"33.Medium" Search in Rotated Sorted Array</a>
 */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.lastIndex

    // binary search to find the smallest element of the array
    while (left < right) {
        val mid = right.minus(left).div(2).plus(left) // find the mid-point

        if (nums[mid] > nums[nums.lastIndex]) { // check if we are in rotated subarray
            left = mid.inc()
        } else {
            right = mid
        }
    }

    // detect the subarray to binary search for the target
    if (target >= nums[left] && target <= nums[nums.lastIndex]) {
        right = nums.lastIndex
    } else {
        right = left
        left = 0
    }

    while (left <= right) {
        val mid = right.minus(left).div(2).plus(left) // find the mid-point

        if (target == nums[mid]) return mid
        if (target > nums[mid]) left = mid.inc()
        else right = mid.dec()
    }

    return -1
}

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 * @see <a href = "https://leetcode.com/problems/implement-trie-prefix-tree/description/">"208.Medium" Implement Trie</a>
 */
class Trie() {

    private val root = TrieNode()

    fun insert(word: String) {
        var node = root
        word.forEach { char ->
            if (node.children.containsKey(char).not())
                node.children[char] = TrieNode()
            node = node.children[char]!!
        }
        node.aWordEnd = true
    }

    fun search(word: String): Boolean {
        var node = root
        word.forEach { char ->
            if (node.children.containsKey(char)) {
                node = node.children[char]!!
            } else {
                return false
            }
        }
        return node.aWordEnd
    }

    fun startsWith(prefix: String): Boolean {
        var node = root
        prefix.forEach { char ->
            if (node.children.containsKey(char)) {
                node = node.children[char]!!
            } else {
                return false
            }
        }
        return true
    }

    internal data class TrieNode(
        val children: MutableMap<Char, TrieNode> = mutableMapOf(),
        var aWordEnd: Boolean = false
    )

}

/**
 * @see <a href = "https://leetcode.com/problems/vowel-spellchecker/description/">"966.Medium" Vowel Spellchecker</a>
 */
fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
    val wordsSet = wordlist.toSet()
    // build case-insensitive word map
    val wordsMap = mutableMapOf<String, String>()
    wordlist.forEach {
        val key = it.lowercase()
        if (wordsMap.containsKey(key).not()) {
            wordsMap[key] = it
        }
    }
    // build vowel word map
    val wordsVowel = mutableMapOf<String, String>()
    wordlist.forEach {
        val key = it.lowercase().replace("[aeiouAEIOU]".toRegex(), "*")
        if (wordsVowel.containsKey(key).not()) {
            wordsVowel[key] = it
        }
    }

    println(wordsVowel)

    val spellChecked = Array(queries.size) { "" }
    queries.forEachIndexed { index, string ->
        if (wordsSet.contains(string)) {
            spellChecked[index] = string
        } else if (wordsMap.containsKey(string.lowercase())) {
            spellChecked[index] = wordsMap[string.lowercase()]!!
        } else if (wordsVowel.containsKey(string.replace("[aeiouAEIOU]".toRegex(), "*"))) {
            spellChecked[index] = wordsVowel[string.lowercase().replace("[aeiouAEIOU]".toRegex(), "*")]!!
        }
    }
    return spellChecked
}

/**
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 * @see <a href = "leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/description/">"1209.Medium" Remove Adjacent Duplicates II</a>
 */
fun removeDuplicates(s: String, k: Int): String {
    val stack = ArrayDeque<Pair<Char, Int>>()

    s.forEach { char ->
        val (peek, count) = stack.lastOrNull() ?: ('*' to 0)

        if (peek != char) { // new char
            stack.addLast(char to 1)
        } else if (count.inc() != k) { // same char but k didn't match
            stack.addLast(char to count.inc())
        } else { // same char but k matched
            repeat(count) { // count is k-1
                stack.removeLast()
            }
        }
    }

    return stack.joinToString(separator = "") { it.first.toString() }
}

/**
 * The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 * For example, "ACGAATTCCG" is a DNA sequence.
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 * * Example 1:
 * ```
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * ```
 * @see <a href = "https://leetcode.com/problems/repeated-dna-sequences/description/">"187.Medium" Repeated Dna Sequences</a>
 *
 */
fun findRepeatedDnaSequences(s: String): List<String> {
    val sequencesMap = mutableMapOf<String, Int>()
    findDnaSequences(s, sequences = sequencesMap)
    return sequencesMap.filter { it.value > 1 }.map { it.key }
}

fun findDnaSequences(s: String, i: Int = 0, sequences: MutableMap<String, Int> = mutableMapOf()) {
    if (i.plus(10) > s.length) return

    val seq = s.substring(i, i.plus(10))
    sequences[seq] = sequences.getOrDefault(seq, 0).inc()
    findDnaSequences(s, i.inc(), sequences)
}

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * * Example:
 * ```
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * ```
 * @see <a href = "https://leetcode.com/problems/generate-parentheses/description/">"22.Medium" Generate Parentheses</a>
 */
fun generateParenthesis(n: Int, l: Int = n, r: Int = n, combination: String = ""): List<String> {
    if (combination.length == 2 * n) return listOf(combination)

    val all = mutableListOf<String>()

    if (l > 0) all += generateParenthesis(n, l.dec(), r, "$combination(")

    if (r > 0 && l < r) all += generateParenthesis(n, l, r.dec(), "$combination)")

    return all
}

/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 *
 * * Example 1:
 * ```
 * Input: x = 4
 * Output: 2
 * Explanation: The square root of 4 is 2, so we return 2.
 * ```
 * @see <a href = "https://leetcode.com/problems/sqrtx/">"69.Easy" Sqrt(X)</a>
 */
fun mySqrt(x: Int): Int {
    if (x < 2) return x

    var l = 2
    var r = x

    while (l <= r) {
        val mid = r.minus(l).div(2).plus(l)
        val num = mid.toLong() * mid

        if (num > x) r = mid.dec()
        else if (num < x) l = mid.inc()
        else return mid

    }

    return r
}

/**
 * Design a logger system that receives a stream of messages along with their timestamps. Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * @see <a href = "https://leetcode.com/problems/logger-rate-limiter/description/">"359.Easy" Logger Rate Limiter</a>
 */
class Logger() {
    private val logs = mutableMapOf<String, Int>()
    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
        val oldStamp = logs.getOrDefault(message, 0)
        val isAllowed = timestamp >= oldStamp
        if (isAllowed) logs[message] = timestamp + 10
        return isAllowed
    }
}

/**
 * A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself. A divisor of an integer x is an integer that can divide x evenly.
 *
 * Given an integer n, return true if n is a perfect number, otherwise return false.
 *
 * * Example 1:
 * ```
 * Input: num = 28
 * Output: true
 * Explanation: 28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, and 14 are all divisors of 28.
 * ```
 * @see <a href = "https://leetcode.com/problems/perfect-number/description/">"507.Easy" Perfect Number</a>
 */
fun checkPerfectNumber(num: Int): Boolean {
    var sum = 0
    for (i in 1..num.div(2)) {
        if (num.mod(i) == 0) sum += i
    }
    return sum == num
}

/**
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 *
 * @see <a href = "https://leetcode.com/problems/sum-of-left-leaves/description/">"404.Easy" Sum of Left Leaves</a>
 */
fun sumOfLeftLeaves(root: Node<Int>?, isLeft: Boolean = false): Int {
    if (root == null) return 0
    if (isLeft && root.isLeaf()) return root.value

    return sumOfLeftLeaves(root.left, true) + sumOfLeftLeaves(root.right)
}

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * @see <a href = "https://leetcode.com/problems/word-search/description/">"79.Medium" Word Search</a>
 */
fun exist(board: Array<CharArray>, word: String): Boolean {
    var y = 0
    var x = 0

    while (y < board.size) {
        if (exist(board, word, x, y)) return true
        if (x < board[y].lastIndex) x++
        else {
            y++
            x = 0
        }
    }

    return false
}

fun exist(
    board: Array<CharArray>,
    word: String,
    x: Int,
    y: Int,
    i: Int = 0
): Boolean {
    if (i > word.lastIndex) return true
    if (y < 0 || y > board.lastIndex) return false
    if (x < 0 || x > board.first().lastIndex) return false

    val target = word[i]
    val current = board[y][x]

    if (target != current) {
        return false
    }

    board[y][x] = '*'

    val result =
        exist(board, word, x.inc(), y, i.inc()) ||
                exist(board, word, x, y.inc(), i.inc()) ||
                exist(board, word, x.dec(), y, i.inc()) ||
                exist(board, word, x, y.dec(), i.inc())

    board[y][x] = current

    return result
}

/**
 * You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
 *
 * Evaluate the expression. Return an integer that represents the value of the expression.
 *
 * Note that:
 *
 * * The valid operators are '+', '-', '*', and '/'.
 * * Each operand may be an integer or another expression.
 * * The division between two integers always truncates toward zero.
 * * There will not be any division by zero.
 * * The input represents a valid arithmetic expression in a reverse polish notation.
 * * The answer and all the intermediate calculations can be represented in a 32-bit integer.
 *
 * Example 1:
 * ```
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * ```
 * Example 2:
 * ```
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * ```
 * @see <a href = "https://leetcode.com/problems/evaluate-reverse-polish-notation/description/">"150.Medium" Evaluate Reverse Polish Notation</a>
 */
fun evalRPN(tokens: Array<String>): Int {
    val myStack = ArrayDeque<String>()

    fun String.isOperator() =
        this == "+" || this == "-" || this == "*" || this == "/"

    tokens.forEach {
        if (it.isOperator().not()) myStack.addLast(it)
        else {
            val num1 = myStack.removeLast().toInt()
            val num2 = myStack.removeLast().toInt()
            val result =
                when (it) {
                    "+" -> num2 + num1
                    "-" -> num2 - num1
                    "*" -> num2 * num1
                    "/" -> num2 / num1
                    else -> return 0
                }
            myStack.addLast(result.toString())
        }
    }
    return myStack.last().toInt()
}

/**
 * Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.
 *
 * @see <a href = "https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/description/">"1461.Medium" Check If a String Contains All Binary Codes of Size K</a>
 */
fun hasAllCodes(s: String, k: Int): Boolean {
    val binaryCodes = mutableSetOf<String>()

    for (i in 0..s.lastIndex.minus(k.dec())) {
        val code = s.substring(i, i.plus(k))
        binaryCodes.add(code)
    }

    return binaryCodes.size == 2.0.pow(k.toDouble()).toInt()
}

/**
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 * @see <a href = "https://leetcode.com/problems/unique-paths/description/">"62.Medium" Unique Paths</a>
 */
fun uniquePaths(
    m: Int,
    n: Int,
    mPointer: Int = 0,
    nPointer: Int = 0,
    memory: MutableMap<Pair<Int, Int>, Int> = mutableMapOf(),
    target: Pair<Int, Int> = m.dec() to n.dec()
): Int {
    if (target == mPointer to nPointer) return 1
    if (mPointer !in 0..<m) return 0
    if (nPointer !in 0..<n) return 0
    if (memory.containsKey(mPointer to nPointer)) return memory[mPointer to nPointer]!!

    val ways = uniquePaths(m, n, mPointer, nPointer.inc(), memory) + uniquePaths(m, n, mPointer.inc(), nPointer, memory)

    memory[mPointer to nPointer] = ways

    return ways
}

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 * ```
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 * ```
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 *
 * @see <a href = "https://leetcode.com/problems/clone-graph/">"133.Medium" Clone Graph</a>
 */
fun cloneGraph(
    node: GNode<Int>?,
    cloneMap: MutableMap<GNode<Int>, GNode<Int>> = mutableMapOf()
): GNode<Int>? {
    if (node == null) return null
    if (cloneMap.containsKey(node)) return cloneMap[node]!!

    val neighbors = arrayListOf<GNode<Int>?>()
    val copy = GNode(node.value)
    cloneMap[node] = copy
    node.neighbors.forEach {
        cloneGraph(it, cloneMap)?.let { clone -> neighbors.add(clone) }
    }
    copy.neighbors = neighbors
    return copy
}

/**
 * @see <a href = "https://leetcode.com/problems/decode-the-slanted-ciphertext/description/">"2057.Medium" Decode the Slanted Ciphertext</a>
 */
fun decodeCiphertext(encodedText: String, rows: Int): String {
    val columns = encodedText.length.div(rows)
    var decoded = StringBuilder()

    for (i in 0..<columns) {
        for (j in i..<encodedText.length step columns.inc()) {
            decoded.append(encodedText[j])
        }
    }

    return decoded.toString().trimEnd()
}
