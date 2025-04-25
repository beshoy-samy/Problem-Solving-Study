package structy

import QueueArray
import neighbours

fun <T> List<List<T>>.toGraph(): Map<T, List<T>> {
    val graph = mutableMapOf<T, MutableList<T>>()
    forEach {
        graph.getOrPut(it[0]) { mutableListOf() }.add(it[1])
        graph.getOrPut(it[1]) { mutableListOf() }.add(it[0])
    }
    return graph
}

fun <T> graphDFS(graph: Map<T, List<T>>, src: T, visited: MutableSet<T> = mutableSetOf()) {
    if (visited.add(src).not()) return
    print(src)
    graph[src]?.forEach {
        graphDFS(graph, it, visited)
    }
}

/**
 * e -> edges, n -> nodes
 * in a fully connected graph
 * e = (n * (n - 1)) / 2
 * so in worst case O(e) or O(n^2)*/

fun hasPath(graph: Map<String, List<String>>, src: String, dst: String): Boolean {
    if (src == dst) return true
    graph[src]?.forEach {
        if (hasPath(graph, it, dst)) return true
    }
    return false
}

fun undirectedPath(edges: List<List<String>>, nodeA: String, nodeB: String): Boolean {
    return undirectedPath(edges.toGraph(), nodeA, nodeB)
}

fun undirectedPath(
    graph: Map<String, List<String>>,
    src: String,
    dst: String,
    visited: MutableSet<String> = mutableSetOf()
): Boolean {
    if (visited.add(src).not()) return false
    if (src == dst) return true
    graph[src]?.forEach {
        if (undirectedPath(graph, it, dst, visited)) return true
    }
    return false
}

fun connectedComponentsCount(graph: Map<Int, List<Int>>): Int {
    var count = 0
    val visited = mutableSetOf<Int>()
    graph.keys.forEach {
        if (traverse(it, graph, visited)) count++
    }
    return count
}

fun traverse(node: Int, graph: Map<Int, List<Int>>, visited: MutableSet<Int>): Boolean {
    if (visited.add(node).not()) return false
    graph[node]?.forEach {
        traverse(it, graph, visited)
    }
    return true
}

fun largestComponent(graph: Map<Int, List<Int>>): Int {
    var max = 0
    val visited = mutableSetOf<Int>()
    graph.keys.forEach {
        val count = traverseCounting(it, graph, visited)
        if (count > max) max = count
    }
    return max
}

fun traverseCounting(node: Int, graph: Map<Int, List<Int>>, visited: MutableSet<Int>): Int {
    if (visited.add(node).not()) return 0
    var count = 1
    graph[node]?.forEach {
        count += traverseCounting(it, graph, visited)
    }
    return count
}

fun shortestPath(edges: List<List<String>>, nodeA: String, nodeB: String): Int {
    val graph = edges.toGraph()
    val visited = mutableSetOf<String>()
    val queue = QueueArray(nodeA to 0)
    while (queue.isNotEmpty()) {
        val (node, distance) = queue.dequeue()!!
        if (visited.add(node).not()) continue
        if (node == nodeB) return distance
        graph[node]?.forEach {
            queue.enqueue(it to distance.inc())
        }
    }
    return -1
}

fun islandCount(grid: List<List<String>>): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    var x = 0
    var y = 0
    var islands = 0
    while (y < grid.size) {
        if (walk(x, y, grid, visited)) islands++
        if (x < grid[y].lastIndex) x++
        else {
            x = 0
            y++
        }
    }

    return islands
}

fun walk(x: Int, y: Int, grid: List<List<String>>, visited: MutableSet<Pair<Int, Int>>): Boolean {
    if (visited.add(x to y).not()) return false
    val node = grid.getOrNull(y)?.getOrNull(x) ?: "W"
    if (node == "W") return false
    (x to y).neighbours().forEach {
        walk(it.first, it.second, grid, visited)
    }
    return true
}

fun minimumIsland(grid: List<List<String>>): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    var x = 0
    var y = 0
    var minIsland = Int.MAX_VALUE
    while (y < grid.size) {
        val size = explore(x, y, grid, visited)
        if (size != 0 && size < minIsland) minIsland = size
        if (x < grid[y].lastIndex) x++
        else {
            x = 0
            y++
        }
    }

    return minIsland
}

fun explore(x: Int, y: Int, grid: List<List<String>>, visited: MutableSet<Pair<Int, Int>>): Int {

    if (visited.add(x to y).not()) return 0
    val node = grid.getOrNull(y)?.getOrNull(x) ?: "W"
    if (node == "W") return 0
    var steps = 1
    (x to y).neighbours().forEach {
        steps += explore(it.first, it.second, grid, visited)
    }
    return steps
}

fun closestCarrot(grid: List<List<String>>, startRow: Int, startCol: Int): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    val queue = QueueArray(startRow to startCol to 0)
    while (queue.isNotEmpty()) {
        val (rc, distance) = queue.dequeue()!!
        val node = grid.getOrNull(rc.first)?.getOrNull(rc.second) ?: continue
        if (visited.add(rc).not()) continue
        if (node == "C") return distance
        else if (node == "X") continue
        rc.neighbours().forEach { queue.enqueue(it to distance.inc()) }
    }
    return -1
}

fun longestPath(graph: Map<String, List<String>>): Int {
    val distances = mutableMapOf<String, Int>()
    var lPath = Int.MIN_VALUE
    graph.keys.forEach {
        val distance = pathLength(it, graph, distances)
        if (distance > lPath) lPath = distance
    }
    return lPath
}

fun pathLength(src: String, graph: Map<String, List<String>>, distances: MutableMap<String, Int>): Int {
    if (distances.containsKey(src)) return distances[src]!!
    val neighbours = graph[src].orEmpty()
    if (neighbours.isEmpty()) {
        distances[src] = 0
        return 0
    }

    var max = 0
    neighbours.forEach {
        val edges = pathLength(it, graph, distances).inc()
        max = maxOf(max, edges)
    }
    distances[src] = max
    return max
}

/**
 * courses ids are from 0 to n-1.
 *
 * listOf(A, B) -> course A must be taken before B
 * O(prereqs) S(N)
 * @return the minimum number of semesters required to complete all n courses.
 **/
fun semestersRequired(numCourses: Int, prereqs: List<List<Int>>): Int {
    val graph = prereqs.prereqsCoursesGraph()
    val distances = mutableMapOf<Int, Int>()
    var semesters = Int.MIN_VALUE
    repeat(numCourses) { course ->
        semesters = maxOf(study(course, graph, distances), semesters)
    }
    return semesters
}

fun study(course: Int, preregs: Map<Int, List<Int>>, distances: MutableMap<Int, Int>): Int {
    if (distances.containsKey(course)) return distances[course]!!
    if (preregs[course].orEmpty().isEmpty()) {
        distances[course] = 1
        return 1
    }
    var path = 0
    preregs[course]?.forEach {
        val semesters = study(it, preregs, distances).inc()
        distances[course] = semesters
        path = maxOf(semesters, path)
    }
    distances[course] = path
    return path
}

fun <T> List<List<T>>.prereqsCoursesGraph(): Map<T, List<T>> {
    val graph = mutableMapOf<T, MutableList<T>>()
    forEach {
        graph.getOrPut(it[0]) { mutableListOf() }.add(it[1])
    }
    return graph
}

/**
 * Write a method, bestBridge, that takes in a grid as an argument.
 * The grid contains water (W) and land (L). There are exactly two islands in the grid.
 * An island is a vertically or horizontally connected region of land. Return the minimum length bridge needed
 * to connect the two islands. A bridge does not need to form a straight line.
 *
 * approach is to find one island from the two using dfs then from there do bfs to find the shortest path
 * O(rc) S(rc)
 * */
fun bestBridge(grid: List<List<String>>): Int {
    val visited = mutableSetOf<Pair<Int, Int>>()
    val base = QueueArray<Pair<Pair<Int, Int>, Int>>()

    var r = 0
    var c = 0
    while (r < grid.size) {
        if (walk(r, c, grid, visited, base)) break
        if (c < grid[r].lastIndex) c++
        else {
            c = 0
            r++
        }
    }

    return swim(base, grid, visited)
}

fun walk(
    r: Int,
    c: Int,
    grid: List<List<String>>,
    visited: MutableSet<Pair<Int, Int>>,
    base: QueueArray<Pair<Pair<Int, Int>, Int>>
): Boolean {
    val node = grid.getOrNull(r)?.getOrNull(c) ?: "W"
    if (node == "W") return false
    if (visited.add(r to c).not()) return false
    base.enqueue(r to c to 0)
    (r to c).neighbours().forEach {
        walk(it.first, it.second, grid, visited, base)
    }
    return true
}

fun swim(
    base: QueueArray<Pair<Pair<Int, Int>, Int>>,
    grid: List<List<String>>,
    visited: MutableSet<Pair<Int, Int>>
): Int {
    while (base.isNotEmpty()) {
        val (rc, d) = base.dequeue()!!
        rc.neighbours().forEach {
            if (visited.contains(it).not()) {
                base.enqueue(it to d.inc())
            }
        }
        if(visited.contains(rc)) continue
        if ((grid.getOrNull(rc.first)?.getOrNull(rc.second) ?: "W") == "L") return d.dec()
        visited.add(rc)
    }
    return 0
}
