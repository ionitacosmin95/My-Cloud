package Tests;

import FileSystem.FileSystem;
import Interfaces.Command;
import Interfaces.Repository;
import Utils.CommandFactory;
import Utils.ParametersManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ionita Cosmin on 12/23/2015.
 */
public class CommandMkdirTest {

    Command mkdirCommand = CommandFactory.getCommand("mkdir");
    String tempParameters = "";

    private void login() {
        // Login
        Command newuserCommand = CommandFactory.getCommand("newuser");

        ParametersManager.setParameters("root rootpass root root");

        newuserCommand.execute();

        ParametersManager.flushParameters();

        Command loginCommand = CommandFactory.getCommand("login");

        ParametersManager.setParameters("root rootpass");

        loginCommand.execute();

        ParametersManager.flushParameters();
    }

    @Before
    public void setUp() throws Exception {
        login();

        ParametersManager.setParameters("myFolder");
        tempParameters = "myFolder";
    }

    @Test
    public void testExecute() throws Exception {
        mkdirCommand.execute();

        Repository rep = FileSystem.getFileSystem().currentDirectory.getNode(tempParameters);

        assertNotNull(rep);
    }
}