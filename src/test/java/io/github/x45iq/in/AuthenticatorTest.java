package io.github.x45iq.in;

import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.out.Output;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatorTest {

    @Test
    void logIN() {
        Authenticator authenticator = new Authenticator(new UserDatabase(),new Input(Mockito.mock(),new ByteArrayInputStream("admin\nadmin".getBytes(StandardCharsets.UTF_8))));
        assertThat(authenticator.auth(Authenticator.AuthType.LOG_IN)).isNotEqualTo(null);
    }
    @Test
    void signIn() {
        Authenticator authenticator = new Authenticator(new UserDatabase(),new Input(Mockito.mock(),new ByteArrayInputStream("1234\n1234\n1".getBytes(StandardCharsets.UTF_8))));
        assertThat(authenticator.auth(Authenticator.AuthType.SIGN_IN)).isNotEqualTo(null);
    }
}