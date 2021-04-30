import java.util.*;
import java.io.*;

class Cow_Jump {
    public static Scanner in;
    public static PrintWriter out;
    public static int N;
    public static Segment_Point [] points;
    public static Segment [] segments;
    public static TreeSet<Segment_Point> currentPoints;

    // Define the two lines that we need to search for before moving directly to the O(N)
    // remaining part of the solution
    public static Segment segment1 = null;
    public static Segment segment2 = null;

    public static void main(String[] args) throws IOException{
        // Initialize readers and writers
        in = new Scanner(new FileReader("cowjump.in"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));

        // Store all the points and lines
        N = in.nextInt();
        points = new Segment_Point[2 * N];
        segments = new Segment[N];
        for (int i = 0; i < 2 * N; i += 2) {
            // Index by 0 for now
            // Create end points
            Segment_Point startingPoint = new Segment_Point(in.nextInt(), in.nextInt(), i / 2);
            Segment_Point endingPoint = new Segment_Point(in.nextInt(), in.nextInt(), i / 2);

            // Set the partners of each point
            startingPoint.otherPoint = endingPoint;
            endingPoint.otherPoint = startingPoint;

            // Create and store the segment
            segments[i / 2] = new Segment(startingPoint, endingPoint);
            startingPoint.segment = segments[i / 2];
            endingPoint.segment = segments[i / 2];
            points[i] = startingPoint;
            points[i + 1] = endingPoint;
        }
        
        // Sort the array
        Arrays.sort(points);

        // Start using the Sweep Line algorithm. Note that we're supposed to sort by increasing
        // y values for this part
        Segment_Point.sortByX = false;
        currentPoints = new TreeSet<Segment_Point>();

        // Start going through all the points via the Sweeping Line algorithm
        for(int i = 0; i < points.length; i ++) {
            if (points[i].visited) {
                // We don't want to keep this segment/point in the set when calling the floor
                // and ceiling functions for this case
                currentPoints.remove(points[i].otherPoint);

                // Check if the adjacent points intersect with out current point
                if(checkAdjacents(points[i])) {
                    // Stop if we found our potential solution
                    break;
                }
            }
            else {
                // Check if the adjacent points intersect with out current point
                if(checkAdjacents(points[i])) {
                    // Stop if we found our potential solution
                    break;
                }

                // Add these points and mark them as visited
                currentPoints.add(points[i]);
                points[i].visited = true;
                points[i].otherPoint.visited = true;
            }
        }

        // Given our two segments, we need to determine which one to output as the answer. Do this by checking how many times
        // each segment intersects with another segment.
        int numIntersections1 = 0;
        int numIntersections2 = 0;
        for (int i = 0; i < N; i++) {
            if (isIntersecting(segment1.point1, segment1.point2, segments[i].point1, segments[i].point2)) {
                numIntersections1 ++;
            }

            if (isIntersecting(segment2.point1, segment2.point2, segments[i].point1, segments[i].point2)) {
                numIntersections2 ++;
            }
        }

        // Use the number of intersections we found for each of the segments to determine which one to remove
        if (numIntersections1 > numIntersections2) {
            // Remember to index by 1
            out.println(segment1.point1.index + 1);
        }
        else if (numIntersections2 > numIntersections1) {
            // Remember to index by 1
            out.println(segment2.point1.index + 1);
        }
        else {
            // This means we got the case that only two lines intersect out of all lines in the sample case
            if (segment1.point1.index < segment2.point1.index) {
                out.println(segment1.point1.index + 1);
            }
            else {
                out.println(segment2.point1.index + 1);
            }
        }

        // Close streams
        in.close();
        out.close();
    }

    // Checks to see if adjacent points are part of segments that intersect with the given point.
    // Return true if we found something, so we can tell our algorithm to stop.
    public static Boolean checkAdjacents(Segment_Point point) {
        // Check if the point that comes before intersects with out current point
        Segment_Point adjacent = currentPoints.floor(point.otherPoint);
        if (adjacent != null && checkIntersection(point, adjacent)) {
            segment1 = segments[point.index];
            segment2 = segments[adjacent.index];

            return true;
        }

        // Check if the point that comes after intersects with out current point
        adjacent = currentPoints.ceiling(point.otherPoint);
        if (adjacent != null && checkIntersection(point, adjacent)) {
            segment1 = segments[point.index];
            segment2 = segments[adjacent.index];

            return true;
        }

        return false;
    }

    public static int getOrientation (Segment_Point point1, Segment_Point point2, Segment_Point point3) {
        double rotationArea = (point2.x - point1.x) * (point3.y - point1.y) -
                              (point3.x - point1.x) * (point2.y - point1.y);

        // Compute the orientation of these three points
        
        // Collinear case
        if(rotationArea == 0) {
            return 1;
        }

        if(rotationArea > 0) {
            // Clockwise case
            return 2;
        }
        else {
            // Counter-clockwise case
            return 3;
        }
    }

    public static Boolean isOnSegments(Segment_Point point1, Segment_Point point2, Segment_Point point3) {
        // Basically uses these 3 points to check if a point 1 lies on the segment (point2 and point3)
        if(((point2.x <= point1.x && point1.x <= point3.x) || 
            (point3.x <= point1.x && point1.x <= point2.x)) &&
           ((point2.y <= point1.y && point1.y <= point3.y) || 
            (point3.y <= point1.y && point1.y <= point2.y))) {
            return true;
        }

        return false;
    }

    // Check intersection, given points from two lines
    public static Boolean checkIntersection(Segment_Point segment1Point, Segment_Point segment2Point) {
        // This will get the two points of each line for us
        return isIntersecting(segment1Point, segment1Point.otherPoint, segment2Point, segment2Point.otherPoint);
    }

    public static Boolean isIntersecting (Segment_Point point1, Segment_Point point2, 
                                          Segment_Point point3, Segment_Point point4) {
        if (getOrientation(point1, point3, point4) == 1 && 
            getOrientation(point2, point3, point4) == 1 && 
            getOrientation(point3, point1, point2) == 1 && 
            getOrientation(point4, point1, point2) == 1) {
            // Special cases in which these 3D points are all collinear, and the projections
            // of each line intersect
            if(isOnSegments(point1, point3, point4) || isOnSegments(point2, point3, point4) || isOnSegments(point3, point1, point2)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            // General case of (p1, q1, p2) and (p1, q1, q2) having different orientations
            // or (p2, q2, p1) and (p2, q2, q1) having different orientations
            if((getOrientation(point3,point1,point2) != getOrientation(point4,point1,point2)) && 
              (getOrientation(point1,point3,point4) != getOrientation(point2,point3,point4))) {
                return true;
            }
            else {
                return false;
            }
        }
    }
}

class Segment {
    public Segment_Point point1;
    public Segment_Point point2;

    public Segment (Segment_Point point1, Segment_Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }
}
    
class Segment_Point implements Comparable<Segment_Point> {
    public int x;
    public int y;
    public int index;  // index in of this point in the input file
    public Segment_Point otherPoint;    // Other point connected to this point
    public Segment segment; // The segment that this Segment_Point belongs to
    public Boolean visited; // Marks if this point (and it's partner point) have been visited
    public static Boolean sortByX = true;   // Tells the sorting algorithms which variable to sort by

    public Segment_Point (int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.visited = false;
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
}