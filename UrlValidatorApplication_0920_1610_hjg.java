// 代码生成时间: 2025-09-20 16:10:45
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
# 扩展功能模块
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
# FIXME: 处理边界情况
import org.springframework.web.bind.annotation.RestController;
# 改进用户体验
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
# 扩展功能模块
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.net.URL;

@SpringBootApplication
public class UrlValidatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlValidatorApplication.class, args);
    }
}

@RestController
class UrlValidationController {

    private final RestTemplate restTemplate;

    public UrlValidationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/validate-url")
    public String validateUrl(@RequestParam String url) {
        try {
            new URL(url).toURI();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return "URL is Valid: " + url;
            } else {
                return "URL is Invalid: " + url;
            }
        } catch (Exception e) {
            return "URL is Invalid or Malformed: " + url;
# NOTE: 重要实现细节
        }
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "An error occurred: " + e.getMessage();
    }
}
