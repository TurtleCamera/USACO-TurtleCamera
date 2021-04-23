# Information about Triangle
### Summarized concepts
  - Dynamic programming with very mild memoization.
  - Get ready for a relatively straightforward algorithm, but complicated implementation.
  - Merge sort.
    - Override compareTo.
  - Coordinates.
  - Focus on points with the same X coordinate (or same Y coordinate depending on what you sorted by first).
    - Think of a cross shape.
  - Massive output number.
    - Remember to mod by what they gave you in the question.
    - Apparently even an int is not large enough because it causes the solution to overflow. Unless the competitor's int value is 64 bits, then use a long to store the value instead.

### Explanation
###### What I learned from the problem
  - There are a ton of points on the field such that Farmer John can put a fence post. None of the points are duplicates.
  - Farmer John wants wants to make a bunch of right triangles out of these points, but the catch is that he wants two of the sides of these right triangles to be aligned with the x and y axis of the field.  
      - He wants the sum of the areas of all possible triangles.
  - Learning from my mistakes in the Clock Tree problem, I looked at the maximum size of N. N is at most 10^5, and usually when I see a number like that on USACO, it suggests that some N * log(N) solution is involved. The first thing I thought of when I saw that is merge sort, but before I thought too deeply, I thought of how I would be able to compute the areas of all these triangles quickly. 
  - The apparently problem wants us to compute the area of squares instead of triangles because it guarantes integers.  

###### Some ideas after reading the problem
The first thing I thought of was to align all the points based on a point of focus -- that is, I find all the points with either the same x coordinate or same y coordinate as the point of focus as shown below.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_2.png)

Next, I imagined computing a row of triangles as shown below.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_3.png)

After that, I imagined drawing even more triangles.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_4.png)  

After thinking about it for a few minutes, I then came to the realization that the easiest way to compute the area of all these triangles is to sum all the distances from the focus point in the x direction, and sum all the distances from the focus point in the y direction, then multiply the two values together. To visualize this, I'll label some things below.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_6.png)  

From this, the total area of the triangles in the first quadrant is = (a + b + c + d + e) * (f + g + h + i + j).  

###### Some issues to consider about this problem
  - What I thought of may not necessarily be good enough -- this is too superficial -- because I need to make sure that all my operations don't exceeed O(N * log(N)) time.
  - The first thing I have to deal with is to figure out how to align all the points together in the way I imagined in the first place. The only thing I could think of is to use merge sort, which is perfect because of the O(N * log(N)) restriction I thought of.
  - As for computing the distances to each point of focus along an axis, this is where it gets really tough, so much to the point that I need a whole section for it.

###### Computing the distances in no more than O(N * log(N)) time
I was stuck on this for a while, so I decided to start off with a brute force idea to approaching this. Imagine a line of dots as shown in the screenshot below.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_7.png)  
I first thought of computing the distances between every pair of points.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_8.png)  
Obviously, this is an O(N^2) solution, but this at least got me thinking about the problem a little more deeply. I was stuck on this for about an hours, but I'll try to list a few things I kept in mind when trying to figure this out:    
  - The possible run times for solving this subproblem are O(log(N)), O(N), and O(N * log(N)). Out of these 3, the only one that really makes sense is the O(N) solution. This is because common algorithms you see on USACO are some variant of binary search and merge sort, neither which would really be helpful in this case, but I should still keep these log run times in mind. Overall, I was mainly focused on figuring out an O(N) solution, since it felt like it made the most sense.  
    - Given that the solution is probably O(N), it tells me that the only real way to really compute these by just traversing each points is to use information from one point of focus to compute information for other points of focus.
    - Eventually, this led me to think that perhaps a minimal dynamic programming solution -- if this can really be considered dynamic programming -- is probably required, although to think of a solution for this is where it became really hard.  

###### Executing the dynamic programming solution
About an hour passed, and that's when it hit me. We can visualize the distances as lines instead of curves in my images above. I have some drawings on paper shown below.
![image](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle.jpg)  
But it's a bit messy, so I'll redraw my idea on Microsoft paint.  
We can visualize the distances as adjacent lines sorted in increasing length from top to bottom.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_9.png)  
To compute the distances from the next point of focus, we need to cut some of these lines.
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_10.png)   
Then for the next point of focus, we do the same, but we have to add some of the trimmed lines back (in orange).
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_11.png)  
Then again.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_12.png)  
And again.
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_13.png)  
And finally, we reach the last point of focus on the same y level.
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_14.png)  
We're now getting really close to the solution. All we need to solve this subproblem is the formula needed to make this work. In the algorithm, I probably need to go through all the points once just to get the initial distances from the first point of focus. Before I can explain the dynamic programming part, I need to label a few things below.  
![Sample](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle_15.png)  
These are what each variable represents:  
  - L: length of the part we trimmed.
  - N: number of points (not used in the formula).
  - M: number of gaps between points.  
  - T: not shown on the screenshot, but this is the sum of all distances from a point of focus.  
  - G: not shown on the screenshot, but this is the number of gaps we've already traversed.
The formula for computing the distances from the next point of focus is therefore T(next) = T(current) - (M - G - 1) * L + (G - 1) * L.  
To explain this formula, (M - G - 1) * L represents the number of lines we end up trimming while (G - 1) * L represents the numbner of lines we add back. We subtract the former from T(current) and add the latter to T(current) to get T(next). Thus, we have our O(N) solution to this subproblem.  

There's one last thing we need to consider for this dynamic programming solution, however. How do we enable this dynamic programming solution to work in the first place? In the example I showed, we need to make sure all the points are on the same Y level, but at the same time, we also need to sort by the X coordinates in increasing order. Since we're on Java, the way I dealt with this is to make a class that extends Point but also implements Comparable. I will then Override the compareTo function to implement how Arrays.sort() will sort these points. One thing to note is that I need to compute these distances in both the X and Y direction, so I have a static variable that toggles how the compareTo function should sort. On one hand, when computing distances in the Y direction, I should be sorting by X and then sorting by Y if two points have the same X value. On the other hand, when computing the distances in the X direction, I should be sorting by Y first and then sorting by X if two points have the same Y value.

Then, the compute the rest of the distances from the next points of focus, I

There are a few things to note about my approach:
1. Sort by Ys first
    - Make sure the compareTo function also sorts by X coordinate if we get two points with the same Y value.
    - This should group the points with the same Y coordinate pretty well.
2. Use dynamic programming on each subset of points with the same Y coordinate. For the point with the lowest X coordinate in this subset, compute the total edge lengths from all other points in that group to that point. Because the points in this subset are also sorted by X values, you can move up the points (move up the X values), using the previous point's total edge lengths to compute the total edge lengths (via dynamic programming) of the point you are currently visiting. Keep doing this for every subset of points.
3. Repeat 1 and 2, except sort by X's and compute the total edge lengths from each node in terms of the Y coordinate.
4. For each point, you've pretty much summed up all possible X edges connected to that point that could be used to make a right triangle as described in the problem. Now, all we have to do is multiply the total X edge lengths by the total Y edge lengths, mod that by 1000000007, add that value to the total area, and mod the resulting total area by that 1000000007 value again.
