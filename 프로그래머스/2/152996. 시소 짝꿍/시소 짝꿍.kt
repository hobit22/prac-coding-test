class Solution {
    fun solution(weight: IntArray): Long {
        var answer: Long = 0
        
        val weights = LongArray(1001)
        for(i in weight) weights[i]++
        
        
        for(i in 100..1000){
            answer += Comb2(weights[i].toLong())
        }
        
        for(i in 100..999){
            if (weights[i] == 0L) continue
            val p = hashSetOf(i * 2,i * 3,i * 4)
            for(j in i+1..1000){
                if (i * 4 < j * 2) break
                if (weights[j] == 0L) continue
                val q = hashSetOf(j * 2,j * 3,j * 4)
                if (p.intersect(q).isNotEmpty()) answer += weights[i] * weights[j]
            }
        }
        
        
        return answer
    }
    
    fun Comb2(n:Long) = (n*(n-1)) / 2L
}