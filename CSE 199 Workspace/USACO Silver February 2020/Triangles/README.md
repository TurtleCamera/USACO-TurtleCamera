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
Total area = 
(a + b + c + d + e) * 
(f + g + h + i + j)

This should result in a solution that makes you sort twice in O(N * log(N)) time, and you traverse the points three times in O(N) time. This should be good enough for a silver question. Here is an ![image](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Triangle.jpg) showing some of the work that I wrote down on scratch paper to help me solve this problem.  
There are a few things to note about my approach:
1. Sort by Ys first
    - Make sure the compareTo function also sorts by X coordinate if we get two points with the same Y value.
    - This should group the points with the same Y coordinate pretty well.
2. Use dynamic programming on each subset of points with the same Y coordinate. For the point with the lowest X coordinate in this subset, compute the total edge lengths from all other points in that group to that point. Because the points in this subset are also sorted by X values, you can move up the points (move up the X values), using the previous point's total edge lengths to compute the total edge lengths (via dynamic programming) of the point you are currently visiting. Keep doing this for every subset of points.
3. Repeat 1 and 2, except sort by X's and compute the total edge lengths from each node in terms of the Y coordinate.
4. For each point, you've pretty much summed up all possible X edges connected to that point that could be used to make a right triangle as described in the problem. Now, all we have to do is multiply the total X edge lengths by the total Y edge lengths, mod that by 1000000007, add that value to the total area, and mod the resulting total area by that 1000000007 value again.
