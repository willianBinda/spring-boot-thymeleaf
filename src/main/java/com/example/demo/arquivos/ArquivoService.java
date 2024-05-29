package com.example.demo.arquivos;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ArquivoService {
    private final ArquivoRepository arquivoRepository;
    private final ModelMapper modelMapper;

    public Boolean salvaArquivo(ArquivoDTO arquivoDTO){
        for (MultipartFile file : arquivoDTO.getFiles()) {
            ArquivoDTO2 arquivos = new ArquivoDTO2();
            arquivos.setTitulo(arquivoDTO.getTitulo());
            arquivos.setFilename(file.getOriginalFilename());
            try {
                arquivos.setData(file.getBytes());
            } catch (IOException e) {
                return false;
//                throw new RuntimeException(e);
            }
            Arquivo arquivo = modelMapper.map(arquivos,Arquivo.class);
            arquivoRepository.save(arquivo);

        }
        return true;

//        List<ArquivoDTO2> arquivosMulti = arquivoDTO.getFiles().stream()
//                .map(file -> {
//                    ArquivoDTO2 arquivos = new ArquivoDTO2();
//                    arquivos.setTitulo(arquivoDTO.getTitulo());
//                    arquivos.setFilename(file.getOriginalFilename());
//                    try {
//                        arquivos.setData(file.getBytes());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    return arquivos;
//                })
//                .toList();
//        List<Arquivo> arquivosEntity = arquivosMulti.stream()
//                .map(ar -> modelMapper.map(ar,Arquivo.class))
//                .toList();
//        arquivoRepository.saveAll(arquivosEntity);
//
//        return true;
    }

    public Page<ArquivoDTO2> listaArquivos(Integer page, Integer pageSize, String query){

        int def_page = page < 1 ? 0 : page - 1;

        Pageable adj_page = PageRequest.of(def_page, pageSize, Sort.Direction.DESC,"createdAt");

        return arquivoRepository.searchArquivo(query, adj_page)
                .map(a -> modelMapper.map(a,ArquivoDTO2.class));
    }

    public ArquivoDTO2 getPdfData(String id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        return arquivo.map(value -> modelMapper.map(value, ArquivoDTO2.class)).orElse(null);
    }

    public void removerArquivo(String id) {
        arquivoRepository.deleteById(id);
    }
}
