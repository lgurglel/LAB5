package commands;

import consoleWork.Scanner;
import fileWork.FileManager;
import managers.CollectionManager;
import managers.Command;
import managers.Response;


public class ExecuteScriptCommand extends Command {
    private CollectionManager collectionManager;
    private String parameter;
    private FileManager fileManager;

    public ExecuteScriptCommand(CollectionManager collectionManager, String parameter,FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager =fileManager;
        this.parameter =parameter;
    }

    public ExecuteScriptCommand() {

    }

    @Override
    public String toString() {
        return "execute_script";
    }

    @Override
    public Response execute(Scanner scanner) {
            return collectionManager.executeExecuteScriptCommand(fileManager, parameter, scanner);
    }
}
