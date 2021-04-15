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
Note that N is actually very small, so O(N^2) could be feasible, which it in fact is. For this problem, the first thing you should understand is that the more you give Elsie, the less Bessie will have -- that is, Bessie should be as selfish as possible, because we only care about ourselves, instead of our sibling, right?  Jokes aside, there is a sweet spot/threshold that you want to aim for. This is basically a cap on the number of berries per basket that Elsie should receive. Because we're putting a cap on the number of berries that Elsie can receive, it makes it so we can reserve more berries on trees for Bessie. Thus, this is how I came up with the code:
1. Read in the array, but note that you don't actually have to sort it here, as long as you aren't using the method of creating a K length array of buckets. This is because we're going to count the number of full buckets just by dividing each number of berries on trees by the threshold value.
2. Next, do what I mentioned above, which is to see how many buckets we can fill to the threshold by dividing each number of berries on trees by the threshold value and add the resulting quotient to the number of full buckets.
3. Let T be the threshold value. There are three cases to check for:
    - number of buckets filled is >= K:
        - Basically, everyone gets the same number of berries, so Bessie will get T * K / 2.
    - number of buckets filled is between K / 2 and K - 1 inclusive:
        - The threshold is low enough that Elsie receives buckets of equal number of berries. In this case, you would want to copy over the number of berries and mod them by T. You need to do this because you already determined the number of buckets filled to T on that berries array. Bessie will then receive berries from the remaining buckets that she didn't give to Elsie plus the berries in the buckets used to collect the remaining berries off trees. Note that you will want to sort the array containing the remaining number of berries in each of the trees, but in Java, unless you provided a comparator function for Integers, it will sort in ascending order, so you will want to traverse the array backwards.
    - number of buckets filled is <= K / 2:
        - Well, Bessie just lost a ton of berries. I didn't realize this at the time, but the solution on USACO's website says to ignore this case because the threshold that causes the bare minimum number of buckets filled to T to be greater than K / 2 should always cause Bessie to get more berries than Elsie would. Nevertheless, I still calculated this. You would pretty much do the same thing as above, but you only start counting the berries from trees after you fill K / 2 buckets. Note that you will most likely have empty buckets in this result, which is another reason why this case is probably ignored.
