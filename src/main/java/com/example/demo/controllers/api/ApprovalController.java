package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.ResponseHandler;
import com.example.demo.models.dto.ApprovalDto;
import com.example.demo.services.ApprovalService;

@RestController
@RequestMapping("api/approval")
public class ApprovalController {
    private ApprovalService approvalService;

    @Autowired
    public ApprovalController(ApprovalService approvalService) {
        this.approvalService = approvalService;
    }

    @GetMapping
    public ResponseEntity<Object> getData(@RequestHeader(name = "token") String token,@RequestParam(name = "action") String action) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        return ResponseHandler.generateResponse("success", HttpStatus.OK,
                approvalService.getAllDataByAction(action));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestHeader(name = "token") String token, @RequestParam Integer id,
        @RequestBody ApprovalDto approvalDto) {
        if (!token.equals("RECRUBATM")) {
            return ResponseHandler.generateResponse("failed", HttpStatus.UNAUTHORIZED, "");
        }
        approvalDto.setUserId(id);
        return ResponseHandler.generateResponse("modified success", HttpStatus.OK,
                approvalService.save(approvalDto));
    }
}
