package model;

import org.junit.Test; // Import do JUnit 4
import static org.junit.Assert.*; // Assert do JUnit 4

public class UsuarioTest {
    
    public UsuarioTest() {
    }

    @Test
    public void testEmailValido() {
        Usuario instance = new Usuario("flavio@teste.com", "123456", "ADM");
        assertTrue("O email deveria ser valido", instance.isEmailValido());
    }

    @Test
    public void testEmailInvalido() {
        Usuario instance = new Usuario("flaviotestecom", "123456", "ADM");
        assertFalse("O email sem @ deveria ser invalido", instance.isEmailValido());
    }

    @Test
    public void testSenhaForte() {
        Usuario instance = new Usuario("flavio@teste.com", "minhasenha123", "ADM");
        assertTrue("A senha deveria ser forte", instance.isSenhaForte());
    }

    @Test
    public void testSenhaFraca() {
        Usuario instance = new Usuario("flavio@teste.com", "123", "ADM");
        assertFalse("A senha deveria ser fraca", instance.isSenhaForte());
    }
}