package managers;
import consoleWork.Scanner;
import fileWork.FileManager;

public interface Executer {

  Response executeExecuteScriptCommand(FileManager fileManager, String parameter, Scanner scanner);
  Response addCommand(Scanner scanner);
  Response clearCommand(Scanner scanner);
  Response exitCommand(Scanner scanner);
  Response helpCommand(Scanner scanner);
  Response infoCommand(Scanner scanner);
  Response removeByIdCommand(Scanner scanner, String parameter);
  Response saveCommand(Scanner scanner);
  Response showCommand(Scanner scanner);
  Response updateCommand(Scanner scanner, String parameter);
  Response sumOfDiscountCommand(Scanner scanner);
  Response removeGreaterCommand(Scanner scanner);
  Response filterByPriceCommand(String parameter);
  Response removeAllByPriceCommand(String parameter);
  Response reorder();
  Response sort();
}
