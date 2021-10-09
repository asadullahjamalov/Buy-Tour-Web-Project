package com.example.buytourwebproject.util;

import com.example.buytourwebproject.enums.ArchiveStatus;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.models.RequestStatus;
import com.example.buytourwebproject.repository.AgentRepo;
import com.example.buytourwebproject.repository.RequestRepo;
import com.example.buytourwebproject.repository.RequestStatusRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduleUtil {
    private final RequestRepo requestRepo;
    private final RequestStatusRepo requestStatusRepo;
    private final AgentRepo agentRepo;

    public ScheduleUtil(RequestRepo requestRepo, RequestStatusRepo requestStatusRepo, AgentRepo agentRepo) {
        this.requestRepo = requestRepo;
        this.requestStatusRepo = requestStatusRepo;
        this.agentRepo = agentRepo;
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void ExpireSchedule() {
        for (Request request : requestRepo.getRequestByIsExpiredFalse()) {
            if (request.getExpireDate().isBefore(LocalDateTime.now())) {
                requestRepo.deactivateRequestByRequestId(request.getId());
            }
        }
    }

    @Scheduled(fixedRate = 1000 * 60)
    public void ArchiveSchedule() {
        for (RequestStatus requestStatus : requestStatusRepo.findAll()) {
            if (requestStatus.getRequest().getIsExpired() == true) {
                requestStatusRepo.changeRequestArchiveStatusByAgentAndRequestId(ArchiveStatus.ARCHIVED,
                        requestStatus.getAgent(), requestStatus.getRequest().getId());
            }
        }
    }


}
