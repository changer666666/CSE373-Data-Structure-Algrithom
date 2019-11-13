package deques.palindrome;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OffByOneTest {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testOffByOne() {
        assertFalse(offByOne.equalChars('a', 'B'));
        assertTrue(offByOne.equalChars('c', 'b'));
        assertTrue(offByOne.equalChars('X', 'W'));
        assertTrue(offByOne.equalChars('B', 'C'));
        assertTrue(offByOne.equalChars('.', '/'));
        assertFalse(offByOne.equalChars('a', ' '));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars(']', '@'));
        assertFalse(offByOne.equalChars('B', 'D'));
        assertFalse(offByOne.equalChars(' ', '*'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertFalse(offByOne.equalChars('C', '6'));
        assertFalse(offByOne.equalChars('X', 'Z'));
        assertTrue(offByOne.equalChars('0', '1'));
        assertFalse(offByOne.equalChars('.', '8'));
        assertFalse(offByOne.equalChars('Q', 'q'));
        assertFalse(offByOne.equalChars('A', ' '));
        assertFalse(offByOne.equalChars('A', 'a'));
        assertFalse(offByOne.equalChars(']', 'F'));
    }
}
