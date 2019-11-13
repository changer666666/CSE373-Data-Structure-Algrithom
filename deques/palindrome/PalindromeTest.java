package deques.palindrome;

import deques.Deque;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PalindromeTest {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("abba"));
        assertTrue(palindrome.isPalindrome("abcdefgfedcba"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("abc"));
        assertFalse(palindrome.isPalindrome("abbc"));
        assertFalse(palindrome.isPalindrome("abcdefggedcba"));
        assertFalse(palindrome.isPalindrome("Aba"));
        assertFalse(palindrome.isPalindrome(".]"));
    }

    @Test
    public void testIsPalindromeOff() {
        assertTrue(palindrome.isPalindrome("a", new OffByOne()));
        assertTrue(palindrome.isPalindrome("ab", new OffByOne()));
        assertTrue(palindrome.isPalindrome("abcb", new OffByOne()));
        assertFalse(palindrome.isPalindrome("abcdefgfedcba", new OffByOne()));
        assertTrue(palindrome.isPalindrome("", new OffByOne()));
        assertTrue(palindrome.isPalindrome("abb", new OffByOne()));
        assertFalse(palindrome.isPalindrome("abc", new OffByOne()));
        assertFalse(palindrome.isPalindrome("abbc", new OffByOne()));
        assertTrue(palindrome.isPalindrome("abcdefggfedcb", new OffByOne()));
        assertFalse(palindrome.isPalindrome("Abcb", new OffByOne()));
        assertFalse(palindrome.isPalindrome("\\Ab]", new OffByOne()));
    }
}
