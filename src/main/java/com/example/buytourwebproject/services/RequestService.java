package com.example.buytourwebproject.services;

import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.models.RequestStatus;
import com.example.buytourwebproject.repositories.RequestRepo;
import com.example.buytourwebproject.repositories.RequestStatusRepo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class RequestService {

    private RequestStatusRepo requestStatusRepo;

    public RequestService(RequestStatusRepo requestStatusRepo) {
        this.requestStatusRepo = requestStatusRepo;
    }

    public List<Request> getOfferedRequests(Agent agent){
       return requestStatusRepo.getRequestsByTypeAndAgent(RequestType.OFFERED, agent);
    }

    public List<Request> getArchivedRequests(Agent agent){
        return requestStatusRepo.getRequestsByTypeAndAgent(RequestType.EXPIRED, agent);
    }
}
