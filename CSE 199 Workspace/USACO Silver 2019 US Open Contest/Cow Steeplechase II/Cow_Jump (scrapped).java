import java.io.*;
import java.util.*;
import java.awt.Point;
import java.lang.Math;

public class Cow_Jump {
    public static Scanner in;
    public static PrintWriter out;
    public static int N;
    public static void main(String[] args) throws IOException {
        // Initialize readers and writers
        in = new Scanner(new FileReader("cowjump.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));

        // Get input information
        N = in.nextInt();   // Number of segments

        // Store all the points
        Segment_Point [] points = new Segment_Point[2 * N];
        for(int n = 0; n < 2 * N; n += 2) {
            points[n] = new Segment_Point(in.nextInt(), in.nextInt(), n / 2);
            points[n + 1] = new Segment_Point(in.nextInt(), in.nextInt(), n / 2);
            points[n].otherPoint = points[n + 1];
            points[n + 1].otherPoint = points[n];
        }

        // Sort by x coordinates
        Arrays.sort(points);

        // For debug purposes
        for(int i = 0; i < points.length; i ++) {
            System.out.println(points[i].x + " " + points[i].y);
            System.out.println(points[i].otherPoint.x + " " + points[i].otherPoint.y);
        }
        
        // For the Sweep Line algorithm, we need to sort the points by y values in the
        // TreeSet
        Segment_Point.sortByX = false;
        TreeSet<Segment_Point> currentPoints = new TreeSet<Segment_Point>();

        // Loop through all the points in the array, although note that for us to shortcut
        // to the remaining O(N) part of the solution, we only need to find one pait of
        // intersecting segments
        Segment_Point point1 = null;
        Segment_Point point2 = null;
        System.out.println(points.length);
        for(int i = 0; i < points.length; i ++) {
            System.out.println(points[i]);
            // This point could either be a starting point or an ending point.
            // WARNING: WHEN USING FLOOR OR CEILING, DO NOT CALL THE FUNCTION IF THE NODE EXISTS
            //          INSIDE THE TREE, OTHERWISE THE FUNCTION WILL RETURN ITSELF. REMOVE THE NODE
            //          BEFORE CALLING THESE FUNCTIONS
            Segment_Point before;
            Segment_Point after;
            Boolean isStartingPoint = true; // Use this to tell if points[i] is a starting point
            if(!points[i].visited) {
                // This is an ending poing
                isStartingPoint = false;
                System.out.println("added");
                // Find adjacent points before adding the node in
                before = currentPoints.floor(points[i]);
                after = currentPoints.ceiling(points[i]);

                // Not visited means that this is a starting point, since we've never seen this
                // line before.
                // Add this point to the TreeSet.
                currentPoints.add(points[i]);

                // Mark both points of this line as visited.
                points[i].visited = true;
                points[i].otherPoint.visited = true;
            }
            else {
                System.out.println("removed");
                // We have visited this point before, which means this is the ending point of
                // the line. We don't want to keep this line in the set when getting the
                // adjacent points, so we need to remove it.
                // Remove this point from the TreeSet.
                currentPoints.remove(points[i].otherPoint);

                // Find points after we remove the node. We should be calling floor and ceiling based
                // on the starting point, because that's what all the points in the set are based on.
                before = currentPoints.floor(points[i].otherPoint);
                after = currentPoints.ceiling(points[i].otherPoint);
            }

            // Check the adjacent points to see if our current node intersects with the
            // segments attributed to them
            System.out.print(points[i]);
            System.out.print(before);
            System.out.print(after);
            System.out.println();

            // Do these the lines connected to these adjacent points intersect with our
            // current point? Make sure we're not using null points
            if(before != null && before.isIntersecting(points[i])) {
                System.out.println("Before works");
                point1 = points[i];
                if(!isStartingPoint) {
                    point1 = point1.otherPoint;
                }
                point2 = before;
                break;
            }

            if(after != null && after.isIntersecting(points[i])) {
                System.out.println("After works");
                point1 = points[i];
                if(!isStartingPoint) {
                    point1 = point1.otherPoint;
                }
                point2 = after;
                break;
            }
        }

        // Shortcut to the O(N) part of the solution, which is to count the number of intersections
        int numIntersect1 = 0;
        int numIntersect2 = 0;
        System.out.println(point1);
        System.out.println(point2);
        for(int i = 0; i < points.length; i += 2) {
            if(point1.isIntersecting(points[i])) {
                System.out.println(i + " intersecting point 1");
                numIntersect1 ++;
            }

            if(point2.isIntersecting(points[i])) {
                System.out.println(i + " intersecting point 2");
                numIntersect2 ++;
            }
        }

        System.out.println(numIntersect1);
        System.out.println(numIntersect2);

        // Choose the line with more intersections
        if(numIntersect1 > numIntersect2) {
            out.println(point1.index + 1);
        }
        else if(numIntersect1 < numIntersect2) {
            out.println(point2.index + 1);
        }
        else {
            // Output whichever index comes first
            if(point1.index < point2.index) {
                out.println(point1.index + 1);
            }
            else {
                out.println(point2.index + 1);
            }
        }

        // Close streams
        in.close();
        out.close();
    }
}

class Segment_Point extends Point implements Comparable<Segment_Point> {
    public Segment_Point otherPoint;    // Other end point of the segment
    public static Boolean sortByX = true;   // Tells the sorting algorithms which variable to sort by
    public Boolean visited;
    public int index;

    public Segment_Point(int x, int y, int index) {
        super(x, y);
        visited = false;
        this.index = index;
    }

    @Override
    public int compareTo(Segment_Point other) {
        // Sort depending on which variable we want to sort by
        if(sortByX) {
            if(this.x > other.x) {
                return 1;
            }
            else if(this.x < other.x) {
                return -1;
            }
            else {
                if(this.y > other.y) {
                    return 1;
                }
                else if(this.y < other.y) {
                    return -1;
                }
            }
        }
        else {
            if(this.y > other.y) {
                return 1;
            }
            else if(this.y < other.y) {
                return -1;
            }
            else {
                if(this.x > other.x) {
                    return 1;
                }
                else if(this.x < other.x) {
                    return -1;
                }
            }
        }
        
        // Points are the same
        return 0;
    }

    public Boolean isIntersecting(Segment_Point other) {
        // If this point and the other point are the end points of the
        // same line, then return false
        if(this.otherPoint == other || this == other) {
            return false;
        }

        // Get the orientations of each point
        int orientation1 = getOrientation(this, other, this.otherPoint);
        int orientation2 = getOrientation(this.otherPoint, other, other.otherPoint);
        int orientation3 = getOrientation(other, this, this.otherPoint);
        int orientation4 = getOrientation(this.otherPoint, this, this.otherPoint);

        // Special cases in which these 3D points are all collinear, and the projections
        // of each line intersect
        if(orientation1 == 1 && orientation2 == 1 && orientation3 == 1 && orientation4 == 1) {
            if(isOnSegment(this, other, other.otherPoint) || isOnSegment(this.otherPoint, other, other.otherPoint) || isOnSegment(other, this, this.otherPoint)) {
                return true;
            }
            else {
                return false;
            }
        }

        // General case of (p1, q1, p2) and (p1, q1, q2) having different orientations
        // or (p2, q2, p1) and (p2, q2, q1) having different orientations
        if(orientation3 != orientation4 && orientation1 != orientation2) {
            return true;
        }
        else {
            return false;
        }
    }

    public int getOrientation(Segment_Point point1, 
                              Segment_Point point2, 
                              Segment_Point point3) {
        // Get the state of orientation
        int state = (point2.x - point1.x) * (point3.y - point1.y) -
                    (point2.y - point1.y) * (point3.x - point1.x);
        
        // Collinear case
        if(state == 0) {
            return 1;
        }

        if(state > 0) {
            // Clockwise case
            return 2;
        }
        else {
            // Counter-clockwise case
            return 3;
        }
    }

    public Boolean isOnSegment(Point point1, Point point2, Point point3) {
        if(((point2.x <= point1.x && point1.x <= point3.x) || 
            (point3.x <= point1.x && point1.x <= point2.x)) &&
           ((point2.y <= point1.y && point1.y <= point3.y) || 
            (point3.y <= point1.y && point1.y <= point2.y))) {
            return true;
        }

        return false;
    }

    // public Boolean isIntersecting(Segment_Point other) {
    //     // If this point and the other point are the end points of the
    //     // same line, then return false
    //     if(this.otherPoint == other || this == other) {
    //         return false;
    //     }

    //     // Get the orientations of each point
    //     int orientation1 = getOrientation(this, other, this.otherPoint);
    //     int orientation2 = getOrientation(this, other, other.otherPoint);
    //     int orientation3 = getOrientation(this.otherPoint, other.otherPoint, this);
    //     int orientation4 = getOrientation(this.otherPoint, other.otherPoint, this);

    //     // General case of (p1, q1, p2) and (p1, q1, q2) having different orientations
    //     // or (p2, q2, p1) and (p2, q2, q1) having different orientations
    //     if(orientation1 != orientation2 && orientation3 == orientation4) {
    //         return true;
    //     }

    //     // Special cases in which these 3D points are all collinear, and the projections
    //     // of each line intersect
    //     if(orientation1 == 1 && isOnSegment(this, this.otherPoint, other)) {
    //         return true;
    //     }
    //     else if(orientation2 == 1 && isOnSegment(this, other.otherPoint, other)) {
    //         return true;
    //     }
    //     else if(orientation3 == 1 && isOnSegment(this.otherPoint, this, other.otherPoint)) {
    //         return true;
    //     }
    //     else if(orientation4 == 1 && isOnSegment(this.otherPoint, other, other.otherPoint)) {
    //         return true;
    //     }
    //     else {
    //         return false;
    //     }
    // }

    // public int getOrientation(Segment_Point point1, 
    //                           Segment_Point point2, 
    //                           Segment_Point point3) {
    //     // Get the state of orientation
    //     int state = (point2.y - point1.y) * (point3.x - point2.x) -
    //                 (point2.x - point1.x) * (point3.y - point2.y);
        
    //     // Collinear case
    //     if(state == 0) {
    //         return 1;
    //     }

    //     if(state > 0) {
    //         // Clockwise case
    //         return 2;
    //     }
    //     else {
    //         // Counter-clockwise case
    //         return 3;
    //     }
    // }

    // public Boolean isOnSegment(Point point1, Point point2, Point point3) {
    //     if((point2.x <= Math.max(point1.x, point3.x)) && 
    //        (point2.x >= Math.min(point1.x, point3.x)) &&
    //        (point2.y <= Math.max(point1.y, point3.y)) &&
    //        (point2.y >= Math.min(point1.y, point3.y))) {
    //         return true;
    //     }

    //     return false;
    // }

    // public Boolean isIntersecting(Segment_Point other) {
    //     // A refers to this line, while B refers to the other line
    //     // If this point and the other point are the end points of the
    //     // same line, then return false
    //     if(this.otherPoint == other || this == other) {
    //         return false;
    //     }

    //     // Figure out these points just for simplicity
    //     Segment_Point startingA;
    //     Segment_Point endingA;
    //     Segment_Point startingB;
    //     Segment_Point endingB;

    //     if(isStartingPoint()) {
    //         startingA = this;
    //         endingA = this.otherPoint;
    //     }
    //     else {
    //         startingA = this.otherPoint;
    //         endingA = this;
    //     }

    //     if(other.isStartingPoint()) {
    //         startingB = other;
    //         endingB = other.otherPoint;
    //     }
    //     else {
    //         startingB = other.otherPoint;
    //         endingB = other;
    //     }

    //     // Compute the slops of each segment
    //     double slopeA = (endingA.y - startingA.y) / (endingA.x - startingA.x);
    //     double slopeB = (endingB.y - startingB.y) / (endingB.x - startingB.x);

    //     // Compute the intersecting point assuming the segments are lines
    //     double intersectingX = (slopeA * startingA.x - slopeB * startingB.x + startingB.y - startingA.y) / 
    //                            (slopeA - slopeB);

    //     double intersectingY = slopeA * (intersectingX - startingA.x) + startingA.y;

    //     // Return true only if this point is on both segments, which implies that these segments are 
    //     // "long enough" to intersect
    //     return (startingA.x <= intersectingX && intersectingX <= endingA.x) &&
    //            (startingB.x <= intersectingX && intersectingX <= endingB.x) &&
    //            (startingA.y <= intersectingY && intersectingY <= endingA.y) &&
    //            (startingB.y <= intersectingY && intersectingY <= endingB.y);
    // }

    // // Returns true "this" point is the starting point
    // public Boolean isStartingPoint() {
    //     // Prioritize the x coordinate over the y one when dealing with intersections
    //     if(this.x < this.otherPoint.x) {
    //         return true;
    //     } 
    //     else if(this.x > this.otherPoint.x) {
    //         return false;
    //     }
    //     else {
    //         if(this.y < this.otherPoint.y) {
    //             return true;
    //         } 
    //         else if(this.y > this.otherPoint.y) {
    //             // Can't have same starting and ending points, so it's fine to ignore the
    //             // last case
    //             return false;
    //         }
    //     }

    //     return false;
    // }
}