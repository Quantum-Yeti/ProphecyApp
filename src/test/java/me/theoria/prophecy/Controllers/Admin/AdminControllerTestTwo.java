package me.theoria.prophecy.Controllers.Admin;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javax.accessibility.AccessibleResourceBundle;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AdminControllerTestTwo {
    /**
     * Method under test: {@link AdminController#initialize(URL, ResourceBundle)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInitialize() throws MalformedURLException {

        // Arrange
        AdminController adminController = new AdminController();
        URL url = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

        // Act
        adminController.initialize(url, new AccessibleResourceBundle());
    }

    /**
     * Method under test: {@link AdminController#initialize(URL, ResourceBundle)}
     */
    @Test
    void testInitialize2() throws MalformedURLException {

        // Arrange
        AdminController adminController = new AdminController();
        URL url = Paths.get(System.getProperty("java.io.tmpdir"), "foo").toUri().toURL();

        // Act
        adminController.initialize(url, new AccessibleResourceBundle());
    }
}
