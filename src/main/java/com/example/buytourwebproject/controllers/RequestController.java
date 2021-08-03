package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.DTOs.AcceptQueueDTO;
import com.example.buytourwebproject.models.AcceptedOfferResponse;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.services.interfaces.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/request/")
public class RequestController {
    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping("get-unarchived")
    public ResponseEntity<List<Request>> getUnarchivedRequests(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(requestService.getUnarchivedRequests(token), HttpStatus.OK);
    }

    @GetMapping("get-archived")
    public ResponseEntity<List<Request>> getArchivedRequests(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(requestService.getArchivedRequests(token), HttpStatus.OK);
    }

    @GetMapping("get-offered")
    public ResponseEntity<List<Request>> getOfferedRequests(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(requestService.getOfferedRequests(token), HttpStatus.OK);
    }

    @PutMapping("set-archived/{id}")
    public ResponseEntity<String> setArchived(@RequestHeader("Authorization") String token,
                                              @PathVariable Long id) {
        requestService.setRequestArchived(token, id);
        return new ResponseEntity<>("Request was archived", HttpStatus.OK);
    }

    @GetMapping("get-accepted")
    public ResponseEntity<List<Request>> getAcceptedRequests(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(requestService.getAcceptedRequests(token), HttpStatus.OK);
    }

    @GetMapping("get-accepted-info/{id}")
    public ResponseEntity<AcceptedOfferResponse> getAcceptedRequestInfo(@RequestHeader("Authorization") String token,
                                                                        @PathVariable Long id) {
        return new ResponseEntity<>(requestService.getAcceptedRequestInfo(token, id), HttpStatus.OK);
    }


}
