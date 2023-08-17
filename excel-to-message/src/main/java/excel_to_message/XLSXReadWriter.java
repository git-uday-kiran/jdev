package excel_to_message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static excel_to_message.Utils.check;
import static excel_to_message.Utils.not;

public class XLSXReadWriter {

    private static final String NEW_LINE = "\n";
    private static final Logger LOGGER = LogManager.getLogger(XLSXReadWriter.class);
    private static final List<String> languages = Arrays.asList("Hindi", "Bengali", "Gujarati", "Marathi", "Tamil", "Telugu", "Kannada", "Malayalam");

    public static InputStream read(final InputStream inputStream, int size) throws IOException {
        final StringJoiner content = new StringJoiner(NEW_LINE);
        final Map<String, Integer> languages = new HashMap<>();

        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(inputStream);
        } catch (Exception exception) {
            throw new XLSXReadWriterException("Can't read the data as XSSFWorkbook file", exception);
        } finally {
            inputStream.close();
        }

        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow headerRow = sheet.getRow(0);
        check(headerRow != null, "Header row not found");

        assert headerRow != null;
        for (int cellIndex = 0; cellIndex < headerRow.getLastCellNum(); cellIndex++) {
            final String cellValue = headerRow.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
            if (isLang(cellValue))
                languages.put(cellValue.toLowerCase(), cellIndex);
        }

        LOGGER.info("Found languages {}", languages);
        final String keyNotExistMessage = "first row first  column cell value must be as 'key'";
        check(not(languages.isEmpty()), "No language found on header row, need at least one header.");
        check(headerRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue().equals("key"), keyNotExistMessage);

        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        int rowNum = 2;
        size--;

        while (rowIterator.hasNext() && size > 0) {
            final Row row = rowIterator.next();
            for (final String language : languages.keySet()) {

                final int cellIndex = languages.get(language);
                final String key = row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                final String value = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();

                check(not(key.isEmpty()), "key name can't be empty at the row number " + rowNum);
                check(not(value.isEmpty()), "value can't be empty at row number " + rowNum + " at cell number " + (cellIndex + 1));

                content.add(key + "_" + language + " = " + value);
            }
            content.add(NEW_LINE);
            rowNum++;
            size--;
        }

        workbook.close();
        return new ByteArrayInputStream(content.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static boolean isLang(final String lang) {
        return languages.stream().anyMatch(e -> e.equalsIgnoreCase(lang));
    }


    static class XLSXReadWriterException extends RuntimeException {

        public XLSXReadWriterException(String message) {
            super(message);
        }

        public XLSXReadWriterException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
