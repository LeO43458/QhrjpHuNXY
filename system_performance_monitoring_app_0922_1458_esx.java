// 代码生成时间: 2025-09-22 14:58:26
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/monitoring")
public class SystemPerformanceMonitoringApp extends ResponseEntityExceptionHandler {

    @GetMapping("/cpu")
    public Map<String, String> cpuLoad() {
        // Simulate CPU load
        Map<String, String> cpuInfo = new HashMap<>();
        cpuInfo.put("cpuLoad", "Load: 25%");
        return cpuInfo;
    }

    @GetMapping("/memory")
    public Map<String, String> memoryUsage() {
        // Simulate memory usage
        Map<String, String> memoryInfo = new HashMap<>();
        memoryInfo.put("memoryUsage", "Used: 80%");
        return memoryInfo;
    }

    @GetMapping("/disk")
    public Map<String, String> diskSpace() {
        // Simulate disk space
        Map<String, String> diskInfo = new HashMap<>();
        diskInfo.put("diskSpace", "Free: 10GB");
        return diskInfo;
    }

    // Exception handling
    public ResponseEntity<Object> handleException(RuntimeException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemPerformanceMonitoringApp.class, args);
    }
}
