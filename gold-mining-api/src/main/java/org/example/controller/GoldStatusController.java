package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.request.GoldStatusRequest;
import org.example.response.GoldStatusResponse;
import org.example.service.GoldStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/gold-status")
public class GoldStatusController {
    private final GoldStatusService goldStatusService;

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
