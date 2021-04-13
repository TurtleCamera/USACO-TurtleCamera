# Information about Triangle
### Summarized concepts
  - Dynamic programming with very mild memoization
  - Get ready for a relatively straightforward algorithm, but complicated implementation.
  - Merge sort
    - Override compareTo
  - Coordinates
  - 

### Explanation
N and K are massive, which means the time complexity limit is probably very strict; just from the numbers, it should immediately be a red flag, that you that you need a linear time solution to pass all the test cases. Honestly, I have a hard time believing this is a silver question, because when I used to do USACO back in high school, I never got questions nearly as hard as this one. Basically, to solve this question, you need to compress the problem into smaller subproblems extremely hard, except that you don't actually use memoization. This is pretty much what happened in my mind while I was pondering and implementing the question and solution respectively:
1. Each iteration of K is just repeating all M exercises. If you compute the final cow indices after those M exercises, that should just directly give you the resulting locations of the cows at each index at the end of each iteration of K. Thus, we don't need to compute each exercise more than once. In fact, you probably don't even need to store information about these M exercises.
2. For each cow, use that new array of permutations (resulting array of M exercises mentioned in #1) to compute where each cow will end up after one iteration of K. Keep repeating this until the cow lands back in its original position (or until we hit K iterations, which is probably unlikely because K is insanely big). 
3. You can further reduce computations by marking each index as visited/isComputed. This is because once you touch an index, then we know a cycle would eventually be computed for it. It would save a ton of time to not repeat a cycle a bunch of times.
4. One big way to reduce the number of full cycle traversals is to check the length of the cycle your starting cow is in, and mod K by it. This pretty much eliminates the need to traverse the cycle more than once; however, there's actually a way to optimize this even further.
    - While you're computing the cycles in the first place, use an array to store the cows that appear in this cycle. Once the cycle has been fully completed, traverse it one more time and calculate the solution location of each cow directly by using the mod strategy from #4, which will reduce the number of traverals of the cycle all the way down to only 2.
