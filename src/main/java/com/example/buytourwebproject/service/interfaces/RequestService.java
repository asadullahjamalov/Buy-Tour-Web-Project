package com.example.buytourwebproject.service.interfaces;

import com.example.buytourwebproject.models.AcceptedOfferResponse;
import com.example.buytourwebproject.models.Request;

import java.util.List;

public interface RequestService {
    List<Request> getOfferedRequests(String token);

    List<Request> getArchivedRequests(String token);

    List<Request> getUnarchivedRequests(String token);

    List<Request> getAcceptedRequests(String token);

    void setRequestArchived(String token, Long requestId);

    AcceptedOfferResponse getAcceptedRequestInfo(String token, Long requestId);

    List<AcceptedOfferResponse> getAcceptedRequestInfoAll(String token);
}
