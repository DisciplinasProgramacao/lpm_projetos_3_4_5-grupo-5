package codigo.app;

public class Administrador {
    protected String nomeDeUsuario;
    protected String login;
    protected String senha;

    /**
     * Cria um novo administrador
     *
     * @param nomeDeUsuario Nome de usuario pode ter ate 30 caracteres
     * @param login         Login pode ter até 20 caracteres
     * @param senha         Senha pode ter ate 10 caracteres
     */

    public Administrador(String nomeDeUsuario, String login, String senha) {
        try {
            if (!(login.isEmpty() || login.length() > 20)) this.login = login;
            else throw new NullPointerException("Não foi possível criar o administrador!");
            if (!(nomeDeUsuario.isEmpty() || nomeDeUsuario.length() > 30)) this.nomeDeUsuario = nomeDeUsuario;
            else throw new NullPointerException("Não foi possível criar o administrador!");
            if (!(senha.isEmpty() || senha.length() > 10)) this.senha = senha;
            else throw new NullPointerException("Não foi possível criar o administrador!");

        } catch (NullPointerException e) {
            System.out.println(e.toString());
        }
    }

    public String getUsuario() {
        return this.nomeDeUsuario;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getLogin() {
        return login;
    }
}
