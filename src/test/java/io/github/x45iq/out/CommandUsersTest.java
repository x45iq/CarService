package io.github.x45iq.out;

import io.github.x45iq.data.UserDatabase;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandUsersTest {

    @Test
    void users() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CommandUsers commandUsers = new CommandUsers(new Output(new PrintStream(byteArrayOutputStream)), new UserDatabase());
        commandUsers.users("users");
        assertThat(byteArrayOutputStream.toString()).isNotEqualTo(null);
    }
}