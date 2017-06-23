package com.hashim.mohamed.weldshoubra;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class WPSActivity extends AppCompatActivity {

    private Button button;
    private static String FILE = Environment.getExternalStorageDirectory() + File.separator + "firstPdf.pdf";
    private static String photo_path = Environment.getExternalStorageDirectory() + File.separator + "a.png";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font greenFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD, BaseColor.GREEN);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wps);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        File file = new File(FILE);

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            DesignPage(document);
            //createImage();
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        pdfView.fromFile(file).load();
    }

    private static void addMetaData(Document document) {
        document.addTitle("WPS PDF");
        document.addSubject("WPS");
        document.addKeywords("WPS");
        document.addAuthor("Mob WPS App");
        document.addCreator("Mob WPS App");
    }

    private void DesignPage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();

        PdfPTable table_title_1 = new PdfPTable(1);
        table_title_1.setWidthPercentage(100);
        table_title_1.addCell(getCell("WELDING PROCEDURE SPECIFICATION (WPS)", PdfPCell.ALIGN_CENTER, greenFont, 8));
        document.add(table_title_1);

        addEmptyLine(preface, 2);
        document.add(preface);

        PdfPTable header_table = new PdfPTable(4);
        header_table.setWidthPercentage(100);
        header_table.addCell(getCell("Project Name", PdfPCell.ALIGN_LEFT, catFont, 8));
        header_table.addCell(getCell("Mob WPS", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        header_table.addCell(getCell("User Name", PdfPCell.ALIGN_CENTER, catFont, 8));
        header_table.addCell(getCell("___________", PdfPCell.ALIGN_RIGHT, catFont, 8));
        header_table.completeRow();

        header_table.addCell(getCell("WPS No", PdfPCell.ALIGN_LEFT, catFont, 8));
        header_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        header_table.addCell(getCell("DATE", PdfPCell.ALIGN_CENTER, catFont, 8));
        header_table.addCell(getCell("___________", PdfPCell.ALIGN_RIGHT, catFont, 8));
        header_table.completeRow();

        header_table.addCell(getCell("WELDING PROCESS", PdfPCell.ALIGN_LEFT, catFont, 8));
        header_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        header_table.addCell(getCell("TYPE", PdfPCell.ALIGN_CENTER, catFont, 8));
        header_table.addCell(getCell("___________", PdfPCell.ALIGN_RIGHT, catFont, 8));
        header_table.completeRow();

        document.add(header_table);
        Chunk linebreak1 = new Chunk(new LineSeparator());
        document.add(linebreak1);


        PdfPTable table_title_2 = new PdfPTable(1);
        table_title_2.setWidthPercentage(100);
        table_title_2.addCell(getCell("JOINTS DESIGN", PdfPCell.ALIGN_CENTER, greenFont, 20));
        document.add(table_title_2);

        PdfPTable table_joints_design = new PdfPTable(2);
        table_joints_design.setWidthPercentage(100);
        try {
            table_joints_design.addCell(createImageCell(photo_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        table_joints_design.addCell(getCell("jhhj", PdfPCell.ALIGN_MIDDLE,catFont));
        table_joints_design.completeRow();
//
        table_joints_design.addCell(getCell("JOINT TYPE:______Butt Joint", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell("", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();
        table_joints_design.addCell(getCell("Backing:___________N/A", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell("", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();
        table_joints_design.addCell(getCell("Backing Material:___N/A", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell("", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();
        table_joints_design.addCell(getCell("GROOVE ANGLE (α)", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();
        table_joints_design.addCell(getCell("ROOT OPENING(R)", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();
        table_joints_design.addCell(getCell("ROOT FACE (F)", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();

        document.add(table_joints_design);
        Chunk linebreak2 = new Chunk(new LineSeparator());
        document.add(linebreak2);


        PdfPTable table_title_3 = new PdfPTable(1);
        table_title_3.setWidthPercentage(100);
        table_title_3.addCell(getCell("BASE METAL", PdfPCell.ALIGN_CENTER, greenFont, 20));
        document.add(table_title_3);

        PdfPTable table_base_metal = new PdfPTable(2);
        table_base_metal.setWidthPercentage(100);

        table_base_metal.addCell(getCell("MATERIAL 1", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();
        table_base_metal.addCell(getCell("MATERIAL 2", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();
        table_base_metal.addCell(getCell("THICKNESS", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();
        table_base_metal.addCell(getCell("GROOVE", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();


        document.add(table_base_metal);
        Chunk linebreak3 = new Chunk(new LineSeparator());
        document.add(linebreak3);


        PdfPTable table_title_4 = new PdfPTable(1);
        table_title_4.setWidthPercentage(100);
        table_title_4.addCell(getCell("FILLER METALS", PdfPCell.ALIGN_CENTER, greenFont, 20));
        document.add(table_title_4);

        PdfPTable table_filler_metal = new PdfPTable(2);
        table_filler_metal.setWidthPercentage(100);

        table_filler_metal.addCell(getCell("AWS CLASSIFICATION", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("FILLER METAL SIZE", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("ELECTRODE-FLUX (CLASS)", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("FLUX TRADE NAME", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("OTHER", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();


        document.add(table_filler_metal);
        Chunk linebreak4 = new Chunk(new LineSeparator());
        document.add(linebreak4);


        PdfPTable table_title_5 = new PdfPTable(1);
        table_title_5.setWidthPercentage(100);
        table_title_5.addCell(getCell("POSITION", PdfPCell.ALIGN_CENTER, greenFont, 20));
        document.add(table_title_5);

        PdfPTable position_table = new PdfPTable(4);
        position_table.setWidthPercentage(100);
        position_table.addCell(getCell("POSITION(S) OF GROOVE", PdfPCell.ALIGN_LEFT, catFont, 8));
        position_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        position_table.addCell(getCell("WELD PROGRESSION", PdfPCell.ALIGN_CENTER, catFont, 0));
        position_table.addCell(getCell("___________", PdfPCell.ALIGN_RIGHT, catFont, 8));
        position_table.completeRow();

        document.add(position_table);
        Chunk linebreak5 = new Chunk(new LineSeparator());
        document.add(linebreak5);


        addEmptyLine(preface, 4);
        document.add(preface);
        PdfPTable table_title_6 = new PdfPTable(1);
        table_title_6.setWidthPercentage(100);
        table_title_6.addCell(getCell("PREHEAT", PdfPCell.ALIGN_CENTER, greenFont, 20));
        document.add(table_title_6);

        PdfPTable preheat_table = new PdfPTable(4);
        preheat_table.setWidthPercentage(100);
        preheat_table.addCell(getCell("PREHEAT TEMP. (MINIMUM)", PdfPCell.ALIGN_LEFT, catFont, 8));
        preheat_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        preheat_table.addCell(getCell("INTERPASS TEMP. (MAXIMUM)", PdfPCell.ALIGN_CENTER, catFont, 0));
        preheat_table.addCell(getCell("200", PdfPCell.ALIGN_RIGHT, catFont, 8));
        preheat_table.completeRow();

        document.add(preheat_table);
        Chunk linebreak6 = new Chunk(new LineSeparator());
        document.add(linebreak6);


        PdfPTable table_title_7 = new PdfPTable(1);
        table_title_7.setWidthPercentage(100);
        table_title_7.addCell(getCell("SHIELDING", PdfPCell.ALIGN_CENTER, greenFont, 20));
        document.add(table_title_7);

        PdfPTable shielding_table = new PdfPTable(2);
        shielding_table.setWidthPercentage(100);
        shielding_table.addCell(getCell("GAS", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell("", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();
        shielding_table.addCell(getCell("TYPE OF GAS :", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();
        shielding_table.addCell(getCell("GAS COMPOSITION:", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();
        shielding_table.addCell(getCell("FLOW RATE(LIT/MIN):", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell("____8:16", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();

        document.add(shielding_table);
        Chunk linebreak7 = new Chunk(new LineSeparator());
        document.add(linebreak7);


        PdfPTable table_title_8 = new PdfPTable(1);
        table_title_8.setWidthPercentage(100);
        table_title_8.addCell(getCell("ELECTRICAL CHARACTERISTICS", PdfPCell.ALIGN_CENTER, greenFont, 20));
        document.add(table_title_6);

        PdfPTable electerical_table = new PdfPTable(4);
        electerical_table.setWidthPercentage(100);
        electerical_table.addCell(getCell("CURRENT TYPE AND POLARITY", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table.addCell(getCell("PULSING (YES OR NO)", PdfPCell.ALIGN_CENTER, catFont, 0));
        electerical_table.addCell(getCell("___________", PdfPCell.ALIGN_RIGHT, catFont, 8));
        electerical_table.completeRow();
        electerical_table.addCell(getCell("CURRENT (RANGE)", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table.addCell(getCell("VOLTAGE (RANGE)", PdfPCell.ALIGN_CENTER, catFont, 0));
        electerical_table.addCell(getCell("___________", PdfPCell.ALIGN_RIGHT, catFont, 8));
        electerical_table.completeRow();
        document.add(electerical_table);


        PdfPTable electerical_table2 = new PdfPTable(2);
        electerical_table2.setWidthPercentage(100);

        electerical_table2.addCell(getCell("WIRE FEED SPEED (RANGE)", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        electerical_table2.addCell(getCell("TUNGSTEN ELECTRODE SIZE AND TYPE", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        electerical_table2.addCell(getCell("PULSING PARAMETERS", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        electerical_table2.addCell(getCell("OTHER", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        document.add(electerical_table2);


        Chunk linebreak8 = new Chunk(new LineSeparator());
        document.add(linebreak8);


        // Start a new page
        document.newPage();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    public PdfPCell getCell(String text, int alignment, Font font, int padding) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(padding);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }

    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
        Image img = Image.getInstance(path);
        PdfPCell cell = new PdfPCell(img, true);
        cell.setPaddingBottom(25);
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setColspan(2);
        cell.setFixedHeight(280);
//                img.setScaleToFitLineWhenOverflow(true);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;
    }
}