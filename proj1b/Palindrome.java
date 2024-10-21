public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<>();
        int length = word.length();
        for (int i = 0; i < length; i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    public boolean isPalindrome(String word) {
        if (word == null || word.length() <= 1) {
            return true;
        }
        int length = word.length();
        for (int i = 0; i < length; i++) {
            if (word.charAt(i) != word.charAt(length - i)) {
                return false;
            }

        }
        return true;
    }







    


