package ru.job4j.algo.stack;

import java.util.Map;
import java.util.Stack;

public class Brackets {
    private final Map<Character, Character> bracketsComplianceMap = Map.of(']', '[', '}', '{', ')', '(');

    public boolean isValid(String s) {
        Stack<Character> brackets = new Stack<>();
        Stack<Character> temp = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(', '{', '[':
                    brackets.push(s.charAt(i));
                    break;
                case ')', '}', ']':
                    if (brackets.isEmpty()) {
                        return false;
                    }
                    Character close = s.charAt(i);
                    if (bracketsComplianceMap.get(close).equals(brackets.peek())) {
                        brackets.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        if (!brackets.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Brackets brackets = new Brackets();
        System.out.println(brackets.isValid("(}"));
    }
}
