package com.ufn.projetomuseutreze.service;

import com.ufn.projetomuseutreze.model.Livro;
import com.ufn.projetomuseutreze.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    //Listar livros
    public List<Livro> listarTodos(){
        return livroRepository.findByAtivoTrue();
    }
    //Buscar livro por ID
    public Livro buscarPorId(Long id){
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isEmpty()){
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }
        return livro.get();
    }

    //Salvar livro
    public Livro salvar(Livro livro){
        if(livro.getId() == null){
            //Salva o livro
            Livro livroSalvo = livroRepository.save(livro);
            //Gera o código no formato L-001
            String codigoGerado = String.format("L-%03d", livroSalvo.getId());
            livroSalvo.setCodigoPatrimonio(codigoGerado);
            //Atualiza o livro no banco
            return livroRepository.save(livroSalvo);
        }
        return livroRepository.save(livro);
    }

    //Desativar livro
    public void Desativar(Long id){
        Livro livro = buscarPorId(id);
        livro.setAtivo(false);
        livroRepository.save(livro);
    }
}
