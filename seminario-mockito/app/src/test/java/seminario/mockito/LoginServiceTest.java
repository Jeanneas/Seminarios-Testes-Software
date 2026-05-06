package seminario.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) // Habilita o suporte às anotações do Mockito no JUnit 5
public class LoginServiceTest {

    @Mock
    private UserRepository userRepository; // Cria automaticamente o objeto dublê

    @InjectMocks
    private LoginService loginService; // Cria a instância e injeta o mock acima

    @Test
    public void deveRetornarSucesso_QuandoCredenciaisForemValidas() {
        // GIVEN: Configura o comportamento do dublê
        when(userRepository.findByCredentials("admin", "123")).thenReturn(true);

        // WHEN: Executa a ação
        String resultado = loginService.logar("admin", "123");

        // THEN: Valida o resultado e se o dublê foi consultado
        assertEquals("Acesso Permitido", resultado);
        verify(userRepository).findByCredentials("admin", "123");
    }

    @Test
    public void deveRetornarErro_QuandoSenhaForIncorreta() {
        // GIVEN: Simula um retorno falso do banco de dados/API
        when(userRepository.findByCredentials("admin", "errada")).thenReturn(false);

        // WHEN
        String resultado = loginService.logar("admin", "errada");

        // THEN
        assertEquals("Usuário ou Senha Inválidos", resultado); //
    }
}