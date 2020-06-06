package by.epam.javatraining.restaurant.util;

import by.epam.javatraining.restaurant.entity.Bill;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocxBillSaver {
    private static final Logger LOGGER = LogManager.getLogger(DocxBillSaver.class);

    private DocxBillSaver() {
    }

    private static class DocxBillSaverHolder {
        private static final DocxBillSaver INSTANCE = new DocxBillSaver();
    }

    public static DocxBillSaver getInstance() {
        return DocxBillSaverHolder.INSTANCE;
    }

    public void createDocxBill(Bill bill) {
        try (FileOutputStream stream = new FileOutputStream(new File("file123.docx"));
             XWPFDocument document = new XWPFDocument()) {

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(bill.toString());
            document.write(stream);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
