package autocomplete;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TermTest {
    @Test
    public void testSimpleCompareTo() {
        Term a = new Term("autocomplete", 0);
        Term b = new Term("me", 0);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(new Term("a", 0).compareTo(new Term("b", 0)) < 0);
        assertTrue(new Term("b", 0).compareTo(new Term("b", 0)) == 0);
        assertTrue(new Term("aa", 0).compareTo(new Term("b", 0)) < 0);
        assertTrue(new Term("ba", 0).compareTo(new Term("b", 0)) > 0);
        assertTrue(new Term("", 0).compareTo(new Term("b", 0)) < 0);
        assertTrue(new Term("aaa", 0).compareTo(new Term("aaz", 0)) < 0);
        assertTrue(new Term("aaa", 0).compareToByReverseWeightOrder(new Term("aaa", 1)) < 0);
        assertTrue(new Term("aaa", 1).compareToByReverseWeightOrder(new Term("aaa", 0)) > 0);
        assertTrue(new Term("aaa", 0).compareToByReverseWeightOrder(new Term("aaa", 0)) == 0);
        assertTrue(new Term("bba", 0).compareToByPrefixOrder(new Term("bba", 0), 3) == 0);
        assertTrue(new Term("baa", 0).compareToByPrefixOrder(new Term("bba", 0), 4) < 0);
        assertTrue(new Term("bba", 0).compareToByPrefixOrder(new Term("bbab", 0), 3) == 0);
        assertTrue(new Term("aaa", 3).query.equals("aaa"));
        assertTrue(new Term("aaa", 3).weight == 3);
        assertTrue(new Term("aaa", 3).queryPrefix(2).equals("aa"));

    }

    // Write more unit tests below.
}
