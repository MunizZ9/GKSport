package com.example.security.generator;

import com.example.security.models.Camisa;
import com.example.security.service.CamisaService;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Component
public class PdfGenerator {

    @Autowired
    private CamisaService camisaService;

    public byte[] generateOrcamentoPdf() throws IOException {
        List<Camisa> camisas = camisaService.listarTodasCamisa(); // Obtém todas as camisas do serviço

        double valorTotal = 0;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); // Cria um fluxo de saída de bytes

        try (PdfWriter writer = new PdfWriter(outputStream);
             PdfDocument pdfDocument = new PdfDocument(writer)) {

            Document document = new Document(pdfDocument); // Cria um documento PDF

            // Adiciona um título ao documento
            Paragraph title = new Paragraph("Orçamento das camisas")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(20);


            document.add(title);

            document.add(new Paragraph(" "));

            // Cria uma tabela para as camisas
            Table table = new Table(4);
            table.setWidth(520);

            // Cabeçalho da tabela
            table.addCell(createCell("ID", true));
            table.addCell(createCell("Tecido", true));
            table.addCell(createCell("Valor", true));
            table.addCell(createCell("Tamanho", true));

            // Adiciona os detalhes das camisas à tabela
            for (Camisa camisa : camisas) {
                table.addCell(createCell(String.valueOf(camisa.getId()), false));
                table.addCell(createCell(camisa.getNome(), false));
                table.addCell(createCell(formatarValor(camisa.getValor()), false));
                table.addCell(createCell(camisa.getTamanho(), false));

                valorTotal += camisa.getValor();
            }

            document.add(table);

            // Rodapé
            Text rodape = new Text("-----------------------------------------------").setFontColor(new DeviceRgb(255, 0, 0));
            Paragraph footer = new Paragraph(rodape)
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(footer);

            // Adiciona o valor total ao documento
            Paragraph totalParagraph = new Paragraph("Valor Total: R$" + formatarValor(valorTotal))
                    .setFontSize(14);
            document.add(totalParagraph);

            document.close();
        }

        return outputStream.toByteArray(); // Retorna o conteúdo do PDF como um array de bytes
    }

    private String formatarValor(double valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(valor);
    }

    private Cell createCell(String content, boolean isHeader) {
        Cell cell = new Cell().add(new Paragraph(content));
        if (isHeader) {
            cell.setBackgroundColor(new DeviceRgb(192, 192, 192));
            cell.setBold();
        }
        return cell;
    }
}
