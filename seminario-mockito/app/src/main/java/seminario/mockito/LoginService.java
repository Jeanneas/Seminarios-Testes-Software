package seminario.mockito;

/**
 * Classe de regra de negócio que depende do repositório.
 */
public class LoginService {
    private UserRepository repository;

    // Injeção de dependência via construtor: facilita o trabalho do Mockito
    public LoginService(UserRepository repository) {
        this.repository = repository;
    }

    public String logar(String user, String password) {
        if (repository.findByCredentials(user, password)) {
            return "Acesso Permitido";
        }
        return "Usuário ou Senha Inválidos";
    } //
}