package kdtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Naive nearest neighbor implementation using a linear scan.
 */
public class NaivePointSet implements PointSet {
    List<Point> pointList = new ArrayList<>();

    /**
     * Instantiates a new NaivePointSet with the given points.
     * @param points a non-null, non-empty list of points to include
     *               (makes a defensive copy of points, so changes to the list
     *               after construction don't affect the point set)
     */
    public NaivePointSet(List<Point> points) {
        for (Point p : points) {
            this.pointList.add(p);
        }
    }

    /**
     * Returns the point in this set closest to (x, y) in O(N) time,
     * where N is the number of points in this set.
     */
    @Override
    public Point nearest(double x, double y) {
        double minDist = Double.MAX_VALUE;
        Point minPoint = null;
        double dist;
        for (Point p : this.pointList) {
            dist = p.distanceSquaredTo(x, y);
            if (dist < minDist) {
                minDist = dist;
                minPoint = p;
            }
        }
        return minPoint;
    }
}
