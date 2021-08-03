package com.example.buytourwebproject.repositories;

import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepo extends JpaRepository<Request, Long> {

    Request getRequestById(Long id);

    Request getRequestByUuid(String uuid);

    @Query("select r from Request r where r.isExpired=false")
    List<Request> getRequestByIsExpiredFalse();

    @Query("select r from Request r where r.isExpired=true")
    List<Request> getExpiredRequests();

    @Modifying
    @Transactional
    @Query("update Request r set r.isExpired=true where r.uuid= :uuid")
    void deactivateRequestByUuid(String uuid);

    @Modifying
    @Transactional
    @Query("update Request r set r.isExpired=true where r.id=:requestId")
    void deactivateRequestByRequestId(Long requestId);

    @Query("select r.uuid from Request r where r.id=:requestId")
    String getUuidByRequestId(Long requestId);
}
