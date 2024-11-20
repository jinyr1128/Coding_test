import Foundation

struct Heap<T: Comparable> {
    private var elements: [T]
    private let order: (T, T) -> Bool

    init(order: @escaping (T, T) -> Bool) {
        self.elements = []
        self.order = order
    }

    var isEmpty: Bool { elements.isEmpty }

    func peek() -> T? {
        return elements.first
    }

    mutating func insert(_ value: T) {
        elements.append(value)
        siftUp(from: elements.count - 1)
    }

    mutating func remove() -> T? {
        guard !elements.isEmpty else { return nil }
        if elements.count == 1 { return elements.removeLast() }

        let root = elements.first
        elements[0] = elements.removeLast()
        siftDown(from: 0)
        return root
    }

    private mutating func siftUp(from index: Int) {
        var child = index
        var parent = (child - 1) / 2

        while child > 0 && order(elements[child], elements[parent]) {
            elements.swapAt(child, parent)
            child = parent
            parent = (child - 1) / 2
        }
    }

    private mutating func siftDown(from index: Int) {
        var parent = index
        let count = elements.count

        while true {
            let left = 2 * parent + 1
            let right = left + 1
            var candidate = parent

            if left < count && order(elements[left], elements[candidate]) {
                candidate = left
            }
            if right < count && order(elements[right], elements[candidate]) {
                candidate = right
            }
            if candidate == parent { return }

            elements.swapAt(parent, candidate)
            parent = candidate
        }
    }
}

func solution(_ operations: [String]) -> [Int] {
    var minHeap = Heap<Int>(order: <) // 최소 힙
    var maxHeap = Heap<Int>(order: >) // 최대 힙
    var validSet = Set<Int>() // 유효한 값 관리

    for operation in operations {
        let parts = operation.split(separator: " ")
        let command = String(parts[0])
        let value = Int(parts[1])!

        if command == "I" {
            minHeap.insert(value)
            maxHeap.insert(value)
            validSet.insert(value)
        } else if command == "D" {
            if validSet.isEmpty { continue }

            if value == 1 {
                // 최댓값 삭제
                while let maxVal = maxHeap.peek(), !validSet.contains(maxVal) {
                    _ = maxHeap.remove()
                }
                if let maxVal = maxHeap.remove() {
                    validSet.remove(maxVal)
                    while let minVal = minHeap.peek(), minVal == maxVal {
                        _ = minHeap.remove()
                    }
                }
            } else if value == -1 {
                // 최솟값 삭제
                while let minVal = minHeap.peek(), !validSet.contains(minVal) {
                    _ = minHeap.remove()
                }
                if let minVal = minHeap.remove() {
                    validSet.remove(minVal)
                    while let maxVal = maxHeap.peek(), maxVal == minVal {
                        _ = maxHeap.remove()
                    }
                }
            }
        }
    }

    // 유효하지 않은 값을 정리
    while let maxVal = maxHeap.peek(), !validSet.contains(maxVal) {
        _ = maxHeap.remove()
    }
    while let minVal = minHeap.peek(), !validSet.contains(minVal) {
        _ = minHeap.remove()
    }

    if validSet.isEmpty {
        return [0, 0]
    } else {
        return [maxHeap.peek()!, minHeap.peek()!]
    }
}
