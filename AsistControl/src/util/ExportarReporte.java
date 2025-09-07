/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Asistencia;;
/**
 *
 * @author cristobalO.O
 */
public class ExportarReporte {
    
    public static void exportarAsistencias(List<Asistencia> lista, String rutaArchivo, String estadoFiltro) {
        try {
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            // Encabezado
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            String tituloTexto = "Reporte de Asistencias";

            if (estadoFiltro != null && !estadoFiltro.equalsIgnoreCase("Todos")) {
                tituloTexto += " - " + estadoFiltro;
            }

            Paragraph titulo = new Paragraph(tituloTexto, tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

            // Tabla
            PdfPTable pdfTable = new PdfPTable(4);
            pdfTable.setWidthPercentage(100);
            pdfTable.setWidths(new float[]{3, 3, 3, 2});

            pdfTable.addCell(celdaEncabezado("Empleado"));
            pdfTable.addCell(celdaEncabezado("Entrada"));
            pdfTable.addCell(celdaEncabezado("Salida"));
            pdfTable.addCell(celdaEncabezado("Estado"));

            for (Asistencia a : lista) {
                pdfTable.addCell(a.getUsuario() != null ? a.getUsuario().getNombre() : "Sin nombre");
                pdfTable.addCell(a.getFechaHoraEntrada() != null ? a.getFechaHoraEntrada().format(formato) : "-");
                pdfTable.addCell(a.getFechaHoraSalida() != null ? a.getFechaHoraSalida().format(formato) : "-");
                pdfTable.addCell(a.getEstado());
            }

            document.add(pdfTable);
            document.close();

            System.out.println("✅ Reporte generado en: " + rutaArchivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static PdfPCell celdaEncabezado(String texto) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        return cell;
    }
    
}
