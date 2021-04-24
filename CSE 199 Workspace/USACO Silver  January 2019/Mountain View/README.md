# Information about Mountain View
### Summarized concepts
  - N is large, so the algorithm should be at least as good as O(N * log(N))
  - Visualize the problem in a different way: don't think of it as peaks. Convert the problem to an interval problem
      - Famous interval scheduling problem (greedy version).  
      - Sort by start times, but choose the left-most end time, such that the interval isn't covered by any of the other intervals.
  - Merge sort.
      - compareTo function.  

###### What I learned from the problem
  - Bessie is going sightseeing, and she sees N peaks of mountains represented as N points on a plane.  
      - These mountains are shaped as 45-45-90 isosceles triangles.  
  - She can only see a mountain if the peak's point is not "obscured" by another point -- that is, the peak not "under another mountain's shape." In other words, the peak of a point shouldn't be inside another isosceles triangle.

###### Some ideas that I got from the problem
I initially went the wrong direction. I forgot to look at the maximum value of N, and thought that perhaps making a ton of y = m*x + b lines would do the trick, and checking if any points lie below each of these lines. Then, I thought about sorting these values in terms of y values and going either bottom to top or from top to bottom. Eventually, I realized that I might be going a brute force route, and finally remembered to look at the maximum N value. It turns out that the maximum N value is 10^5, which tells me that some O(N * log(N)) solution is involved, so that basically ruled my tought process out.  

###### Visualization of the sample test case
I ended up only drawing the sample test case and just starting at it to see if I could notice any patterns (ignore the bottom half of the page for now).  
![page 1](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Mountain_View_1.jpg)  
It took me a while to realize this, but a mountain isn't just obscured if a point is inside of another triangle. The whole triangle of that obscured mountain must be inside another triangle. Then I looked at the base of the mountains and realized that the base of the obscured mountain must be "covered" by a base of another mountain. That was the Eureka moment -- this problem is actually disguised as the famous interval scheduling problem. Unfortunately, I'm not sure how to explain this to a high school student who has never heard of this problem before, so I'll just continue explaining my thought process.  

###### Greedy interval problem 
We now need to take a look at the bottom half of my first notebook paper page.  
![page 1](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Mountain_View_1.jpg)   
In my mind, I thought about how long it would take to convert this to an interval problem. Doing that would just be a trivial case of O(N) time. But how do we go about figuring out which out which ones aren't obscured? Typically when I see an interval problem, I either sort by the starting points or the ending points. I wasn't really sure which approach to check first, so I guessed and went with sorting by starting points. In the image, I put a check next to the intervals that aren't obscured and an X next to the intervals that are obscured. So far, it looks like to me that I should keep these intervals sorted by starting point, but look at the ending points when determining if an interval is obscured. After looking at the intervals for a bit longer, I realized that the sorting by starting points might work out in the end, because the ascending order of starting points makes is so if the ending points of later intervals comes before the ending point of my current interval, then it's guaranteed to be obscured, so I should skip them. To elaborate on what I mean, I will illustrate this with an image below.   
![page 1](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Mountain_View_3.jpg)  
Notice that the starting points are guaranteed to be on the right side of my selected interval, so any ending points that are on the left of the dotted red line (marking my ending point location) will make those intervals obscured. Thus, we have an O(N) runtime for this part.  

###### Edge cases  
Once I got that figured out, I then realized a problem of matching starting points and matching ending points. I first dealt with the matching ending points. This case really isn't that much different from the case that I illustrated in the image above.  
[page 2](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Mountain_View_4.jpg)  
The ending points are still not past the red dotted line, so the ascending order of starting points will force the mountain to be obscured.  
The important part, however, is dealing with the matching starting points. This is where caution must be exercised, because you do need to select a specific interval to make sure it's not obscured. Looking at my scratch paper below, I quickly determined that if we are to have matching starting points, I should select the interval with the furthest ending point, because all other intervals with matching starting points will be obscured due to having ending points on the left side of my selected ending point.  
[page 2](https://github.com/TurtleCamera/USACO-TurtleCamera/blob/main/CSE%20199%20Workspace/images/Mountain_View_2.png)  

###### Sorting
Now that I have that sorted out, I need to figure out how to make this work in the first place. It's pretty clear that some merge sort is going to be involved. After all, we deterimined from the maximum N value of 10^5 that the solution is probably O(N * log(N)) anyway. The part we need to be careful is the edge case. Since we're on Java, we can make use of the compareTo function in the Comparable interface. We first prioritize sorting by starting points in ascending order. Once we get matching starting points, however, we should sort by ending points in __DECREASING__ order (make sure it's decreasing, because I got stuck on one wrong problem for 5 minutes).  

### Pseudocode
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
