package managers;

import commands.*;
import consoleWork.ConsoleWorker;
import consoleWork.Scanner;
import consoleWork.ScriptScanner;
import fileWork.FileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private ConsoleWorker consoleWorker;
    private CollectionManager collectionManager;
    private FileManager fileManager;
    private List<String> scriptsFiles = new ArrayList();

    public CommandManager(ConsoleWorker consoleWorker, CollectionManager collectionManager, FileManager fileManager) {
        this.consoleWorker = consoleWorker;
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    public void checkRequest(String request, Scanner scanner) {
        if (request == null && request.equals("")) {
            scanner.write("Строка не должна быть пустой!");
            return;
        }
        request = request.toLowerCase().trim();

        String commandName = request.split(" ")[0];

        // волидация проверка
        Command command = null;
        if (commandName.equals(new AddCommand().toString())) {
            command = new AddCommand(collectionManager);
        } else if (commandName.equals(new ClearCommand().toString())) {
            command = new ClearCommand(collectionManager);
        } else if (commandName.equals(new ExecuteScriptCommand().toString())) {
            command = new ExecuteScriptCommand(collectionManager, getParameter(request, scanner), fileManager);
        } else if (commandName.equals(new ExitCommand().toString())) {
            command = new ExitCommand(collectionManager);
        } else if (commandName.equals(new HelpCommand().toString())) {
            command = new HelpCommand(collectionManager);
        } else if (commandName.equals(new InfoCommand().toString())) {
            command = new InfoCommand(collectionManager);
        } else if (commandName.equals(new RemoveByIdCommand().toString())) {
            command = new RemoveByIdCommand(collectionManager, getParameter(request, scanner));
        } else if (commandName.equals(new SaveCommand().toString())) {
            command = new SaveCommand(collectionManager);
        } else if (commandName.equals(new ShowCommand().toString())) {
            command = new ShowCommand(collectionManager);
        } else if (commandName.equals(new UpdateCommand().toString())) {
            command = new UpdateCommand(collectionManager, getParameter(request, scanner));
        } else if (commandName.equals(new SumOfDiscountCommand().toString())) {
            command = new SumOfDiscountCommand(collectionManager);
        } else if (commandName.equals(new RemoveGreaterCommand().toString())) {
            command = new RemoveGreaterCommand(collectionManager);
        } else if (commandName.equals(new FilterByPriceCommand().toString())) {
            command = new FilterByPriceCommand(collectionManager, getParameter(request, scanner));
        } else if (commandName.equals(new RemoveAllByPriceCommand().toString())) {
            command = new RemoveAllByPriceCommand(collectionManager, getParameter(request, scanner));
        } else if (commandName.equals(new ReorderCommand().toString())) {
            command = new ReorderCommand(collectionManager);
        } else if (commandName.equals(new SortCommand().toString())) {
            command = new SortCommand(collectionManager);
        }
        if (command == null) {
            scanner.write("Неизвестная команда! Введите help, чтоб увидеть список команд.");
            return;
        } else {
            Response response = command.execute(scanner);
            if (command.toString().equals(new ExecuteScriptCommand().toString())) {
                if (response.getMessage().equals("false")) {
                    scanner.write("Проблема с запуском скрипта");
                } else {
                    String filePath = new File(getParameter(request, scanner)).getAbsolutePath();
                    if (scriptsFiles.contains(filePath)) {
                        scanner.write("\nРекурсия!");
                        scriptsFiles.clear();
                        return;
                    } else {
                        scriptsFiles.add(filePath);
                        executeScript(response.getMessage());
                        scriptsFiles.remove(filePath);
                    }
                }
            } else {
                scanner.write(response.getMessage());
            }
        }
    }

    private void executeScript(String script) {
        Scanner scanner = new ScriptScanner(script);
        while (!scriptsFiles.isEmpty()) {
            String command = scanner.read();
            if (command == null) {
                break;
            }
            checkRequest(command, scanner);
        }
    }


    public String getParameter(String request, Scanner scanner) {
        try {
            return request.split(" ")[1];
        } catch (Exception e) {
            return "Неверно указан параметр";
        }
    }

}
