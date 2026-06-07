package com.ufn.projetomuseutreze.service;

import com.ufn.projetomuseutreze.model.AcervoHistorico;
import com.ufn.projetomuseutreze.repository.AcervoHistoricoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AcervoHistoricoService {
    private final AcervoHistoricoRepository acervoHistoricoRepository;
    public AcervoHistoricoService(AcervoHistoricoRepository acervoHistoricoRepository) {
        this.acervoHistoricoRepository = acervoHistoricoRepository;
    }

    //Listar itens do acervo
    public List<AcervoHistorico> listarTodos(){
        return acervoHistoricoRepository.findByAtivoTrue();
    }
    //Buscar item do acervo por ID
    public AcervoHistorico buscarPorId(Long id){
        Optional<AcervoHistorico> acervoHistorico = acervoHistoricoRepository.findById(id);
        if(acervoHistorico.isEmpty()){
            throw new RuntimeException("Item do acervo histórico não encontrado com o ID: " + id);
        }
        return acervoHistorico.get();
    }

    //Salvar item do acervo
    public AcervoHistorico salvar(AcervoHistorico acervoHistorico){
        if(acervoHistorico.getId() == null){
            AcervoHistorico acervoHistoricoSalvo = acervoHistoricoRepository.save(acervoHistorico);
            String codigoGerado = String.format("AH-%03d", acervoHistoricoSalvo.getId());
            acervoHistoricoSalvo.setCodigoPatrimonio(codigoGerado);
            return acervoHistoricoRepository.save(acervoHistoricoSalvo);
        }
        return acervoHistoricoRepository.save(acervoHistorico);
    }

    //Desativar item do acervo
    public void Desativar(Long id){
        AcervoHistorico acervoHistorico = buscarPorId(id);
        acervoHistorico.setAtivo(false);
        acervoHistoricoRepository.save(acervoHistorico);
    }
}
