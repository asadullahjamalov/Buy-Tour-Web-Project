package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.DTOs.AcceptQueueDTO;
import com.example.buytourwebproject.models.AcceptedOfferResponse;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.services.interfaces.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("api/request/")
public class RequestController {
    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping("unarchived")
    public ResponseEntity<List<Request>> getUnarchivedRequests(@RequestHeader("Authorization") String token) {
        log.info("Getting unarchived requests");
        return new ResponseEntity<>(requestService.getUnarchivedRequests(token), HttpStatus.OK);
    }

    @GetMapping("archived")
    public ResponseEntity<List<Request>> getArchivedRequests(@RequestHeader("Authorization") String token) {
        log.info("Getting archived requests");
        return new ResponseEntity<>(requestService.getArchivedRequests(token), HttpStatus.OK);
    }

    @GetMapping("offered")
    public ResponseEntity<List<Request>> getOfferedRequests(@RequestHeader("Authorization") String token) {
        log.info("Getting offered requests");
        return new ResponseEntity<>(requestService.getOfferedRequests(token), HttpStatus.OK);
    }

    @PutMapping("archived/{id}")
    public ResponseEntity<String> setArchived(@RequestHeader("Authorization") String token,
                                              @PathVariable Long id) {
        requestService.setRequestArchived(token, id);
        log.info("Setting archived request by id");
        return new ResponseEntity<>("Request was archived", HttpStatus.OK);
    }

    @GetMapping("accepted")
    public ResponseEntity<List<Request>> getAcceptedRequests(@RequestHeader("Authorization") String token) {
        log.info("Getting accepted requests");
        return new ResponseEntity<>(requestService.getAcceptedRequests(token), HttpStatus.OK);
    }

    @GetMapping("accepted-info/{id}")
    public ResponseEntity<AcceptedOfferResponse> getAcceptedRequestInfo(@RequestHeader("Authorization") String token,
                                                                        @PathVariable Long id) {
        log.info("Getting accepted-info by id");
        return new ResponseEntity<>(requestService.getAcceptedRequestInfo(token, id), HttpStatus.OK);
    }

    @GetMapping("accepted-info-all")
    public ResponseEntity<List<AcceptedOfferResponse>> getAcceptedRequestInfoAll(@RequestHeader("Authorization") String token) {
        log.info("Getting accepted-info");
        return new ResponseEntity<>(requestService.getAcceptedRequestInfoAll(token), HttpStatus.OK);
    }


}
