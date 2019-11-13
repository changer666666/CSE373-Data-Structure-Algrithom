package kdtree;

import java.util.List;

public class KDTreePointSet implements PointSet {
    class Node {
        Point point;
        Node parent;
        Node leftChild;
        Node rightChild;
        int level;

        Node(Point point) {
            this.point = point;
            parent = null;
            leftChild = null;
            rightChild = null;
        }
    }

    Node root = null;

    /**
     * Instantiates a new KDTree with the given points.
     * @param points a non-null, non-empty list of points to include
     *               (makes a defensive copy of points, so changes to the list
     *               after construction don't affect the point set)
     */
    public KDTreePointSet(List<Point> points) {
        Node lastParent = null;
        int lastLevel = 0;
        boolean isLeft = true;
        for (Point p : points) {
            if (this.root == null) {
                this.root = new Node(p);
                this.root.level = 0;
            } else {
                Node temp = this.root;
                while (temp != null) {
                    lastLevel = temp.level;
                    lastParent = temp;
                    if (temp.level % 2 == 0) {
                        //Left or Right
                        if (p.x() < temp.point.x()) {
                            temp = temp.leftChild;
                            isLeft = true;
                        } else {
                            temp = temp.rightChild;
                            isLeft = false;
                        }
                    } else {
                        //Down or Up
                        if (p.y() < temp.point.y()) {
                            temp = temp.leftChild;
                            isLeft = true;
                        } else {
                            temp = temp.rightChild;
                            isLeft = false;
                        }
                    }
                }
                if (isLeft) {
                    lastParent.leftChild = new Node(p);
                    lastParent.leftChild.level = lastLevel + 1;
                    lastParent.leftChild.parent = lastParent;
                } else {
                    lastParent.rightChild = new Node(p);
                    lastParent.rightChild.level = lastLevel + 1;
                    lastParent.rightChild.parent = lastParent;
                }
            }
        }
    }

    /**
     * Returns the point in this set closest to (x, y) in (usually) O(log N) time,
     * where N is the number of points in this set.
     */
    @Override
    public Point nearest(double x, double y) {
        Node bestNode = new Node(new Point(Math.sqrt(Double.MAX_VALUE), Math.sqrt(Double.MAX_VALUE)));
        bestNode = nearestRecurse(this.root, new Point(x, y), bestNode);
        return bestNode.point;
    }

    private Node nearestRecurse(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        double bestDist = best.point.distanceSquaredTo(goal);
        //Update best distance
        if (n.point.distanceSquaredTo(goal) < bestDist) {
            best = n;
            bestDist = n.point.distanceSquaredTo(goal);
        }
        Node goodSide = null;
        Node badSide = null;
        //Decide which side is bood or bad
        if (n.level % 2 == 0) {
            //Left or Right
            if (goal.x() < n.point.x()) {
                goodSide = n.leftChild;
                badSide = n.rightChild;
            } else {
                goodSide = n.rightChild;
                badSide = n.leftChild;
            }
        } else {
            //Down or Up
            if (goal.y() < n.point.y()) {
                goodSide = n.leftChild;
                badSide = n.rightChild;
            } else {
                goodSide = n.rightChild;
                badSide = n.leftChild;
            }
        }
        //Always visit the goodSide
        best = nearestRecurse(goodSide, goal, best);
        //Decide whether we should visit badSide
        if (badSide != null) {
            if (n.level % 2 == 0) {
                //Left or Right
                if (Math.abs(n.point.x() - goal.x()) < Math.sqrt(bestDist)) {
                    best = nearestRecurse(badSide, goal, best);
                }
            } else {
                //Down or Up
                if ((Math.abs(n.point.y() - goal.y())) < Math.sqrt(bestDist)) {
                    best = nearestRecurse(badSide, goal, best);
                }
            }
        }
        return best;
    }
}
