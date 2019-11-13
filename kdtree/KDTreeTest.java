package kdtree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void testNearestKDTree() {
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
        NaivePointSet naivePointSet = new NaivePointSet(list);
        assertEquals(kdTreePointSet.nearest(1.0, 0.0), naivePointSet.nearest(1.0, 0.0));
        assertEquals(kdTreePointSet.nearest(0.0, 0.0), naivePointSet.nearest(0.0, 0.0));
        assertEquals(kdTreePointSet.nearest(2.0, 3.0), naivePointSet.nearest(2.0, 3.0));
        assertEquals(kdTreePointSet.nearest(3.0, 4.0), naivePointSet.nearest(3.0, 4.0));
        assertEquals(kdTreePointSet.nearest(4.0, 3.0), naivePointSet.nearest(4.0, 3.0));
        assertEquals(kdTreePointSet.nearest(2.0, 4.0), naivePointSet.nearest(2.0, 4.0));
        assertEquals(kdTreePointSet.nearest(3.0, 1.0), naivePointSet.nearest(3.0, 1.0));
        assertEquals(kdTreePointSet.nearest(1.0, 2.0), naivePointSet.nearest(1.0, 2.0));
        assertEquals(kdTreePointSet.nearest(1.0, 3.0), naivePointSet.nearest(1.0, 3.0));
        assertEquals(kdTreePointSet.nearest(1.0, 4.0), naivePointSet.nearest(1.0, 4.0));
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
        NaivePointSet naivePointSet = new NaivePointSet(list);
        kdTreePointSet.nearest(4.2, 2.4);
        for (double i = 0; i < 7; i = i + 0.6) {
            for (double j = 0; j < 7; j = j + 0.6) {
                assertEquals(kdTreePointSet.nearest(i, j), naivePointSet.nearest(i, j));
                System.out.println(i + " " + j);
                System.out.println("KDTree: " + kdTreePointSet.nearest(i, j));
                System.out.println("Naive: " + naivePointSet.nearest(i, j));
            }
        }

    }

    @Test
    public void testRandomKDTreePointSet() {
        List<Point> list = new ArrayList<>();
        Random random = new Random();
        double x;
        double y;
        for (int i = 0; i < 10000; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
            Point point = new Point(x, y);
            list.add(point);
        }

        KDTreePointSet kdTreePointSet = new KDTreePointSet(list);
        NaivePointSet naivePointSet = new NaivePointSet(list);

        for (int i = 0; i < 100000; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
            try {
                assertEquals(kdTreePointSet.nearest(x, y), naivePointSet.nearest(x, y));
            } catch (AssertionError e) {
                System.out.println(x + " " + y);
                System.out.println(kdTreePointSet.nearest(x, y));
                System.out.println(naivePointSet.nearest(x, y));
            }
        }
    }
}
