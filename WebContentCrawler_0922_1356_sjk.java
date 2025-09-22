// 代码生成时间: 2025-09-22 13:56:10
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

@SpringBootApplication
@RestController
public class WebContentCrawler {

    private final RestTemplate restTemplate;

    public WebContentCrawler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/crawl")
    public ResponseEntity<String> crawlWebsite(@RequestParam String url) {
        try {
            // 使用RestTemplate发起GET请求
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return new ResponseEntity<>(response.getBody(), response.getStatusCode());
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>("Client error: " + e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        } catch (RestClientException e) {
            return new ResponseEntity<>("Server error: " + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void main(String[] args) {
        SpringApplication.run(WebContentCrawler.class, args);
    }
}
