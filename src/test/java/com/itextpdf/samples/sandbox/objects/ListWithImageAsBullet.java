/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package com.itextpdf.samples.sandbox.objects;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;

import java.io.File;
import java.io.IOException;

public class ListWithImageAsBullet {
    public static final String DEST = "./target/sandbox/objects/list_with_image_bullet.pdf";
    public static final String IMG = "src/test/resources/img/bulb.gif";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ListWithImageAsBullet().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        ImageData image = ImageDataFactory.create(IMG);
        PdfImageXObject xObject = new PdfImageXObject(image);
        List list = new List().
                setListSymbol(new com.itextpdf.layout.element.Image(xObject)).
                add("Hello World").
                add("This is a list item with a lot of text. " +
                        "It will certainly take more than one line." +
                        " This shows that the list item is indented and that the image is used as bullet.").
                add("This is a test");
        doc.add(list);

        doc.close();
    }
}
