package com.maiconhellmann.data

import org.junit.Test
import kotlin.math.ceil

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun test() {
        print("solution ${solution(intArrayOf(0,3,5,4,2,1))}")
    }
    fun solution(A: IntArray): Int {

        if (A.isEmpty()) return 1

        val sorted = A.sortedArray()

        val result = sorted.filterIndexed { index, i ->
            if (index > 0) {
                i - sorted[index-1] > 1
            } else {
                false
            }
        }.firstOrNull() ?: return if (sorted.first() == 2) 1 else sorted.last() +1

        return result -1
    }
}
