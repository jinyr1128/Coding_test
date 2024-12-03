import Foundation

func solution(_ k:Int, _ dungeons:[[Int]]) -> Int {
    var maxCount = 0
    let n = dungeons.count
    var visited = [Bool](repeating: false, count: n)
    
    func dfs (_ fatigue: Int, _ count: Int){
        maxCount = max(maxCount, count)
        for i in 0..<n {
            if !visited[i] && fatigue >= dungeons[i][0]{
                visited[i] = true
                dfs(fatigue - dungeons[i][1], count + 1)
                visited[i] = false
            }
        }
    }
    dfs(k, 0)
    return maxCount
}