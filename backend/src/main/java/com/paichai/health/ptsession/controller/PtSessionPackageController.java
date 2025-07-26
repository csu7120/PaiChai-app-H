package com.paichai.health.ptsession.controller;

import com.paichai.health.ptsession.dto.PtSessionPackageRequest;
import com.paichai.health.ptsession.service.PtSessionPackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pt-packages")
@RequiredArgsConstructor
public class PtSessionPackageController {

    private final PtSessionPackageService ptSessionPackageService;

    @PostMapping
    public ResponseEntity<Void> registerPtPackage(@RequestBody PtSessionPackageRequest request) {
        ptSessionPackageService.save(request);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkRegistered(
            @RequestParam Integer trainerId,
            @RequestParam Integer clientId) {
        boolean result = ptSessionPackageService.isAlreadyRegistered(trainerId, clientId);
        return ResponseEntity.ok(result);
    }
}
