package com.github.glomadrian.grav;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        Random random = new Random();
        int result = (random.nextInt() * 20) % 20;
        int finalization = (10 - result) % 10;

        Assert.assertEquals(10, result);
    }
}