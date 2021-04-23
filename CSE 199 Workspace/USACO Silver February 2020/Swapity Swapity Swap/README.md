# Information about Swapity Swapity Swap
### Summarized concepts
  - Cycles.
    - Never end up traversing a cycle more than once in your algorithm.
  - Think of it in terms of a graph..., but don't actually implement it in terms of a graph.
  - Avoid repeating the same calculation over and over again -- use a smaller solution to solver a bigger solution. I think this counts as dynamic programming, because even though you use a small amount of memoization, you still calculate subproblems to solve bigger problems.
  - Visited/isComputed array.

###### What I learned from the problem
  - As soon as I saw the max values of N and K being 10^5 and 10^9, I immediately skipped the question and did it last. The big-O runtime will definitely involve both N and K, and it seems like you need some serious optimization to bring those values down. The value of M is 10^2, which is pretty low, but adding a few more 0's to the run time certainly won't be good.    
  - Farmer John has an exercise routine in which the cows perform M set of instructions that just reverses the order of the cows.  
      - Then, he wants the cows to repeat that routine K times.  

###### Some ideas after reading the problem
This is a really daunting question. No way can you even do O(N * K) because those values are too big. I had no idea where to start, so I don't have much to say here.  

###### Doing the sample test case
The sample test case tells the cows between indices 2 and 5 to reverse, and then the cows between indices 3 to 7 to reverse.  
2 5  
3 7  

This means the cows will line up as shown below after each step:  
Step 0: 1 2 3 4 5 6 7  
Step 1: 1 5 4 3 2 6 7  
Step 2: 1 5 7 6 2 3 4  

This is where I noticed the first pattern. We can think of this in terms of a graph. I thought of it as a graph that's lined up horizontally.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Swap_1.png)  
After completing all M steps, the last step shows where the cows will end up after those M steps, basically mapping out where the cows at each index on the graph will move to.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Swap_2.png)  
To elaborate on what I mean by this, 1 5 7 6 2 3 4 is the mapping such that each value shows where the cow at that index would have moved to. For example, the cow from index 5 will move to index 2, the cow from index 4 will move to index 7, and the cow from index 6 will move to index 4. Farmer John wants us to repeat this K <= 10^9 times, so one way to repeat this procedure is to compute these M steps only once, and store this mapping. Then we can reuse it over and over again like shown below.    
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Swap_6.png)      
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Swap_4.png)       
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Swap_5.png)   
Note that implementing this problem as a graph probably isn't very efficient, so I needed to think of other ideas.  

From what I see at this point, I couldn't really see a way to bring this down from O(N * M), which is at most 10^5 * 10^2 = 10^7, which still seems fine, but the K value of 10^9 is still a huge problem. It's usually fine to run something in 10^7 time in silver, but running something in 10^7 + 10^9 time is way too much.  

###### Thinking about dealing with K
I was pretty lost at this part. I thought maybe we could deal with the K iterations in O(log(K)) time, but algorithms that are common on USACO like binary search don't really seem applicable here. Whatever the solution to this problem is, it probably involves some clever manipulation of the problem that doesn't involve a predefined algorithm that we know of.  
  
After quite some time, I realized a pattern with the sample test case. Notice how some of the values go back to their original location after a certain number of iterations. For example, 1 never moves from its spot; thus, that cow will go back to its original location in 1 iteration of K. The cow starting from index 2 ends up going back to its original location in 2 iterations of K. This opens up a really good oppurtunity to simplify the K iterations. Essentially, instead of visualizing the problem as a graph of horizontal nodes, we can visualize this as a graph of cycles. Using the sample test case to illustrate what I mean, we can show where the values go.   
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Swap_7.png)   
After thinking about these cycles, I realized that the clever "manipulation" of these K iterations is just to cancel out all the cycles, so we don't repeat them. Technically, this solution is still O(K) time, but knowing USACO, they probably won't troll the students by giving a cycle that actually takes the full K = 10^9 iterations to compute, so it's probably safe to assume O(cycle length) instead. In code, we would compute the cycle length of each of the cycles, and mod K by that cycle length to figure out how many nodes we should traverse to get to the solution indices from each starting point.  

###### My plan backfires
I coded up the solution, but then found out that I still timed out on half the problems. It didn't take me long to realize what the issue was, though. The initial O(N * M) part was fine, but it was the way I approached the shortcut of the K iterations that was the problem. What I did was compute all the cycles, and then for each node in the cycle, I made the program traverse K % cycle length iterations on all of them, which basically means I inadvertently did it in O(K^2) (note that the cycles are significantly shorter than K in length, but the point is that something squared is bad). That apparently still causes the program to time out, so what I did instead was sort of modify my "graph" implementation of the code to store all the nodes in a cycle in an array. That way, once I'm done computing the cycle and the K % cycle length value, I can do one more loop around the cycle and use the array to point each node in the cycle directly to the final location it will end up at, which will then run in O(cycle length). This alone was enough to get all the problems correct.  

### Pseudocode
1. Each iteration of K is just repeating all M exercises. If you compute the final cow indices after those M exercises, that should just directly give you the resulting locations of the cows at each index at the end of each iteration of K. Thus, we don't need to compute each exercise more than once. In fact, you probably don't even need to store information about these M exercises.
2. For each cow, use that new array of permutations (resulting array of M exercises mentioned in #1) to compute where each cow will end up after one iteration of K. Keep repeating this until the cow lands back in its original position (or until we hit K iterations, which is probably unlikely because K is insanely big). 
3. You can further reduce computations by marking each index as visited/isComputed. This is because once you touch an index, then we know a cycle would eventually be computed for it. It would save a ton of time to not repeat a cycle a bunch of times.
4. One big way to reduce the number of full cycle traversals is to check the length of the cycle your starting cow is in, and mod K by it. This pretty much eliminates the need to traverse the cycle more than once; however, there's actually a way to optimize this even further.
    - While you're computing the cycles in the first place, use an array to store the cows that appear in this cycle. Once the cycle has been fully completed, traverse it one more time and calculate the solution location of each cow directly by using the mod strategy from #4, which will reduce the number of traverals of the cycle all the way down to only 2.

Note: what makes this question so difficult is that if you miss any of these steps, you will always get a timeout on some of the test cases.
