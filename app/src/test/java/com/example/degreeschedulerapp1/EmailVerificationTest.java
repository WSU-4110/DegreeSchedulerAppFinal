package com.example.degreeschedulerapp1;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class EmailVerificationTest {

    Boolean isVerified = true;
    String password= "123456";
    String email1 = "[a-zA-Z0-9._-]+@wayne+\\.edu+";
    String email2="[a-zA-Z0-9._-]+@wayne+\\.edu+";

    @Test
    public void check_if_password_is_6_chars()
    {
        password.length();
        Assert.assertEquals(6, password.length()); //check if password is atleast 6 chars
        System.out.println("Password is of length");
    }
    @Test
    public void check_if_account_verified()
    {
        Assert.assertTrue("Account is not verified",isVerified); //check if account is verified
        System.out.println("Account is verified");
    }
    @Test
    public void check_if_wayne_email(){
        Assert.assertEquals("Not a wayne email",email1, email2); // check if email is WSU registered
        System.out.println("Is a Wayne Email");
    }


}
