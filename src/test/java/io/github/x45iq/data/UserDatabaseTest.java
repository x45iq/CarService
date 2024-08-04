package io.github.x45iq.data;

import io.github.x45iq.models.Order;
import io.github.x45iq.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class UserDatabaseTest {
    private UserDatabase userDatabase = null;
    @BeforeEach
    public void before(){
        userDatabase = new UserDatabase();
    }

    @Test
    void createAndRead() {
        User user = Mockito.mock();
        userDatabase.create("testId",user);
        assertThat(userDatabase.read("testId")).isEqualTo(user);
    }

    @Test
    void updateAndDelete() {
        User user = Mockito.mock();
        User user2 = Mockito.mock();
        userDatabase.create("testId",user);
        assertThat(userDatabase.read("testId")).isEqualTo(user);
        userDatabase.update("testId",user2);
        assertThat(userDatabase.read("testId")).isEqualTo(user2);
        userDatabase.delete("testId");
        assertThat(userDatabase.read("testId")).isNull();
    }

}