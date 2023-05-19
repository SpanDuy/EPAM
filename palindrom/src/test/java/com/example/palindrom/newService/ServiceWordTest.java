package com.example.palindrom.newService;

import com.example.palindrom.counters.CounterService;
import com.example.palindrom.word.Palindromic;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceWordTest {
    private ServiceWord service = new ServiceWord(new CounterService());

    @Test
    void testIsPalindromicWithPalindrome() {
        String palindrome = "racecar";
        assertEquals(true, service.isPalindromic(palindrome));
    }
    @Test
    void testIsPalindromicWithNonPalindrome() {
        String nonPalindrome = "hello";
        assertEquals(false, service.isPalindromic(nonPalindrome));
    }
    @Test
    void testIsPalindromicWithPalindromeList() {
        String palindrome = "racecar";
        List<String> stringList = new LinkedList<>();
        stringList.add(palindrome);

        List<Boolean> booleanList = new LinkedList<>();
        booleanList.add(true);

        assertEquals(booleanList, service.isPalindromicList2(stringList));
    }
    @Test
    void testIsPalindromicWithNonPalindromeList() {
        String palindrome = "hello";
        List<String> stringList = new LinkedList<>();
        stringList.add(palindrome);

        List<Boolean> booleanList = new LinkedList<>();
        booleanList.add(false);

        assertEquals(booleanList, service.isPalindromicList2(stringList));
    }
    @Test
    void testCalculateAverOfResult() {
        Palindromic palindromic = new Palindromic();
        palindromic.setWord("qwewq");
        palindromic.setIsPalindromic(true);
        List<Palindromic> palindromicList = new LinkedList<>();
        palindromicList.add(palindromic);

        assertEquals(5, service.calculateAverOfResult(palindromicList));
    }
    @Test
    void testfindMinOfResult() {
        Palindromic palindromic = new Palindromic();
        palindromic.setWord("qwewq");
        palindromic.setIsPalindromic(true);
        List<Palindromic> palindromicList = new LinkedList<>();
        palindromicList.add(palindromic);

        assertEquals(5, service.findMinOfResult(palindromicList));
    }
    @Test
    void testfindMaxOfResult() {
        Palindromic palindromic = new Palindromic();
        palindromic.setWord("qwewq");
        palindromic.setIsPalindromic(true);
        List<Palindromic> palindromicList = new LinkedList<>();
        palindromicList.add(palindromic);

        assertEquals(5, service.findMaxOfResult(palindromicList));
    }
}