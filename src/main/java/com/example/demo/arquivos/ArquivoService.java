package com.example.demo.arquivos;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        List<ArquivoDTO2> arquivosMulti = arquivoDTO.getFiles().stream()
                .map(file -> {
                    ArquivoDTO2 arquivos = new ArquivoDTO2();
                    arquivos.setTitulo(arquivoDTO.getTitulo());
                    arquivos.setFilename(file.getOriginalFilename());
                    try {
                        arquivos.setData(file.getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return arquivos;
                })
                .toList();
        List<Arquivo> arquivosEntity = arquivosMulti.stream()
                .map(ar -> modelMapper.map(ar,Arquivo.class))
                .toList();
        arquivoRepository.saveAll(arquivosEntity);
//        Arquivo arquivo = modelMapper.map(arquivoDTO2,Arquivo.class);
//        arquivoRepository.save(arquivo);
//        return modelMapper.map(arquivo,ArquivoDTO2.class);
        return true;
    }

    public List<ArquivoDTO2> listaArquivos(){
        return arquivoRepository.findAll()
                .stream()
                .map(a -> modelMapper.map(a,ArquivoDTO2.class))
                .toList();
    }

    public ArquivoDTO2 getPdfData(String id) {
        Optional<Arquivo> arquivo = arquivoRepository.findById(id);
        return arquivo.map(value -> modelMapper.map(value, ArquivoDTO2.class)).orElse(null);
    }
}
