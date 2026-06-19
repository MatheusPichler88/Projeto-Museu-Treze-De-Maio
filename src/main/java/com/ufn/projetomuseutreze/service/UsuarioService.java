package com.ufn.projetomuseutreze.service;

import com.ufn.projetomuseutreze.model.Usuario;
import com.ufn.projetomuseutreze.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Listar usúarios
    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    //Buscar usúario por ID
    public Usuario buscarPorId(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new RuntimeException("Usuário não encontrado com o ID: " + id);
        }
        return usuario.get();
    }
    //Salvar usúario
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    //Deletar usúario
    public void excluir(Long id){
        Usuario usuario = buscarPorId(id);
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }
}
