package com.elsevier.subsys.framework.utilities;

import com.elsevier.subsys.framework.base.BasePage;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadPDFFile extends BasePage {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\users\\vajdae\\Downloads\\certificate.pdf");
        FileInputStream fis = new FileInputStream(file);

        PDFParser parser = new PDFParser(fis);
        parser.parse();
        COSDocument cosDoc = parser.getDocument();
        PDDocument pdDocument = new PDDocument(cosDoc);
        PDFTextStripper stripper = new PDFTextStripper();
        String data = stripper.getText(pdDocument);
        System.out.println(data);
        Assert.assertTrue(data.contains("Advances in Applied Mathematics"));
        Assert.assertTrue(data.contains("Awarded since January 2020"));
        Assert.assertTrue(data.contains("3 reviews"));
        Assert.assertTrue(data.contains("ROB HUDSON"));
        cosDoc.close();
        pdDocument.close();
        System.out.println("Text Found on the pdf file");

    }
}
