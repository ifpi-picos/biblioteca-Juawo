package com.biblioteca.dominio.Notificacao;

import com.biblioteca.dominio.Usuario;

public class NotificaoEmail implements INotificacao{
    @Override
    public void enviarNotificacao(String texto, Usuario user){
        System.out.println(texto + "\nVia e-mail para " + user.getEmail());
    }
}
