package Arabian;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Введите выражение");
        var scanner = new Scanner(System.in);
        String line;
        while (!(line = scanner.nextLine()).equals("exit")) {
            try {
                Calculate.calc(line);
            } catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
}