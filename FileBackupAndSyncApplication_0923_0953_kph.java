// 代码生成时间: 2025-09-23 09:53:06
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
@RestController
@RequestMapping("/api/backup")
public class FileBackupAndSyncApplication {

    private static final String BACKUP_DIRECTORY = "./backup";

    public static void main(String[] args) {
        SpringApplication.run(FileBackupAndSyncApplication.class, args);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                Path destinationPath = Paths.get(BACKUP_DIRECTORY, file.getOriginalFilename());
                Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
                return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a file");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the file: " + e.getMessage());
        }
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("filename") String filename) {
        try {
            Path filePath = Paths.get(BACKUP_DIRECTORY, filename);
            if (Files.exists(filePath)) {
                return ResponseEntity.ok().body(Files.readAllBytes(filePath));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found".getBytes());
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not download the file: " + e.getMessage().getBytes());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}
