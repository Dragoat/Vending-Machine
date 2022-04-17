package com.techelevator;

import com.techelevator.Candy;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CandyTest {

    @Test
    public void returnMunch() {
        Assert.assertEquals("Munch Munch, Yum!", Candy.getMessage());
    }

}