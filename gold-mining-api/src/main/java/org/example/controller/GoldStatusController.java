package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.request.GoldStatusRequest;
import org.example.response.GoldStatusResponse;
import org.example.service.GoldStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.List;
import java.util.UUID;

@RestController
public class GoldStatusController {
    private InetAddress ip;
    private String uuid;
    private GoldStatusService goldStatusService;

    public GoldStatusController(GoldStatusService goldStatusService) {
        try {
            this.goldStatusService = goldStatusService;
            this.ip = InetAddress.getLocalHost();
            this.uuid = UUID.randomUUID().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public ResponseEntity<String> getIp() {
        return ResponseEntity.ok(this.ip.getHostAddress() + "\n");
    }

    @GetMapping("/gold/uuid")
    public ResponseEntity<String> getUuid() {
        return ResponseEntity.ok(this.uuid + "\n");
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<GoldStatusResponse>> getWorldGoldStatus(@PathVariable String name) {
        List<GoldStatusResponse> goldStatusResponses = goldStatusService.getGoldStatus(name);
        return new ResponseEntity<>(goldStatusResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveWorldGoldStatus(@RequestBody GoldStatusRequest goldStatusRequest) {
        goldStatusService.saveGoldStatus(goldStatusRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
