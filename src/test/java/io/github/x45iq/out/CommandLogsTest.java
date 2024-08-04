package io.github.x45iq.out;

import io.github.x45iq.data.OrdersDatabase;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandLogsTest {

    @Test
    void logs() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CommandLogs commandLogs = new CommandLogs(new Output(new PrintStream(byteArrayOutputStream)));
        commandLogs.logs("logs");
        assertThat(byteArrayOutputStream.toString()).isNotEqualTo(null);
    }
}