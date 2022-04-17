package com.techelevator;

import com.techelevator.Beverage;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BeverageTest {

    @Test
    public void returnGlug() {
        Assert.assertEquals("Glug Glug, Yum!", Beverage.getMessage());
    }
}