package dominio.Notificacao;

import dominio.Usuario;

public interface INotificacao {
    void enviarNotificacao(String texto, Usuario user);
}
