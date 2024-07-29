package me.theoria.prophecy.Controllers.Admin;

import javafx.scene.layout.BorderPane;
import javafx.scene.Node;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ViewFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Model.class)
public class AdminControllerTest {

    @Test
    public void testInitialize() throws Exception {
        // Create mock objects
        Model model = PowerMockito.mock(Model.class);
        Model.ViewFactory viewFactory = PowerMockito.mock(Model.ViewFactory.class);
        URL url = PowerMockito.mock(URL.class);
        ResourceBundle rb = PowerMockito.mock(ResourceBundle.class);
        Node clientsView = PowerMockito.mock(Node.class);
        Node depositView = PowerMockito.mock(Node.class);
        Node createClientView = PowerMockito.mock(Node.class);
        SelectedMenuItem menuItem = PowerMockito.mock(SelectedMenuItem.class);

        // Mock static method calls
        PowerMockito.mockStatic(Model.class);
        PowerMockito.when(Model.getInstance()).thenReturn(model);

        // Stub the regular methods
        PowerMockito.when(model.getViewFactory()).thenReturn(viewFactory);
        PowerMockito.when(viewFactory.getAdminSelectedMenuItem()).thenReturn(menuItem);
        PowerMockito.when(viewFactory.getClientsView()).thenReturn(clientsView);
        PowerMockito.when(viewFactory.getDepositView()).thenReturn(depositView);
        PowerMockito.when(viewFactory.getCreateClientView()).thenReturn(createClientView);

        // Create AdminController and call initialize method
        AdminController adminController = new AdminController();
        adminController.admin_parent = new BorderPane();
        adminController.initialize(url, rb);

        // Assert that the center of the border pane has been set correctly
        assertNotNull(adminController.admin_parent.getCenter());

        // Assert that the correct viewFactory methods are called
        PowerMockito.verifyStatic(Model.class);
        verify(viewFactory).getAdminSelectedMenuItem();
        verify(viewFactory).getClientsView();
        verify(viewFactory).getDepositView();
        verify(viewFactory).getCreateClientView();

        // Change the menu item and assert the center pane changes
        PowerMockito.when(menuItem.get()).thenReturn(SelectedMenuItem.CLIENTS);
        assertEquals(clientsView, adminController.admin_parent.getCenter());

        PowerMockito.when(menuItem.get()).thenReturn(SelectedMenuItem.DEPOSIT);
        assertEquals(depositView, adminController.admin_parent.getCenter());

        PowerMockito.when(menuItem.get()).thenReturn(null);
        assertEquals(createClientView, adminController.admin_parent.getCenter());
    }
}