package com.example.natallialemiasheuskaya.userretrofit;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void test() {
        assertEquals(4, summa(2,2));
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    public int summa(int a,int b){
        return a+b;
    }
}