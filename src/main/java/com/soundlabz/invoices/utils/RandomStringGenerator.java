package com.soundlabz.invoices.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class RandomStringGenerator {

    public SecureRandom random = new SecureRandom();

    public String nextValue() {
        return new BigInteger(130, random).toString(32);
    }
}
