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
  - -
  
  
About an hour passed, and that's when it hit me.

This should result in a solution that makes you sort twice in O(N * log(N)) time, and you traverse the points three times in O(N) time. This should be good enough for a silver question. Here is an ![image](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle.jpg) showing some of the work that I wrote down on scratch paper to help me solve this problem.  
There are a few things to note about my approach:
1. Sort by Ys first
    - Make sure the compareTo function also sorts by X coordinate if we get two points with the same Y value.
    - This should group the points with the same Y coordinate pretty well.
2. Use dynamic programming on each subset of points with the same Y coordinate. For the point with the lowest X coordinate in this subset, compute the total edge lengths from all other points in that group to that point. Because the points in this subset are also sorted by X values, you can move up the points (move up the X values), using the previous point's total edge lengths to compute the total edge lengths (via dynamic programming) of the point you are currently visiting. Keep doing this for every subset of points.
3. Repeat 1 and 2, except sort by X's and compute the total edge lengths from each node in terms of the Y coordinate.
4. For each point, you've pretty much summed up all possible X edges connected to that point that could be used to make a right triangle as described in the problem. Now, all we have to do is multiply the total X edge lengths by the total Y edge lengths, mod that by 1000000007, add that value to the total area, and mod the resulting total area by that 1000000007 value again.
