package deques.palindrome;

import deques.Deque;
import deques.LinkedDeque;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedDeque();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        while ((deque.size() != 0) && (deque.size() != 1)) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        while ((deque.size() != 0) && (deque.size() != 1)) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
        //throw new UnsupportedOperationException("Not implemented yet.");
    }
}
