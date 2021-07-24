package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.services.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/request/")
@RequiredArgsConstructor
public class RequestController {
    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("create")
    public ResponseEntity<?> createRequest(){
        return null;
//            return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("get-archived")
    public ResponseEntity<?> getArchivedRequests(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }

    @PutMapping("set-archived")
    public ResponseEntity<?> setArchived(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }

    @GetMapping("get-offered")
    public ResponseEntity<?> getOfferedRequests(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }

    @PutMapping("set-offered")
    public ResponseEntity<?> setOffered(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }

    @PutMapping("set-accepted")
    public ResponseEntity<?> setAccepted(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }


}
