/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import Client.MainForm;
import Controls.c_BookingSummary;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 *
 * @author Rodolfo Giampietro
 */
public class ExportToPDF {
    
    public ExportToPDF(){
        
    }
    public static void CreatePDFFile(MainForm _mainWindow){
        try {
            
        c_BookingSummary summary = _mainWindow.tabBooking.bookingSummary;
        Paragraph title = new Paragraph("RIEPILOGO PRENOTAZIONE:");
        Paragraph date = new Paragraph("Data: " + summary.lblDateValue.getText().toString());
        Paragraph time = new Paragraph("Orario: " + summary.lblTimeValue.getText().toString());    
        Paragraph film = new Paragraph("Film: " + summary.lblFilmValue.getText().toString());
        Paragraph seated = new Paragraph("Posti: " + summary.lblSeatedListValue.getText().toString());
        Paragraph seatedCount = new Paragraph("Nr. Posti: " + summary.lblSeatedCountValue.getText().toString());
        Paragraph price = new Paragraph("Prezzo: " + summary.lblPriceValue.getText().toString());
        String codeStr = summary.lblDateValue.getText().toString() + "_" + summary.lblFilmValue.getText().toString().trim() + "_" + _mainWindow._currentUserItem.getUsername().trim();
        
        Paragraph code = new Paragraph("Codice Univoco della prenotazione: " + codeStr);
        int colCount = 2;
        //Oggetto PDF Table
//        PdfPTable pdfTable = new PdfPTable(colCount);
//        //Oggetto PDFCell
//        PdfPCell cell;
//        cell = new PdfPCell(new Phrase(summary.lblFilmValue.getText()));
//        cell.setColspan(colCount);
//        pdfTable.addCell(cell);
//       	
//
//       

        /*
            * CREA IL PDF
            */
        Document document = new Document(PageSize.LETTER.rotate());
        /*
            * Crea fileName
            */
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minutes = cal.get(Calendar.MINUTE);
        System.out.println(year);
        String fileName = year + "." + month + "." + day + "_" + hour +"." +minutes + "_Prenotazione_Cinema_" + summary.lblFilmValue.getText().trim() + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();
        document.add(title);
        document.add(date);
        document.add(time);
        document.add(film);
        document.add(seated);
        document.add(seatedCount);
        document.add(price);
        document.add(code);
        
        
        //document.add(pdfTable);
        document.close();
        File file = new File(fileName);
        Desktop.getDesktop().open( file );
        }
        catch(Exception e){
            
        }
    }
}
