package com.biblioteca.dominio.Notificacao;

import com.biblioteca.dominio.Usuario;

public class NotificacaoSms implements INotificacao{
    @Override
    public void enviarNotificacao(String texto, Usuario user) {
        System.out.println(texto + "\n Enviado por SMS para " + user.getNome());
    }
}
