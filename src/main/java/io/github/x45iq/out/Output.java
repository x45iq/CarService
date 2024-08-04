package io.github.x45iq.out;

import java.io.PrintStream;

public class Output {
    private final PrintStream printStream;

    public Output(PrintStream printStream) {
        this.printStream = printStream;
    }
    public void println(Object x){
        printStream.println(x);
    }
    public void print(Object x){
        printStream.print(x);
    }
    public void printf(String format,Object ...vals){
        printStream.printf(format,vals);
    }
}
