package com.biblioteca.dominio.Notificacao;

import com.biblioteca.dominio.Usuario;

public interface INotificacao {
    void enviarNotificacao(String texto, Usuario user);
}
