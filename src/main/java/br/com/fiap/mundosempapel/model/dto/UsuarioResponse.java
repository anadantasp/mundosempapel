package br.com.fiap.mundosempapel.model.dto;

import br.com.fiap.mundosempapel.model.Usuario;

public record UsuarioResponse(Long id, String nome, String email){

    public static UsuarioResponse fromUsuario(Usuario usuario){
        return new UsuarioResponse(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
    
}
