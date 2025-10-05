// 代码生成时间: 2025-10-06 01:59:24
@SpringBootApplication
public class DocumentCollaborationPlatform {

    public static void main(String[] args) {
        SpringApplication.run(DocumentCollaborationPlatform.class, args);
    }
}

/**
 * REST Controller for Document Collaboration
 */
@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        try {
            return new ResponseEntity<>(documentService.createDocument(document), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(