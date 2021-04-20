# Information about Mountain View
### Summarized concepts
  - N is large, so the algorithm should be at least as good as O(N * log(N))
  - Visualize the problem in a different way: don't think of it as peaks. Convert the problem to an interval problem
      - Famous interval scheduling problem (greedy version)
      - Sort by start times, but choose the left-most end time, such that the interval isn't covered by any of the other intervals.
  - Merge sort.
      - compareTo function.

### Explanation
We know from the problem that if a point can't be seen, then its triangle mountain is fully under another triangle mountain's boundary. This also implies that base of that mountain is also within the base of the mountain with the peak on top. This means we can translate this problem into an interval scheduling problem, such that the intervals of each point are (x - y, x + y). We can then use a greedy algorithm to solve this problem. Here are the screenshots that led me to figuring this out: (page 1)[https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Mountain_View_1.jpg] and (page 2)[https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Mountain_View_2.jpg]. Below is an outline of my thought process:  
1) Convert all the points into intervals
2) Sort all the intervals by starting points
3) For the greedy algorithm, keep going through these sorted intervals until you come across an ending point of the interval that isn't covered by other intervals.
    - There are a few edge cases I considered:
        - Intervals have matching start points:
            - This is the edge case that matters. In this case, you need to pick the furthest ending point, because interval with the furthest ending point in this situation will not be covered by any of the other intervals with the same starting point.
        - Intervals have matching end points:
            - Because we're going in the other of sorted starting points, this should solve itself automatically.
    - Given the above information, this is how we'll need to make the compareTo function:
        - Prioritize sorting by starting point.
        - If the starting points are the same, then prioritize the right-most ending point.
        - Basically, this means sort by starting point in increasing order, but sort by ending point in decreasing order if we need to.
