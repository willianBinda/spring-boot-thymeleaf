package com.example.demo.arquivos;

import com.example.demo.users.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class ArquivoController {

    private final ArquivoService arquivoService;
    private final ModelMapper modelMapper;

    @GetMapping("/arquivos")
    public String arquivos(
            Model model,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "5") Integer pageSize,
            @RequestParam(name = "query",defaultValue = "") String query
            ){
        System.out.println(page);
        System.out.println(pageSize);
        System.out.println(query);
        model.addAttribute("logged", true);

        Page<ArquivoDTO2> arquivos = arquivoService.listaArquivos(page,pageSize,query);

        model.addAttribute("content",arquivos.getContent());
        model.addAttribute("totalElements",arquivos.getTotalElements());
        model.addAttribute("totalPage",arquivos.getTotalPages());
        model.addAttribute("currentPage",(arquivos.getNumber() + 1));
        model.addAttribute("isFirst",arquivos.isFirst());
        model.addAttribute("isLast",arquivos.isLast());
        model.addAttribute("hasNext",arquivos.hasNext());
        model.addAttribute("hasPrevious",arquivos.hasPrevious());
        model.addAttribute("pageSize",arquivos.getSize());
        model.addAttribute("query",query);

        model.addAttribute("df", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return "/arquivos/index";
    }

    @GetMapping("/arquivos/new")
    public String novoArquivo(Model model){
        model.addAttribute("logged", true);
        return "/arquivos/new";
    }

    @GetMapping("/visualizar-pdf")
    public String visualizarpdf(Model model){
        model.addAttribute("logged", true);
        return "/arquivos/visualizar-pdf";
    }

    @GetMapping("/visualizarPDF")
    public ResponseEntity<Resource> visualizarPDF(@RequestParam("id") String id,Model model){
        model.addAttribute("logged", true);

        ArquivoDTO2 arquivo = arquivoService.getPdfData(id);
        byte[] pdfData = arquivo.getData();

        ByteArrayResource resource = new ByteArrayResource(pdfData);
        HttpHeaders headers = new HttpHeaders();
//        arquivo.getFilename().toLowerCase().endsWith(".pdf")
        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @GetMapping("/download-pdf")
    public ResponseEntity<Resource> downloadPDF(@RequestParam("id") String id){
        ArquivoDTO2 arquivo = arquivoService.getPdfData(id);
        byte[] pdfData = arquivo.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment",arquivo.getFilename());
        ByteArrayResource resource = new ByteArrayResource(pdfData);
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
    @PostMapping("/arquivos/new")
    public ResponseEntity<?> cadastrarArquivo(@ModelAttribute @Valid ArquivoDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());

        }
        // Lógica para salvar os arquivos e o título no banco de dados ou fazer outras operações necessárias
        // Exemplo:
//        for (MultipartFile file : files) {
//            System.out.println(file.getOriginalFilename());
//            // Processar e salvar o arquivo
//            // file.getInputStream() fornece acesso ao conteúdo do arquivo
//            // titulo contém o título do arquivo
//        }
        Boolean isSaved = arquivoService.salvaArquivo(dto);
        if(isSaved){
            return ResponseEntity.ok().body(Map.of("redirectUrl","/arquivos"));

        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao salvar o arquivo.");
        }
    }
//    @PostMapping("/arquivos/new")
//    public String salvaImagem(@ModelAttribute @Valid ArquivoDTO dto, BindingResult bindingResult, Model model){
//        //public String salvaImagem(@ModelAttribute @Valid ArquivoDTO dto, BindingResult bindingResult, Model model){
//        if (bindingResult.hasErrors()) {
//            // Se houver erros, adicione-os ao modelo
//            model.addAttribute("validationErrors", bindingResult.getAllErrors());
//            model.addAttribute("logged", true);
//            return "arquivos/new";
//        }
//        Boolean isSaved = arquivoService.salvaArquivo(dto);
//        model.addAttribute("saved", true);
//        model.addAttribute("logged", true);
//
//        return "arquivos/new";
//    }

    @PostMapping("/arquivos")
    public String removerArquivo(@RequestParam("id") String id){
        System.out.println(id);
        arquivoService.removerArquivo(id);

        return "redirect:/arquivos";
    }
}
