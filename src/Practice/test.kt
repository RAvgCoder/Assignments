class Solution {
    fun majorityElement(nums: IntArray): Int {
        var highest = 0;
        var count = 0;

        for (n in nums) {
            if (n == highest) {
                count++
                continue;
            }
            count--
            if (count <= 0) {
                highest = n
            }
        }

        return highest
    }
}