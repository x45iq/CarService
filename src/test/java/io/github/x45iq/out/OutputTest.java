package io.github.x45iq.out;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OutputTest {

    @Test
    void println() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(new PrintStream(byteArrayOutputStream));
        output.println("1234");
        assertThat(byteArrayOutputStream.toString()).isEqualTo("1234\n");
    }

    @Test
    void print() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(new PrintStream(byteArrayOutputStream));
        output.print("1234");
        assertThat(byteArrayOutputStream.toString()).isEqualTo("1234");
    }

    @Test
    void printf() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(new PrintStream(byteArrayOutputStream));
        output.printf("%s %s","test","1234");
        assertThat(byteArrayOutputStream.toString()).isEqualTo("test 1234");
    }
}