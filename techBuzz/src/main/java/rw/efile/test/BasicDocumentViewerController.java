package rw.efile.test;


import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;

@ManagedBean  
@SessionScoped  
public class BasicDocumentViewerController implements Serializable {  
  
    private static final long serialVersionUID = 1L;  
  
    private StreamedContent content;  
  
    public void onPrerender(ComponentSystemEvent event) {  
  
        try {  
      
            ByteArrayOutputStream out = new ByteArrayOutputStream();  
  
            Document document = new Document();  
            PdfWriter.getInstance(document, out);  
            document.open();  
  
            for (int i = 0; i < 50; i++) {  
                document.add(new Paragraph("All work and no play makes Jack a dull boy"));  
            }  
              
            document.close();  
            content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public StreamedContent getContent() {  
        return content;  
    }  
  
    public void setContent(StreamedContent content) {  
        this.content = content;  
    }  
}  