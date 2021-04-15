# Information about Berry Picking
### Summarized concepts
  - Understand that an O(N^2) solution works because N <= 1,000.
  - Merge sort.
  - Specific cap on berries per bucket for Elsie.
    - Do not make an array of K size for the buckets you filled. You can use this threshold to compute how many buckets you would have filled.
    - Heavy use of the modulo operator.
  - Max value.
  - For loop with two conditions.

### Explanation
Note that N is actually very small, so O(N^2) could be feasible, which it in fact is. For this problem, the first thing you should understand is that the more you give Elsie, the less Bessie will have -- that is, Bessie should be as selfish as possible, because we only care about ourselves, instead of our sibling, right?  Jokes aside, 
