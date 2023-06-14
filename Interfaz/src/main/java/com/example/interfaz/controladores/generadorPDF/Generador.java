package com.example.interfaz.controladores.generadorPDF;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;

public class Generador{
    private String titulo;

    public Generador(String titulo){
        this.titulo = titulo;
    }

    public void generar(){
        PdfPTable header = new PdfPTable(3);
        Document document = new Document();
        document.setPageSize(PageSize.LETTER);
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        System.out.println(desktopPath);
        try{

            File archivo = new File(desktopPath, "Reporte.pdf");
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();
            header.setTotalWidth(527);
            header.setWidths(new int[]{3, 14, 10});
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            var URL = getClass().getResource("/img/Logo.png");
            Image logo = Image.getInstance(URL);
            logo.setAlignment(Element.ALIGN_CENTER);
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(10);
            text.setPaddingLeft(10);
            text.setHorizontalAlignment(1);
            text.setVerticalAlignment(1);
            text.setBorder(Rectangle.BOTTOM);
            text.addElement(new Phrase("La Canasta"));
            PdfPCell datos = new PdfPCell();
            datos.setPaddingBottom(10);
            datos.setPaddingLeft(0);
            datos.setBorder(Rectangle.BOTTOM);
            datos.setHorizontalAlignment(Element.ALIGN_CENTER);
            datos.setVerticalAlignment(1);
            datos.addElement(new Phrase("Dirección: Del Campesino #90 "));
            datos.addElement(new Phrase("Teléfonos: 2283044492 o 8346785"));
            datos.addElement(new Phrase("Email: laCanasta@gmail.com"));
            header.addCell(logo);
            header.addCell(text);
            header.addCell(datos);
            document.add(header);

            //Cuerpo
            Paragraph Titulo = new Paragraph();
            Titulo.add(new Chunk(titulo));
            Titulo.setAlignment(1);
            Titulo.setSpacingBefore(20);
            document.add(Titulo);


            //Tabla
            Font fuente = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);

            PdfPTable tablaP = new PdfPTable(4);
            tablaP.setTotalWidth(527);
            tablaP.setWidths(new int[]{5, 8, 5, 5});
            tablaP.setLockedWidth(true);
            tablaP.getDefaultCell().setFixedHeight(40);
            tablaP.getDefaultCell().setBorder(Rectangle.BOX);
            tablaP.setSpacingBefore(25);

            PdfPCell cellClave = new PdfPCell();
            Paragraph clave = new Paragraph("Clave", fuente);
            clave.setAlignment(Element.ALIGN_CENTER);
            cellClave.setPaddingBottom(5);
            cellClave.addElement(clave);
            clave.getFont().setColor(BaseColor.WHITE);
            cellClave.setBackgroundColor(new BaseColor(26, 153, 205));

            PdfPCell cellNombre = new PdfPCell();
            Paragraph Nombre = new Paragraph("Nombre", fuente);
            Nombre.getFont().setColor(BaseColor.WHITE);
            Nombre.setAlignment(Element.ALIGN_CENTER);
            cellNombre.setPaddingBottom(5);
            cellNombre.addElement(Nombre);
            cellNombre.setBackgroundColor(new BaseColor(26, 153, 205));

            PdfPCell cellCantidad = new PdfPCell();
            Paragraph cantidad = new Paragraph("Cantidad", fuente);
            cantidad.setAlignment(Element.ALIGN_CENTER);
            cantidad.getFont().setColor(BaseColor.WHITE);
            cellCantidad.setPaddingBottom(5);
            cellCantidad.addElement(cantidad);
            cellCantidad.setBackgroundColor(new BaseColor(26, 153, 205));

            PdfPCell cellPrecio = new PdfPCell();
            Paragraph precio = new Paragraph("Precio", fuente);
            precio.setAlignment(Element.ALIGN_CENTER);
            cellPrecio.setPaddingBottom(5);
            cellPrecio.addElement(precio);
            precio.getFont().setColor(BaseColor.WHITE);
            cellPrecio.setBackgroundColor(new BaseColor(26, 153, 205));

            tablaP.addCell(cellClave);
            tablaP.addCell(cellNombre);
            tablaP.addCell(cellCantidad);
            tablaP.addCell(cellPrecio);
            document.add(tablaP);

            document.close();

        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void generarPDF(){
        PdfPTable header = new PdfPTable(3);
        Document document = new Document();
        document.setPageSize(PageSize.LETTER);
        try{
            File archivo = new File("Reporte.pdf");
            System.out.println(archivo);
            PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();
            header.setTotalWidth(527);
            header.setWidths(new int[]{3, 14, 10});
            header.setLockedWidth(true);
            header.getDefaultCell().setFixedHeight(40);
            header.getDefaultCell().setBorder(Rectangle.BOTTOM);
            var URL = getClass().getResource("/img/Logo.png");
            Image logo = Image.getInstance(URL);
            logo.setAlignment(Element.ALIGN_CENTER);
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(10);
            text.setPaddingLeft(10);
            text.setHorizontalAlignment(1);
            text.setVerticalAlignment(1);
            text.setBorder(Rectangle.BOTTOM);
            text.addElement(new Phrase("La Canasta"));
            PdfPCell datos = new PdfPCell();
            datos.setPaddingBottom(10);
            datos.setPaddingLeft(0);
            datos.setBorder(Rectangle.BOTTOM);
            datos.setHorizontalAlignment(Element.ALIGN_CENTER);
            datos.setVerticalAlignment(1);
            datos.addElement(new Phrase("Dirección: Del Campesino #90 "));
            datos.addElement(new Phrase("Teléfonos: 2283044492 o 8346785"));
            datos.addElement(new Phrase("Email: laCanasta@gmail.com"));
            header.addCell(logo);
            header.addCell(text);
            header.addCell(datos);
            document.add(header);

            //Cuerpo
            Paragraph Titulo = new Paragraph();
            Titulo.add(new Chunk(titulo));
            Titulo.setAlignment(1);
            Titulo.setSpacingBefore(20);
            document.add(Titulo);


            //Tabla
            Font fuente = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);

            PdfPTable tablaP = new PdfPTable(4);
            tablaP.setTotalWidth(527);
            tablaP.setWidths(new int[]{5, 8, 5, 5});
            tablaP.setLockedWidth(true);
            tablaP.getDefaultCell().setFixedHeight(40);
            tablaP.getDefaultCell().setBorder(Rectangle.BOX);
            tablaP.setSpacingBefore(25);

            PdfPCell cellClave = new PdfPCell();
            Paragraph clave = new Paragraph("Clave", fuente);
            clave.setAlignment(Element.ALIGN_CENTER);
            cellClave.setPaddingBottom(5);
            cellClave.addElement(clave);
            clave.getFont().setColor(BaseColor.WHITE);
            cellClave.setBackgroundColor(new BaseColor(26, 153, 205));

            PdfPCell cellNombre = new PdfPCell();
            Paragraph Nombre = new Paragraph("Nombre", fuente);
            Nombre.getFont().setColor(BaseColor.WHITE);
            Nombre.setAlignment(Element.ALIGN_CENTER);
            cellNombre.setPaddingBottom(5);
            cellNombre.addElement(Nombre);
            cellNombre.setBackgroundColor(new BaseColor(26, 153, 205));

            PdfPCell cellCantidad = new PdfPCell();
            Paragraph cantidad = new Paragraph("Cantidad", fuente);
            cantidad.setAlignment(Element.ALIGN_CENTER);
            cantidad.getFont().setColor(BaseColor.WHITE);
            cellCantidad.setPaddingBottom(5);
            cellCantidad.addElement(cantidad);
            cellCantidad.setBackgroundColor(new BaseColor(26, 153, 205));

            PdfPCell cellPrecio = new PdfPCell();
            Paragraph precio = new Paragraph("Precio", fuente);
            precio.setAlignment(Element.ALIGN_CENTER);
            cellPrecio.setPaddingBottom(5);
            cellPrecio.addElement(precio);
            precio.getFont().setColor(BaseColor.WHITE);
            cellPrecio.setBackgroundColor(new BaseColor(26, 153, 205));

            tablaP.addCell(cellClave);
            tablaP.addCell(cellNombre);
            tablaP.addCell(cellCantidad);
            tablaP.addCell(cellPrecio);
            document.add(tablaP);

            document.close();

        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
    }



}
