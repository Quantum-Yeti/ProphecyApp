package me.theoria.prophecy.Models;

import me.theoria.prophecy.Views.ViewFactory;
import org.junit.jupiter.api.Test;

class AllModelsTests {
    /**
     * Method under test: {@link Model#getViewFactory()}
     */
    @Test
    void testGetViewFactory() {
        Model model = Model.getInstance();
        ViewFactory actualViewFactory = model.getViewFactory();
    }

    /**
     * Method under test: {@link Model#getClientLoginSuccessCheck()}
     */
    @Test
    void testGetClientLoginSuccessCheck() {
        Model model = Model.getInstance();
        boolean actualClientLoginSuccessCheck = model.getClientLoginSuccessCheck();
    }

    /**
     * Method under test: {@link Model#evalClientCred(String, String)}
     */
    @Test
    void testEvalClientCred() {
        Model.getInstance().evalClientCred("@hUmphrey2", "dinosaur");
        Model.getInstance().evaluateAdminCred("@Admin", "123456");
        Model.getInstance().evalClientCred("bMsupply1", "123456");
    }

    /**
     * Method under test: {@link Model#setClients()}
     */
    @Test
    void testSetClients() {
        Model.getInstance().setClients();
    }

    /**
     * Method under test: {@link Model#setLatestTransactions()}
     */
    @Test
    void testSetLatestTransactions() {
        Model.getInstance().setLatestTransactions();
    }
}
