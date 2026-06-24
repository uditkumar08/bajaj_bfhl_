package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.dto.BfhlRequest;
import com.chitkara.bfhl.dto.BfhlResponse;
import com.chitkara.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<BfhlResponse> process(@RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    @org.springframework.web.bind.annotation.GetMapping("/bfhl")
    public ResponseEntity<java.util.Map<String, Object>> getOperationCode() {
        java.util.Map<String, Object> response = new java.util.HashMap<>();
        response.put("operation_code", 1);
        return ResponseEntity.ok(response);
    }

    @org.springframework.web.bind.annotation.GetMapping("/health")
    public ResponseEntity<java.util.Map<String, String>> getHealth() {
        java.util.Map<String, String> response = new java.util.HashMap<>();
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}
