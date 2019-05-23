package com.jedev.leflix;

import com.jedev.leflix.model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class LoginUnitTest {

    @Test
    public void loginUser() {
        User user = new User("joaoeudes7@gmail.com", "hahaha123");

        assertEquals("joaoeudes7@gmail.com", user.getEmail());
        assertEquals("hahaha123", user.getPassword());

    }
}
