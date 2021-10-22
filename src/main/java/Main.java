import consoleWork.ConsoleWorker;
import consoleWork.Scanner;
import fileWork.FileManager;
import managers.CollectionManager;
import managers.CommandManager;

import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        final String envVariable = "envVariable";
        ConsoleWorker consoleWorker = new ConsoleWorker();
        FileManager fileManager = new FileManager(envVariable);
        CollectionManager collectionManager = new CollectionManager(consoleWorker, fileManager);
        CommandManager commandManager = new CommandManager(consoleWorker, collectionManager, fileManager);
        Scanner scanner = consoleWorker;
        scanner.write("Введите команду. Для просмотра списка комманд введите 'help'.");
        while (true) {
            try {
                String request = scanner.read();
                commandManager.checkRequest(request, scanner);
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
            scanner.write("\nЗавершено выполнение команды!");
        }
    }
}
