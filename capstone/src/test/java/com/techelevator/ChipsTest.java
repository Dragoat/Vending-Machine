package com.techelevator;

import com.techelevator.Chips;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ChipsTest {

    @Test
    public void returnCrunch() {
        Assert.assertEquals("Crunch Crunch, Yum!", Chips.getMessage());
    }

}