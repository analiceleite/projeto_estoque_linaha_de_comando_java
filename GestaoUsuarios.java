import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestaoUsuarios {

  Scanner scan = new Scanner(System.in);
  public static ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();

  public void adicionarUsuario(Usuario Usuario, String Senha) {
    int id = 1;
    boolean confirmarSenha = true;
    for (Usuario u : listaDeUsuarios) {
      if (u.idUsuario >= id) {
        id = u.idUsuario + 1;
      }
    }
    Usuario.idUsuario = id;
    do {
      if (!Usuario.getSenha().equals(Senha)) {
        confirmarSenha = false;
        System.out.println(Cor.RED + "\nAs senhas não coincidem, tente novamente!\n");
        Usuario.setSenha(
            System.console().readLine(Cor.YELLOW +"Informe a senha novamente: "));
        Senha = System.console().readLine("Confirme a senha novamente: ");
      } else {
        listaDeUsuarios.add(Usuario);
        System.out.println(Cor.YELLOW + "\nUsuário cadastrado com sucesso!");
        break;
      }
    } while (!confirmarSenha);
  }

  public static void cadastrarUsuarioAdmin() {
    Usuario u = new Usuario();
    u.idUsuario = 1;
    u.setNome("Administrator");
    u.setLogin("admin");
    u.setSenha("admin");
    listaDeUsuarios.add(u);
  }

  public Usuario buscarLogin(String login, String senha) {
    for (Usuario u : listaDeUsuarios) {
      if ((u.getLogin().equals(login)) && (u.getSenha().equals(senha))) {
        return u;
      }
    }
    return null;
  }

  // Mostrar usuários cadastrados
  public static void mostrarUsuariosCadastrados() {
    System.out.println(
      "----------------------------------------------------\n" +
    "\n   As informações dos usuários cadastrados são: \n " +
    "\n----------------------------------------------------" );
    for (Usuario u : listaDeUsuarios) {
      System.out.println("\n ID do cadastro: " +
          u.idUsuario +
          "\n Login: " +
          u.getLogin() +
          "\n Senha: " +
          u.getSenha() +
          "\n");
    }
    System.out.println("Pressione ENTER para voltar... \n");
    try {
      System.in.read(new byte[2]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Editar senha usuario
  public String alterarSenhaUsuario(int id) {
    boolean encontrado = false;

    for (Usuario u : listaDeUsuarios) {
      encontrado = false;

      if (u.idUsuario == id) {
        encontrado = true;
        System.out.println("Digite a nova senha do usuário: ");
        String novaSenha = scan.next();
        u.setSenha(novaSenha);

        if (encontrado == true) {
          System.out.println("\nSenha alterada com sucesso!");
        } else {
          System.out.println("Não foi possível realizar a alteração da senha. Tente novamente.");
        }
      }
    }
    return "";
  }

  // Excluir cadastro de usuário
  public static String deletarCadastroUsuario(int id) {
    for (int i = 0; i < listaDeUsuarios.size(); i++) {
      Usuario u = listaDeUsuarios.get(i);
      if (u.getIdUsuario() == id) {
        listaDeUsuarios.remove(i);
        return "Cadastro removido com sucesso!";
      }
    }
    return "ID não encontrado. Nenhum cadastro removido.";
  }
}
