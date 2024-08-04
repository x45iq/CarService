package io.github.x45iq.in;

import io.github.x45iq.models.Command;
import io.github.x45iq.models.Pair;
import io.github.x45iq.out.Output;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Function;

public class Input {
    private final Output output;
    private final Scanner scanner;
    public Input(Output output, InputStream inputStream){
        this.output = output;
        scanner = new Scanner(inputStream);
    }

    public String readString(String message, Function<String,Boolean> validation){
        output.print(message);
        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            if(validation.apply(str)){
                return str;
            }
            output.println("Попробуйте ещё раз");
            output.print(message);
        }
        output.println("Прервано");
        System.exit(0);
        throw new RuntimeException("Not reached");
    }
    public String readString(String message){
        output.print(message);
        if(scanner.hasNextLine()){
            return scanner.nextLine();
        }
        output.println("Прервано");
        System.exit(0);
        throw new RuntimeException("Not reached");
    }
    public Pair<Command,String> readCommand(String message){
        output.print(message);
        while (scanner.hasNextLine()){
            String str = scanner.nextLine();
            for(Command command : Command.values()) {
                if (command.getPattern().matcher(str).matches()) {
                    return new Pair<>(command,str);
                }
            }
            output.println("Попробуйте ещё раз");
            output.print(message);
        }
        output.println("Прервано");
        System.exit(0);
        throw new RuntimeException("Not reached");
    }
}
