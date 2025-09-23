// 代码生成时间: 2025-09-24 00:43:22
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class ScheduledTasksApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduledTasksApplication.class, args);
    }

    @RestController
    @RequestMapping("/api")
    public class ScheduledTasksController {

        @Scheduled(fixedRate = 5000) // 每5秒执行一次
        public void reportCurrentTime() {
            System.out.println("Current time: " + new Date());
        }

        @GetMapping("/time")
        public String getCurrentTime() {
            return "Current time: " + new Date();
        }
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
            return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
