package com.example.demo.arquivos;

import com.example.demo.users.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArquivoController {

    private final ArquivoService arquivoService;
    private final ModelMapper modelMapper;

    @GetMapping("/arquivos")
    public String arquivos(Model model){
        model.addAttribute("logged", true);

        List<ArquivoDTO2> arquivos = arquivoService.listaArquivos();
        model.addAttribute("listaArquivos",arquivos);
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
    public String salvaImagem(@ModelAttribute @Valid ArquivoDTO dto, BindingResult bindingResult, Model model){
        //public String salvaImagem(@ModelAttribute @Valid ArquivoDTO dto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            // Se houver erros, adicione-os ao modelo
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            model.addAttribute("logged", true);
            return "arquivos/new";
        }
        Boolean isSaved = arquivoService.salvaArquivo(dto);
        model.addAttribute("saved", true);
        model.addAttribute("logged", true);

        return "arquivos/new";
    }
}
