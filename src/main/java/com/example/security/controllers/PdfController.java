package com.example.security.controllers;

import com.example.security.generator.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/pdf")
public class PdfController {

    private static final Logger logger = LoggerFactory.getLogger(PdfController.class);

    @Autowired
    private PdfGenerator pdfGenerator;

    @GetMapping("/gerado")
    public ResponseEntity<byte[]> gerarOrcamentoPdf() {
        byte[] pdfData;
        try {
            pdfData = pdfGenerator.generateOrcamentoPdf();
            // Retorna os dados do PDF como uma resposta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "orcamento.pdf");
            headers.setContentLength(pdfData.length);
            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Erro ao gerar PDF", e);
            // Retorna uma mensagem de erro em formato byte[]
            String errorMessage = "Falha ao gerar PDF";
            byte[] errorResponse = errorMessage.getBytes(StandardCharsets.UTF_8);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            headers.setContentLength(errorResponse.length);
            return new ResponseEntity<>(errorResponse, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
