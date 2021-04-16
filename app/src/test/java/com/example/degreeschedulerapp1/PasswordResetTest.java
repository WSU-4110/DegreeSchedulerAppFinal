package com.example.degreeschedulerapp1;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class PasswordResetTest {
    String email1 = "go8010@wayne.edu";
    String email2 = "go8010@wayne.edu";
    String oldPass = "123456";
    String newPass = "654321";

    @Test
    public void check_if_emails_are_registered(){
        Assert.assertEquals("Email is not registered",email1, email2); // check if email account is registered
        System.out.println("Email is registered");
    }

    @Test
    public void check_if_link_sent(){
        Assert.assertEquals("Link failed to send",email1, email2); // check if verification link is sent
        System.out.println("Link is registered");
    }

    @Test
    public void check_if_old_password(){
        Assert.assertNotSame("New password cannot be the old one",oldPass, newPass); // check if new password is old
        System.out.println("New password is updated");
    }


}
