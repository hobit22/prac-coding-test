import java.util.*

lateinit var answer: ArrayList<Int>
lateinit var queue: Queue<Pair<Int, Int>>
lateinit var visited: HashMap<Int, Boolean>

class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {
        answer = arrayListOf()
        queue = LinkedList()
        visited = hashMapOf()

        bfs(x, y, n, 0)

        return if (answer.isEmpty()) -1 else answer.first()
    }

    fun bfs(x: Int, y: Int, n: Int, depth: Int) {
        queue.add(Pair(x, depth))

        while (queue.isNotEmpty()) {
            val node = queue.poll()

            if (node.first > y) continue
            if (node.first == y) {
                answer.add(node.second)
                break
            }

            if (visited[node.first + n] != true) {
                queue.add(Pair(node.first + n, node.second + 1))
                visited[node.first + n] = true
            }
            if (visited[node.first * 2] != true) {
                queue.add(Pair(node.first * 2, node.second + 1))
                visited[node.first * 2] = true
            }
            if (visited[node.first * 3] != true) {
                queue.add(Pair(node.first * 3, node.second + 1))
                visited[node.first * 3] = true
            }
        }
    }
}