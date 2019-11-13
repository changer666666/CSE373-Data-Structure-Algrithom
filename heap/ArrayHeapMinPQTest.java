package heap;

import org.junit.Test;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class ArrayHeapMinPQTest {
    /* Be sure to write randomized tests that can handle millions of items. To
     * test for runtime, compare the runtime of NaiveMinPQ vs ArrayHeapMinPQ on
     * a large input of millions of items. */

    @Test
    public void testStatic() {
        ArrayHeapMinPQ<Integer> arr1 = new ArrayHeapMinPQ<>();
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            double priority = r.nextDouble();
            arr1.add(i, priority);
        }
        ArrayHeapMinPQ<Integer> arr2 = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 10; i++) {
            double priority = r.nextDouble();
            arr2.add(i, priority);
        }
        for (int i = 0; i < 20; i++) {
            arr1.removeSmallest();
        }
    }

    @Test
    public void testRandom() {
        Random r = new Random();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 20; i++) {
            double priority = r.nextDouble();
            arrayHeapMinPQ.add(i, priority);
        }
    }

    @Test
    public void testAddAndContains() {
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        arrayHeapMinPQ.add("a", 4);
        assertTrue(arrayHeapMinPQ.contains("a"));
        assertFalse(arrayHeapMinPQ.contains("b"));
        assertEquals(arrayHeapMinPQ.getSmallest(), "a");
        arrayHeapMinPQ.add("b", 3);
        assertTrue(arrayHeapMinPQ.contains("a"));
        assertTrue(arrayHeapMinPQ.contains("b"));
        assertEquals(arrayHeapMinPQ.getSmallest(), "b");
        arrayHeapMinPQ.add("c", 2);
        assertTrue(arrayHeapMinPQ.contains("b"));
        assertTrue(arrayHeapMinPQ.contains("c"));
        arrayHeapMinPQ.add("d", 1);
        assertTrue(arrayHeapMinPQ.contains("d"));
        assertFalse(arrayHeapMinPQ.contains("e"));
    }

    @Test
    public void testRemoveAndGet() {
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        arrayHeapMinPQ.add("a", 4);
        assertTrue(arrayHeapMinPQ.contains("a"));
        assertEquals(arrayHeapMinPQ.removeSmallest(), "a");
        assertFalse(arrayHeapMinPQ.contains("a"));
        arrayHeapMinPQ.add("b", 3);
        assertEquals(arrayHeapMinPQ.getSmallest(), "b");
        arrayHeapMinPQ.add("c", 2);
        assertEquals(arrayHeapMinPQ.getSmallest(), "c");
        arrayHeapMinPQ.add("d", 1);
        assertEquals(arrayHeapMinPQ.getSmallest(), "d");
        assertEquals(arrayHeapMinPQ.removeSmallest(), "d");
        assertFalse(arrayHeapMinPQ.contains("d"));
        assertEquals(arrayHeapMinPQ.getSmallest(), "c");
        assertTrue(arrayHeapMinPQ.contains("c"));
        assertEquals(arrayHeapMinPQ.removeSmallest(), "c");
        assertFalse(arrayHeapMinPQ.contains("c"));
        assertEquals(arrayHeapMinPQ.getSmallest(), "b");
    }

    @Test
    public void testArrayHeapMinPQ() {
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        arrayHeapMinPQ.add("1", 1);
        arrayHeapMinPQ.add("12", 12);
        System.out.println(arrayHeapMinPQ.size());
        System.out.println(arrayHeapMinPQ.contains("2"));
        System.out.println(arrayHeapMinPQ.contains("20"));
        arrayHeapMinPQ.add("20", 20);
        System.out.println(arrayHeapMinPQ.contains("20"));
        arrayHeapMinPQ.add("8", 8);
        System.out.println(arrayHeapMinPQ.contains("8"));
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.changePriority("12", 0.5);
        System.out.println(arrayHeapMinPQ.getSmallest());


    }

    @Test
    public void testTime() {

        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        Random random = new Random();
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < 10; i++) {
            double priority = random.nextDouble();
            Integer item = random.nextInt(20);
            arrayHeapMinPQ.add(i, priority);
        }
        for (int i = 0; i < 10; i++) {
            arrayHeapMinPQ.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() + " seconds.");
        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < 1000000; i++) {
            double priority = random.nextDouble();
            Integer item = random.nextInt(20);
            naiveMinPQ.add(i, priority);
        }
        for (int i = 0; i < 10000; i++) {
            naiveMinPQ.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw1.elapsedTime() + " seconds.");
    }
    @Test
    public void testArrayAndNaive() {
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            Integer priority = random.nextInt();
            Integer item = random.nextInt(2000);
            naiveMinPQ.add(i, i);
            arrayHeapMinPQ.add(i, i);
        }
        for (int i = 0; i < 50; i++) {
            //System.out.println(arrayHeapMinPQ.getSmallest());
            assertEquals(naiveMinPQ.contains(i), arrayHeapMinPQ.contains(i));
        }
        for (int i = 0; i < 50; i++) {
            System.out.println(arrayHeapMinPQ.getSmallest());
            assertEquals(naiveMinPQ.removeSmallest(), arrayHeapMinPQ.removeSmallest());
        }
        for (int i = 100; i > 50; i--) {
            double priority = random.nextDouble();
            Integer item = random.nextInt(20);
            naiveMinPQ.add(i, i);
            arrayHeapMinPQ.add(i, i);
        }
        for (int i = 100; i > 50; i--) {
            assertEquals(naiveMinPQ.removeSmallest(), arrayHeapMinPQ.removeSmallest());
        }
        for (int i = 100; i < 200; i++) {
            double priority = random.nextDouble();
            Integer item = random.nextInt(20);
            naiveMinPQ.add(i, priority);
            arrayHeapMinPQ.add(i, priority);
        }
        for (int i = 100; i < 200; i++) {
            System.out.println(arrayHeapMinPQ.getSmallest());
            assertEquals(naiveMinPQ.contains(i), arrayHeapMinPQ.contains(i));
        }
        for (int i = 100; i < 200; i++) {
            double priority = random.nextDouble();
            Integer item = random.nextInt(20);
            assertEquals(naiveMinPQ.removeSmallest(), arrayHeapMinPQ.removeSmallest());
        }
        arrayHeapMinPQ.add(2, 2);
        arrayHeapMinPQ.add(1, 1);
        arrayHeapMinPQ.add(9, 9);
        arrayHeapMinPQ.add(100, 0.5);
        arrayHeapMinPQ.add(101, 20);
        arrayHeapMinPQ.changePriority(100, 21);
        arrayHeapMinPQ.changePriority(101, 0.3);
        System.out.println(arrayHeapMinPQ.removeSmallest());
        System.out.println(arrayHeapMinPQ.removeSmallest());
        System.out.println(arrayHeapMinPQ.removeSmallest());
        System.out.println(arrayHeapMinPQ.removeSmallest());
        System.out.println(arrayHeapMinPQ.removeSmallest());


        naiveMinPQ.add(2, 2);
        naiveMinPQ.add(1, 1);
        naiveMinPQ.add(9, 9);
        naiveMinPQ.add(100, 0.5);
        naiveMinPQ.add(101, 20);
        naiveMinPQ.changePriority(100, 21);
        naiveMinPQ.changePriority(101, 0.3);
        System.out.println(naiveMinPQ.removeSmallest());
        System.out.println(naiveMinPQ.removeSmallest());
        System.out.println(naiveMinPQ.removeSmallest());
        System.out.println(naiveMinPQ.removeSmallest());
        System.out.println(naiveMinPQ.removeSmallest());
    }

    @Test
    public void testNull() {
        NaiveMinPQ<String> naiveMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();

        String s = null;
        Double d = null;
        arrayHeapMinPQ.add("s", 1);
        arrayHeapMinPQ.add("null", 1);
        arrayHeapMinPQ.add("2", 2);
        System.out.println(arrayHeapMinPQ.removeSmallest());
        System.out.println(arrayHeapMinPQ.removeSmallest());
        System.out.println(arrayHeapMinPQ.removeSmallest());

    }

    @Test
    public void testChangePriority() {
        NaiveMinPQ<String> naiveMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<String> arrayHeapMinPQ = new ArrayHeapMinPQ<>();

        arrayHeapMinPQ.add("3", 3);
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.add("2", 2);
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.add("4", 4);
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.add("5", 5);
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.changePriority("3", 1);
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.changePriority("2", 6);
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.changePriority("3", 1);
        System.out.println(arrayHeapMinPQ.getSmallest());
        arrayHeapMinPQ.changePriority("3", 7);
        System.out.println(arrayHeapMinPQ.getSmallest());
    }

    @Test
    public void testChangePriorityRandom() {
        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();

        for (int i = 0; i < 50; i++) {
            naiveMinPQ.add(i, i);
            arrayHeapMinPQ.add(i, i);
        }
        for (int i = 0; i < 50; i++) {
            naiveMinPQ.changePriority(i, 100 - i);
            arrayHeapMinPQ.changePriority(i, 100 - i);
            System.out.print(arrayHeapMinPQ.getSmallest());
            assertEquals(naiveMinPQ.getSmallest(), arrayHeapMinPQ.getSmallest());
        }
    }
}
