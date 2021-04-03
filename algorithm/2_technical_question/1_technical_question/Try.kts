class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        // O(N^2)
//        for (num in nums) {
//            for (num2 in nums) {
//                if (num + num2 == target) {
//                    return intArrayOf(num, num2)
//                }
//            }
//        }
        // O(N)
        var candiate = mutableMapOf<Int, Int>()
        nums.forEachIndexed { index, i ->
            candiate.put(target - i, index)
        }
        nums.forEachIndexed { index, i ->
            if (candiate.contains(i)) {
                return intArrayOf(index, candiate.get(i)!!)
            }
        }
        return intArrayOf()
    }
}

println(Solution().twoSum(intArrayOf(1, 2, 3, 4), 3).toList())