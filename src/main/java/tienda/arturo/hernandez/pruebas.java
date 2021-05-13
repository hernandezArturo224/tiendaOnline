package tienda.arturo.hernandez;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tienda.arturo.hernandez.utilidades.PDFHeaderFooter;

public class pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola");
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		
	    try {      
	    	//Obtenemos la instancia del archivo a utilizar
	    	String fileLocation = new File("src\\main\\resources\\static\\pdf").getAbsolutePath() + "\\" + "pdf1.pdf";
	    	
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(fileLocation));
	    	
		    //Para insertar cabeceras/pies en todas las páginas
	    	writer.setPageEvent(new PDFHeaderFooter());
	        
		    //Abrimos el documento para edición
		    documento.open();
		    
		    //PARRAFOS
			Paragraph paragraph = new Paragraph();
			//String contenido = "esto es un párrafo";
			//paragraph.setSpacingBefore(100);
			paragraph.add("\n\n");
			//String font = "Sans";
			//float tamanno = 11;
			//int style = Font.BOLD;
			//BaseColor color = BaseColor.BLACK;
			//float spacBefore = 0;
			//float spacAfter = 5;
		    
			//paragraph.setAlignment(Element.ALIGN_CENTER);
		    //paragraph.setFont(new Font(FontFactory.getFont(font, tamanno, style, color)));
		    //paragraph.add("esto es una párrafo");
		    //paragraph.setSpacingBefore(spacBefore);
		    //paragraph.setSpacingAfter(spacAfter);
		    
	    	documento.add(paragraph);
	    	
	    	
	    	//TABLAS
		    
			//Instanciamos una tabla de X columnas
		    PdfPTable tabla = new PdfPTable(2);
		    
		    //Ancho de cada columna
	        //int[] values = new int[]{40,40,40,40};
	        //tabla.setWidths(values);
		    //tabla.setWidthPercentage(new Float(100));
		    
		    //Phrase phrase = new Phrase("contenido de la celda", 
		    //new Font(FontFactory.getFont("Sans", 9, Font.BOLD, BaseColor.BLACK)));
		    //Phrase phrase = new Phrase("contenido de la celda");
		    Phrase texto = new Phrase("cabecera");
			PdfPCell cabecera = new PdfPCell(texto);
			//PdfPCell cabecera2 = new PdfPCell(texto);
			//Propiedades concretas de formato
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
		    //celda.setBorderColor(BaseColor.WHITE);
			//cabecera.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		    //celda.setVerticalAlignment(PdfPCell.ALIGN_TOP);
		    //celda.setPaddingBottom(3);
		    
		    tabla.addCell(cabecera);
		    tabla.addCell(cabecera);
		    tabla.addCell("3");
		    tabla.addCell("4");
		    tabla.addCell("5");
		    tabla.addCell("6");
		    
		    //documento.add(tabla);
		    
		    /*
		    PdfPTable tabla = new PdfPTable(4);
		    //tabla.addCell(cabecera);
		    //tabla.addCell(cabecera);
		    //tabla.addCell(cabecera);
		    
		    //tabla.addCell(celda);
		    
		    tabla.addCell(new PdfPCell(new Phrase("1")));
		    tabla.addCell(new PdfPCell(new Phrase("2")));
		    tabla.addCell(new PdfPCell(new Phrase("3")));
		    tabla.addCell(new PdfPCell(new Phrase("4")));
		    
		    //tabla.addCell(new PdfPCell(new Phrase("contenido de la celda")));
		    //tabla.addCell(celda);
		    //tabla.addCell(celda);
		    //tabla.completeRow();
		    //tabla.addCell(celda);
*/
		    documento.add(tabla);
	    	
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
		    
		    //Abrir automáticamente el fichero creado
		    //http://docs.oracle.com/javase/6/docs/api/java/awt/Desktop.html
		    
		    
		    try {
		        File path = new File(fileLocation);
		        Desktop.getDesktop().open(path);
		        System.out.println(path.getAbsolutePath());
		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
			
	    } catch (Exception ex) {
	    	ex.getMessage();
	    }
	}
		
		

}
