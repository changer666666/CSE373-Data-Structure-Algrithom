package kdtree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class KDTreePointSetTest {
    @Test
    public void testNaivePointSet() {
        List<Point> list = new ArrayList<>();
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(2.0, 2.0);
        Point p3 = new Point(4.0, 4.0);
        Point p4 = new Point(3.0, 5.0);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        NaivePointSet naivePointSet = new NaivePointSet(list);
        Point minPoint = naivePointSet.nearest(3, 4.6);
        System.out.println(minPoint.toString());
    }

    @Test
    public void testConstructionofKDTreePointSet() {
        List<Point> list = new ArrayList<>();
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(2.0, 2.0);
        Point p3 = new Point(4.0, 4.0);
        Point p4 = new Point(3.0, 1.0);
        Point p5 = new Point(0.0, 1.0);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        KDTreePointSet kdTreePointSet = new KDTreePointSet(list);
    }

    @Test
    public void testComplexConstructionofKDTreePointSet() {
        List<Point> list = new ArrayList<>();
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(2.0, 2.0);
        Point p3 = new Point(4.0, 4.0);
        Point p4 = new Point(3.0, 1.0);
        Point p5 = new Point(0.0, 1.0);
        Point p6 = new Point(5.0, 0.0);
        Point p7 = new Point(5.0, 1.0);
        Point p8 = new Point(5.0, 2.0);
        Point p9 = new Point(2.0, 4.0);
        Point p10 = new Point(2.0, 1.0);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p7);
        list.add(p8);
        list.add(p9);
        list.add(p10);
        KDTreePointSet kdTreePointSet = new KDTreePointSet(list);
    }
}
