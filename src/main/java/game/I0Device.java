package game;

import java.util.Scanner;

public class I0Device {

    private final Scanner input;

    public I0Device(Scanner input) {
        this.input = input;
    }

    public int askForInt(String question) {
        output(question);
        return input.nextInt();
    }

    public void output(String string) {
        System.out.print(string);
    }

    public void outputLine(String string) {
        System.out.println(string);
    }

    public void outputLine() {
        System.out.println();
    }
}
