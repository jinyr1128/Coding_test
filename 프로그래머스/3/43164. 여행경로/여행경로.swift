import Foundation

func solution(_ tickets: [[String]]) -> [String] {
    let sortedTickets = tickets.sorted { $0[1] < $1[1] }
    
    var visited = Array(repeating: false, count: tickets.count)
    var path: [String] = []
    
    func dfs(_ current: String, _ route: [String], _ count: Int) -> Bool {
        if count == sortedTickets.count {
            path = route
            return true
        }
        
        for (i, ticket) in sortedTickets.enumerated() {
            if !visited[i] && ticket[0] == current {
                visited[i] = true 
                if dfs(ticket[1], route + [ticket[1]], count + 1) {
                    return true
                }
                visited[i] = false 
            }
        }
        
        return false
    }
    
    dfs("ICN", ["ICN"], 0)
    
    return path
}
