package com.jsp.theGreenCorner.Contorller;

import com.jsp.theGreenCorner.Services.UPIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upi")
public class UPIController {

    private final UPIService upiService;

    public UPIController(UPIService upiService) {
        this.upiService = upiService;
    }

    // GET http://localhost:8080/upi/generate?amount=350
    @GetMapping("/generate")
    public ResponseEntity<String> generateUPILink(@RequestParam double amount) {
        return ResponseEntity.ok(upiService.generateUPILink(amount));
    }
}
