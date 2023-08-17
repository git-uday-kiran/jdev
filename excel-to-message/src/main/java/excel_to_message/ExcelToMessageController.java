package excel_to_message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static excel_to_message.Utils.*;

@Controller
@CrossOrigin
@RequestMapping
public class ExcelToMessageController {

    private static final Logger LOGGER = LogManager.getLogger(ExcelToMessageController.class);

    @GetMapping
    public String get() {
        return "index";
    }


    @PostMapping(name = "api", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> read(@RequestParam("file") MultipartFile file, @RequestParam int size) throws IOException {
        final String fileName = file.getOriginalFilename();
        LOGGER.info("Received request, file: {}, size: {}", fileName, size);

        check(fileName.endsWith(".xlsx"), "file is not a type of .xlsx");
        check(size > 0, "size can't be zero or negative");

        InputStream fileInputStream = file.getInputStream();
        InputStreamResource resource = new InputStreamResource(XLSXReadWriter.read(fileInputStream, size));

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public String handler(final Throwable throwable) {
        return throwable.getMessage();
    }

}

