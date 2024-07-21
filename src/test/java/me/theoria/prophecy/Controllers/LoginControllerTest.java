package me.theoria.prophecy.Controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javax.accessibility.AccessibleResourceBundle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LoginControllerTest {

    @Test
    @Disabled("TODO: Complete this test")
    void testInitialize() throws MalformedURLException {
        // TODO

        // Arrange
        LoginController loginController = new LoginController();
        URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

        // Act
        loginController.initialize(url, new AccessibleResourceBundle());
    }
}
