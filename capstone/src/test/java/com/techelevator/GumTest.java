package com.techelevator;

import com.techelevator.Gum;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class GumTest {

    @Test
    public void returnChew() {
        Assert.assertEquals("Chew Chew, Yum!", Gum.getMessage());
    }

}