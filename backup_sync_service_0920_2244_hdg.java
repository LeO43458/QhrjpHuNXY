// 代码生成时间: 2025-09-20 22:44:11
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class BackupSyncService {

    private static final String SOURCE_PATH = "/path/to/source";
    private static final String BACKUP_PATH = "/path/to/backup";

    @GetMapping("/backup")
    public ResponseEntity<String> backupFiles() {
        try {
            Files.walk(Paths.get(SOURCE_PATH)).forEach(source -> {
                Path target = Paths.get(BACKUP_PATH, source.toString().replaceFirst(SOURCE_PATH, ""));
                try {
                    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            return ResponseEntity.ok("Backup completed successfully.");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error during backup process: " + e.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.badRequest().body("An error occurred: " + e.getMessage());
    }

    public static void main(String[] args) {
        SpringApplication.run(BackupSyncService.class, args);
    }
}
