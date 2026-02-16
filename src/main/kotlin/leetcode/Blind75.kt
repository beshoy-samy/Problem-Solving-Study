package leetcode

fun canAttendMeetings(intervals: Array<IntArray>): Boolean {
    intervals.sortBy { it[0] }

    for (i in 0..intervals.lastIndex.dec()) {
        val nextStart = intervals[i+1][0]
        if (nextStart == intervals[i][0] || nextStart < intervals[i][1]) return false
    }

    return true
}
