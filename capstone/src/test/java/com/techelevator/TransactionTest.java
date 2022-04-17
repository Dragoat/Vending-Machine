package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class TransactionTest {

    @Before
    public void setup() throws FileNotFoundException {
        MachineItems.readFile();
        Transaction.balance.multiply(new BigDecimal(0.00));
    }

    @Test
    public void feedCashTest() throws Exception {
        Transaction.feedCash("$1");
        Assert.assertTrue(new BigDecimal(1.00).compareTo(Transaction.getBalance()) == 0);
        Transaction.feedCash("$2");
        Assert.assertTrue(new BigDecimal(3.00).compareTo(Transaction.getBalance()) == 0);
        Transaction.feedCash("$5");
        Assert.assertTrue(new BigDecimal(8.00).compareTo(Transaction.getBalance()) == 0);
        Transaction.feedCash("$10");
        Assert.assertTrue(new BigDecimal(18.00).compareTo(Transaction.getBalance()) == 0);
    }

    @Test
    public void makeChangeTest() throws FileNotFoundException {
        Transaction.feedCash("$10");
        Transaction.makeChange(Transaction.getBalance());
        Assert.assertTrue(new BigDecimal(0.00).compareTo(Transaction.getBalance()) == 0);
    }

    @Test
    public void buySnacksTest() throws Exception {
        Transaction.feedCash("$1");
        Transaction.feedCash("$2");
        Transaction.buySnacks("C2");
        Transaction.buySnacks("C4");
        Assert.assertTrue(new BigDecimal(0.00).compareTo(Transaction.getBalance()) == 0);
    }

    @Test
    public void stockTest() throws Exception{
        Transaction.feedCash("$5");
        Transaction.buySnacks("D4");
        Transaction.buySnacks("D4");
        Transaction.buySnacks("D4");
        Transaction.buySnacks("D4");
        Transaction.buySnacks("D4");
        Assert.assertTrue(MachineItems.itemStock.get("D4") == 0);
    }
}
