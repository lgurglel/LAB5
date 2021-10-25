package consoleWork;

import java.util.Scanner;

public class ConsoleWorker implements consoleWork.Scanner {

    private Scanner scanner = new Scanner(System.in);

    public String read() {
        return scanner.nextLine();
    }

    public void write(String str) {
        System.out.println(str);
    }


}
