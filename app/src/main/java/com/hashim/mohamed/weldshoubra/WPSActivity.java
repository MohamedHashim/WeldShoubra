package com.hashim.mohamed.weldshoubra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    ImageView back_btn;
    TextView toolbartxt;
    Button UTS_btn;

    private static String FILE = Environment.getExternalStorageDirectory() + File.separator + "firstPdf.pdf";
    private static String photo_path = Environment.getExternalStorageDirectory() + File.separator + "a.png";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    String material, thickness, groove, position, area_condition, welding_method, process;
    Boolean Square_joint_groove, Single_bevel_groove, Double_bevel_groove, Single_V_groove, Double_V_groove;
    Double thickness_number, current, voltage, travel_speed, heat_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wps);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        drawerToggle = setupDrawerToggle();
        setSupportActionBar(toolbar);
        setupDrawerContent(nvDrawer);
        back_btn = (ImageView) findViewById(R.id.back_btn);
        toolbartxt = (TextView) findViewById(R.id.toolbartxt);
        toolbartxt.setText("WPS");
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        UTS_btn = (Button) findViewById(R.id.uts);
        SharedPreferences sharedPref = getSharedPreferences("parameters", getApplicationContext().MODE_PRIVATE);
        thickness = sharedPref.getString("thickness", "");
        material = sharedPref.getString("material", "");
        position = sharedPref.getString("position", "");
        area_condition = sharedPref.getString("area_condition", "");
        welding_method = sharedPref.getString("welding_method", "");
        Square_joint_groove = sharedPref.getBoolean("Square_joint_groove", false);
        Single_bevel_groove = sharedPref.getBoolean("Single_bevel_groove", false);
        Double_bevel_groove = sharedPref.getBoolean("Double_bevel_groove", false);
        Single_V_groove = sharedPref.getBoolean("Single_V_groove", false);
        Double_V_groove = sharedPref.getBoolean("Double_V_groove", false);
        if (Square_joint_groove)
            groove = "Square Joint Groove";
        else if (Single_bevel_groove)
            groove = "Single Bevel Groove";
        else if (Double_bevel_groove)
            groove = "Double Bevel Groove";
        else if (Single_V_groove)
            groove = "Single V Groove";
        else
            groove = "Double V Groove";

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            process = bundle.getString("process");
        }
        UTS_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WPSActivity.this, UTS_Activity.class);
                intent.putExtra("process", process);
                startActivity(intent);
            }
        });
        Log.d("parameters", thickness + "\n" + material + "\n" + position + "\n" + process + "\n" + area_condition + "\n" + welding_method + "\n" + Square_joint_groove + "\n" + Single_bevel_groove + "\n" + Double_bevel_groove + "\n" + Single_V_groove + "\n" + Double_V_groove);

        thickness_number = Double.parseDouble(thickness);

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

        printTitle(document, preface);
        printHeader(document, process, welding_method); //TODO
        if (Square_joint_groove && (process.equals("smaw") || process.equals("gtaw"))) {
            printJoints_Design(document, "N/A", String.valueOf(thickness_number / 2), "N/A");
        } else if (Square_joint_groove && (process.equals("fcaw") || process.equals("gmaw"))) {
            printJoints_Design(document, "N/A", "3mm", "N/A");
        } else if (Square_joint_groove && process.equals("saw")) {
            printJoints_Design(document, "N/A", "0", "N/A");
        } else if (Single_bevel_groove && (process.equals("smaw") || process.equals("gtaw") || process.equals("fcaw") || process.equals("gmaw"))) {
            printJoints_Design(document, "45", "3mm", "3mm");
        } else if (Single_bevel_groove && process.equals("saw")) {
            printJoints_Design(document, "60", "0", "6mm");
        } else if (Double_bevel_groove && (process.equals("smaw") || process.equals("gtaw") || process.equals("fcaw") || process.equals("gmaw"))) {
            printJoints_Design(document, "45", "3mm", "3mm");
        } else if (Single_V_groove && (process.equals("smaw") || process.equals("gtaw") || process.equals("fcaw") || process.equals("gmaw"))) {
            printJoints_Design(document, "60", "3mm", "3mm");
        } else if (Single_V_groove && (process.equals("saw"))) {
            if (thickness_number >= 10 && thickness_number <= 24.9)
                printJoints_Design(document, "60", "0", "6mm");
            else if (thickness_number >= 25 && thickness_number <= 37.9)
                printJoints_Design(document, "60", "0", "12mm");
            else if (thickness_number >= 38 && thickness_number <= 50)
                printJoints_Design(document, "60", "0", "16mm");
        } else if (Double_V_groove && (process.equals("smaw") || process.equals("gtaw") || process.equals("fcaw") || process.equals("gmaw"))) {
            printJoints_Design(document, "60", "3mm", "3mm");
        } else if (Double_V_groove && (process.equals("saw"))) {
            printJoints_Design(document, "60", "0", "6mm");
        }


        printBase_Metal(document, material, thickness, groove);


        if (process.equals("smaw")) {
            if (material.equals("ST37"))
                printFiller_Metal(document, "E6010", "3.125 mm", "yes");
            else if (material.equals("ST44") || material.equals("ST52"))
                printFiller_Metal(document, "E7018", "3.125 mm", "yes");
        } else if (process.equals("gmaw") || process.equals("gtaw"))
            printFiller_Metal(document, "ER70S-6", "1.2 mm", "yes");
        else if (process.equals("fcaw"))
            printFiller_Metal(document, "E71T-11", "1.6 mm", "yes");
        else if (process.equals("saw")) {
            if (material.equals("ST37"))
                printFiller_Metal(document, "F6A2-EL12", "6 mm", "yes");
            else if (material.equals("ST44") || material.equals("ST52"))
                printFiller_Metal(document, "F7A2-EL12", "6 mm", "yes");
        }


        if (position.charAt(0) == '3')
            printPosition(document, "3G - Vertical", position.substring(14));
        else
            printPosition(document, position, "N/A");

        if (thickness_number < 20)
            printPreheat(document, preface, "N/A");
        else
            printPreheat(document, preface, "90 : 120 °C");


        if (process.equals("smaw") || process.equals("fcaw") || process.equals("saw"))
            printShielding(document, "N/A", "N/A", "N/A");
        else if (process.equals("gtaw"))
            printShielding(document, "Argon", "100% Argon", "8:16 (lit/min)");
        else
            printShielding(document, "CO2", "100% CO2", "8:16 (lit/min)");


        if (process.equals("smaw")) {
            current = 100.0;
            voltage = 26.0;
            travel_speed = 5.0;
            heat_input = Heat_Input(current, voltage, travel_speed);
            printElecterical(document, "DCEP", "100 A", "26 V", "N/A", "5 (mm/sec)", heat_input.toString());
        } else if (process.equals("gmaw")) {
            current = 100.0;
            voltage = 21.0;
            travel_speed = 4.0;
            heat_input = Heat_Input(current, voltage, travel_speed);
            printElecterical(document, "DCEP", "100 A", "21 V", "5.2 (m/min)", "4 (mm/sec)", heat_input.toString());
        } else if (process.equals("gtaw")) {
            current = 100.0;
            voltage = 21.0;
            travel_speed = 2.0;
            heat_input = Heat_Input(current, voltage, travel_speed);
            printElecterical(document, "DCEN", "100 A", "21 V", "N/A", "2 (mm/sec)", heat_input.toString());
        } else if (process.equals("saw")) {
            current = 550.0;
            voltage = 34.0;
            travel_speed = 5.0;
            heat_input = Heat_Input(current, voltage, travel_speed);
            printElecterical(document, "DCEP", "550 A", "34 V", "N/A", "5 (mm/sec)", heat_input.toString());
        } else if (process.equals("fcaw")) {
            if (position.equals("4G - Overhead Groove") || position.equals("3G - Vertical Uphill")) {
                current = 170.0;
                voltage = 16.0;
                travel_speed = 2.0;
                heat_input = Heat_Input(current, voltage, travel_speed);
                printElecterical(document, "DCEN", "170 A", "16 V", "2.3 (m/min)", "2 (mm/sec)", heat_input.toString());
            } else if (position.equals("1G - Flat Groove") || position.equals("2G - Horizontal Groove") || position.equals("3G - vertical Downhill")) {
                current = 250.0;
                voltage = 18.0;
                travel_speed = 2.0;
                heat_input = Heat_Input(current, voltage, travel_speed);
                printElecterical(document, "DCEN", "250 A", "18 V", "2.8 (m/min)", "2 (mm/sec)", heat_input.toString());
            }
        }
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

    public void printTitle(Document document, Paragraph preface) throws DocumentException {
        PdfPTable table_title_1 = new PdfPTable(1);
        table_title_1.setWidthPercentage(100);
        table_title_1.addCell(getCell("WELDING PROCEDURE SPECIFICATION (WPS)", PdfPCell.ALIGN_CENTER, catFont, 8));
        document.add(table_title_1);
        addEmptyLine(preface, 2);
        document.add(preface);
    }

    public void printHeader(Document document, String process, String welding_method) throws DocumentException {
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
        header_table.addCell(getCell(process.toUpperCase(), PdfPCell.ALIGN_MIDDLE, catFont, 8));
        header_table.addCell(getCell("TYPE", PdfPCell.ALIGN_CENTER, catFont, 8));
        header_table.addCell(getCell(welding_method, PdfPCell.ALIGN_RIGHT, catFont, 8));
        header_table.completeRow();

        document.add(header_table);
        Chunk linebreak1 = new Chunk(new LineSeparator());
        document.add(linebreak1);
    }

    public void printJoints_Design(Document document, String alpha, String r, String f) throws DocumentException {      //TODO Add photo as a parameter
        PdfPTable table_title_2 = new PdfPTable(1);
        table_title_2.setWidthPercentage(100);
        table_title_2.addCell(getCell("JOINTS DESIGN", PdfPCell.ALIGN_CENTER, catFont, 20));
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
        table_joints_design.addCell(getCell(alpha, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();
        table_joints_design.addCell(getCell("ROOT OPENING(R)", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell(r, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();
        table_joints_design.addCell(getCell("ROOT FACE (F)", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_joints_design.addCell(getCell(f, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_joints_design.completeRow();

        document.add(table_joints_design);
        Chunk linebreak2 = new Chunk(new LineSeparator());
        document.add(linebreak2);

    }

    public void printBase_Metal(Document document, String material, String thickness, String groove) throws DocumentException {
        PdfPTable table_title_3 = new PdfPTable(1);
        table_title_3.setWidthPercentage(100);
        table_title_3.addCell(getCell("BASE METAL", PdfPCell.ALIGN_CENTER, catFont, 20));
        document.add(table_title_3);

        PdfPTable table_base_metal = new PdfPTable(2);
        table_base_metal.setWidthPercentage(100);

        table_base_metal.addCell(getCell("MATERIAL 1", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell(material, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();
        table_base_metal.addCell(getCell("MATERIAL 2", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell(material, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();
        table_base_metal.addCell(getCell("THICKNESS", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell(thickness, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();
        table_base_metal.addCell(getCell("GROOVE", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_base_metal.addCell(getCell(groove, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_base_metal.completeRow();


        document.add(table_base_metal);
        Chunk linebreak3 = new Chunk(new LineSeparator());
        document.add(linebreak3);

    }

    public void printFiller_Metal(Document document, String electrode_classification, String filler_metal_size, String consumable) throws DocumentException {
        PdfPTable table_title_4 = new PdfPTable(1);
        table_title_4.setWidthPercentage(100);
        table_title_4.addCell(getCell("FILLER METALS", PdfPCell.ALIGN_CENTER, catFont, 20));
        document.add(table_title_4);

        PdfPTable table_filler_metal = new PdfPTable(2);
        table_filler_metal.setWidthPercentage(100);

        table_filler_metal.addCell(getCell("Electrode CLASSIFICATION:", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell(electrode_classification, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("FILLER METAL SIZE:", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell(filler_metal_size, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("CONSUMABLE:", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell(consumable, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("ELECTRODE-FLUX (CLASS):", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("N/A", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("FLUX TRADE NAME", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("N/A", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();
        table_filler_metal.addCell(getCell("OTHER", PdfPCell.ALIGN_LEFT, catFont, 8));
        table_filler_metal.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        table_filler_metal.completeRow();


        document.add(table_filler_metal);
        Chunk linebreak4 = new Chunk(new LineSeparator());
        document.add(linebreak4);
    }

    public void printPosition(Document document, String position, String weld_progression) throws DocumentException {
        PdfPTable table_title_5 = new PdfPTable(1);
        table_title_5.setWidthPercentage(100);
        table_title_5.addCell(getCell("POSITION", PdfPCell.ALIGN_CENTER, catFont, 20));
        document.add(table_title_5);

        PdfPTable position_table = new PdfPTable(4);
        position_table.setWidthPercentage(100);
        position_table.addCell(getCell("POSITION(S) OF GROOVE", PdfPCell.ALIGN_LEFT, catFont, 8));
        position_table.addCell(getCell(position, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        position_table.addCell(getCell("WELD PROGRESSION", PdfPCell.ALIGN_CENTER, catFont, 0));
        position_table.addCell(getCell(weld_progression, PdfPCell.ALIGN_RIGHT, catFont, 8));
        position_table.completeRow();

        document.add(position_table);
        Chunk linebreak5 = new Chunk(new LineSeparator());
        document.add(linebreak5);

    }

    public void printPreheat(Document document, Paragraph preface, String min_preheat_temp) throws DocumentException {
        addEmptyLine(preface, 4);
        document.add(preface);
        PdfPTable table_title_6 = new PdfPTable(1);
        table_title_6.setWidthPercentage(100);
        table_title_6.addCell(getCell("PREHEAT", PdfPCell.ALIGN_CENTER, catFont, 20));
        document.add(table_title_6);

        PdfPTable preheat_table = new PdfPTable(4);
        preheat_table.setWidthPercentage(100);
        preheat_table.addCell(getCell("PREHEAT TEMP. (MINIMUM)", PdfPCell.ALIGN_LEFT, catFont, 8));
        preheat_table.addCell(getCell(min_preheat_temp, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        preheat_table.addCell(getCell("INTERPASS TEMP. (MAXIMUM)", PdfPCell.ALIGN_CENTER, catFont, 0));
        preheat_table.addCell(getCell("200", PdfPCell.ALIGN_RIGHT, catFont, 8));
        preheat_table.completeRow();

        document.add(preheat_table);
        Chunk linebreak6 = new Chunk(new LineSeparator());
        document.add(linebreak6);
    }

    public void printShielding(Document document, String type_of_gas, String gas_composition, String flow_rate) throws DocumentException {
        PdfPTable table_title_7 = new PdfPTable(1);
        table_title_7.setWidthPercentage(100);
        table_title_7.addCell(getCell("SHIELDING", PdfPCell.ALIGN_CENTER, catFont, 20));
        document.add(table_title_7);

        PdfPTable shielding_table = new PdfPTable(2);
        shielding_table.setWidthPercentage(100);
        shielding_table.addCell(getCell("GAS", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell("", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();
        shielding_table.addCell(getCell("TYPE OF GAS :", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell(type_of_gas, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();
        shielding_table.addCell(getCell("GAS COMPOSITION:", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell(gas_composition, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();
        shielding_table.addCell(getCell("FLOW RATE(LIT/MIN):", PdfPCell.ALIGN_LEFT, catFont, 8));
        shielding_table.addCell(getCell(flow_rate, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        shielding_table.completeRow();

        document.add(shielding_table);
        Chunk linebreak7 = new Chunk(new LineSeparator());
        document.add(linebreak7);
    }

    public void printElecterical(Document document, String current_type, String current, String voltage, String wire_feed_speed, String travel_speed, String heat_input) throws DocumentException {
        PdfPTable table_title_8 = new PdfPTable(1);
        table_title_8.setWidthPercentage(100);

        table_title_8.addCell(getCell("ELECTRICAL CHARACTERISTICS", PdfPCell.ALIGN_CENTER, catFont, 20));
        document.add(table_title_8);

        PdfPTable electerical_table1 = new PdfPTable(2);
        electerical_table1.setWidthPercentage(100);
        electerical_table1.addCell(getCell("CURRENT TYPE AND POLARITY", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table1.addCell(getCell(current_type, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table1.completeRow();
        document.add(electerical_table1);

        PdfPTable electerical_table = new PdfPTable(4);
        electerical_table.setWidthPercentage(100);
        electerical_table.addCell(getCell("CURRENT:", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table.addCell(getCell(current, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table.addCell(getCell("VOLTAGE:", PdfPCell.ALIGN_CENTER, catFont, 0));
        electerical_table.addCell(getCell(voltage, PdfPCell.ALIGN_RIGHT, catFont, 8));
        electerical_table.completeRow();
        document.add(electerical_table);


        PdfPTable electerical_table2 = new PdfPTable(2);
        electerical_table2.setWidthPercentage(100);
        electerical_table.addCell(getCell("CURRENT TYPE AND POLARITY", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table.addCell(getCell(current_type, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();

        electerical_table2.addCell(getCell("WIRE FEED SPEED:", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell(wire_feed_speed, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        electerical_table2.addCell(getCell("TRAVEL SPEED:", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell(travel_speed, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        electerical_table2.addCell(getCell("HEAT INPUT:", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell(heat_input, PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        electerical_table2.addCell(getCell("OTHER", PdfPCell.ALIGN_LEFT, catFont, 8));
        electerical_table2.addCell(getCell("___________", PdfPCell.ALIGN_MIDDLE, catFont, 8));
        electerical_table2.completeRow();
        document.add(electerical_table2);


        Chunk linebreak8 = new Chunk(new LineSeparator());
        document.add(linebreak8);

    }

    private Double Heat_Input(Double current, Double voltage, Double travel_speed) {
        return (voltage * current * 0.9 * 1000) / travel_speed;
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                Intent i2 = new Intent(this, MainActivity.class);
                startActivity(i2);
                break;
            case R.id.nav_second_fragment:
                Intent i = new Intent(this, AboutUsActivity.class);
                startActivity(i);
                break;
            case R.id.nav_third_fragment:
                Intent logout = new Intent(this, MainActivity.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(logout);
                finish();
                moveTaskToBack(true);
                break;
        }
        menuItem.setChecked(true);
        mDrawer.closeDrawers();
    }

}