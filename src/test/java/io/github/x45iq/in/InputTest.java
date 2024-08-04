package io.github.x45iq.in;

import io.github.x45iq.models.Command;
import io.github.x45iq.models.Pair;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InputTest {

    @Test
    void readString() {
        var input = new Input(Mockito.mock(),new ByteArrayInputStream("12345".getBytes(StandardCharsets.UTF_8)));
        assertThat(input.readString("")).isEqualTo("12345");
    }

    @Test
    void testReadString() {
        var input = new Input(Mockito.mock(),new ByteArrayInputStream("12345\n12345\n111".getBytes(StandardCharsets.UTF_8)));
        assertThat(input.readString("",s->!s.equals("12345"))).isEqualTo("111");
    }

    @Test
    void readCommand() {
        var input = new Input(Mockito.mock(),new ByteArrayInputStream("exit".getBytes(StandardCharsets.UTF_8)));
        assertThat(input.readCommand("")).isEqualTo(new Pair<>(Command.EXIT,"exit"));
        input = new Input(Mockito.mock(),new ByteArrayInputStream("add_car".getBytes(StandardCharsets.UTF_8)));
        assertThat(input.readCommand("")).isEqualTo(new Pair<>(Command.ADD_CAR,"add_car"));
    }
}