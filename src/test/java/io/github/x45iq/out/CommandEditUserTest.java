package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.models.Car;
import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandEditUserTest {

    @Test
    void edit() {
        UserDatabase userDatabase = new UserDatabase();
        CommandEditUser commandEditUser = new CommandEditUser(Mockito.mock(),userDatabase);
        userDatabase.create("45",new User(Role.CLIENT,123));
        commandEditUser.edit("edit_user 45 --field-name ken");
        assertThat(userDatabase.read("45").getName()).isEqualTo("ken");
    }
}