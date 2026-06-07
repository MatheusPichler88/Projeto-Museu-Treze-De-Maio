package com.ufn.projetomuseutreze.service;

import com.ufn.projetomuseutreze.model.Revista;
import com.ufn.projetomuseutreze.repository.RevistaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RevistaService {
    private final RevistaRepository revistaRepository;

    public RevistaService(RevistaRepository revistaRepository) {
        this.revistaRepository = revistaRepository;
    }

    //Listar revistas
    public List<Revista> listarTodos(){
        return revistaRepository.findByAtivoTrue();
    }
    //Buscar revista por ID
    public Revista buscarPorId(Long id){
        Optional<Revista> revista = revistaRepository.findById(id);
        if(revista.isEmpty()){
            throw new RuntimeException("Revista não encontrada com o ID: " + id);
        }
        return revista.get();
    }
    //Salvar revista
    public Revista salvar(Revista revista){
        if(revista.getId() == null){
            Revista revistaSalvo = revistaRepository.save(revista);
            String codigoGerado = String.format("R-%03d", revistaSalvo.getId());
            revistaSalvo.setCodigoPatrimonio(codigoGerado);
            return revistaRepository.save(revistaSalvo);
        }
        return revistaRepository.save(revista);
    }
    //Desativar revista
    public void excluir(Long id){
        Revista revista = buscarPorId(id);
        revista.setAtivo(false);
        revistaRepository.save(revista);
    }
}
