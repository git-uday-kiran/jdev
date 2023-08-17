package draw.bytes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class XLSXReadWriter {

    private static final String NEW_LINE = "\n";
    private static final Logger LOGGER = LogManager.getLogger(XLSXReadWriter.class);
    private static final List<String> languages = List.of("Hindi", "Bengali", "Gujarati", "Marathi", "Tamil", "Telugu", "Kannada", "Malayalam");

    public static void main(String[] args) throws IOException {
        System.out.println("Hello...");
        read();
        System.out.println("Completed..");
    }

    public static void read() throws IOException {
        final String path = "C:\\Users\\uday.mekala\\Desktop\\test-vernacular.xlsx";
        final InputStream inputStream = new FileInputStream(new File(path));

        byte[] bytes = read(inputStream);

        System.out.println(new String(bytes));

    }

    public static byte[] read(final InputStream inputStream) throws XLSXReadWriterException, IOException {
        final StringJoiner content = new StringJoiner(NEW_LINE);
        final Map<String, Integer> languages = new HashMap<>();
        final String key = "key";

        int from = 1, to = 23;

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFRow headerRow = sheet.getRow(0);
        Objects.requireNonNull(headerRow, "header row not found");

        for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
            final String cellValue = headerRow.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            System.out.println("index : " + cellIndex + " data '" + cellValue + "'");
            if (isLang(cellValue))
                languages.put(cellValue.toLowerCase(), cellIndex);
        }


        LOGGER.info("Found languages {}", languages);
        System.out.println("found languages " + languages);

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        from++;

        while (rowIterator.hasNext() && from <= to) {
            final Row row = rowIterator.next();
            for (final String language : languages.keySet()) {
                final String keyName = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK) + "_" + language;
                final int cellIndex = languages.get(language);
                final String keyValue = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                content.add(keyName + " = " + keyValue);
            }
            content.add(NEW_LINE);
            from++;
        }

//        LOGGER.info("Content :: {}", content);
        return content.toString().getBytes(StandardCharsets.UTF_8);
    }

    public static boolean isLang(final String lang) {
        return languages.stream().anyMatch(e -> e.equalsIgnoreCase(lang));
    }

    class XLSXReadWriterException extends RuntimeException {

        public XLSXReadWriterException(String message) {
            super(message);
        }

        public XLSXReadWriterException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
