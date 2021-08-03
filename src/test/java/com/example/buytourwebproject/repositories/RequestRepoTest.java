package com.example.buytourwebproject.repositories;

import com.example.buytourwebproject.models.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RequestRepoTest {

    @Autowired
    private  RequestRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void getRequestById() {

        Request request1 = Request.builder().id(1l).build();

        underTest.save(request1);

        Request request = underTest.getRequestById(1l);

        assertThat(request).isEqualTo(request1);
    }

    @Test
    void getRequestByUuid() {

        Request request1 = Request.builder().id(1l).uuid("uuid").build();

        underTest.save(request1);

        Request request = underTest.getRequestByUuid("uuid");

        assertThat(request).isEqualTo(request1);
    }

    @Test
    void getRequestByIsExpiredFalse() {

        Request request1 = Request.builder().id(1l).isExpired(false).build();
        underTest.save(request1);

        Request request2 = Request.builder().id(2l).isExpired(false).build();
        underTest.save(request2);

        Request request3 = Request.builder().id(3l).isExpired(true).build();
        underTest.save(request3);

        Request request4 = Request.builder().id(4l).isExpired(true).build();
        underTest.save(request4);

        List<Request> requestList = underTest.getRequestByIsExpiredFalse();

        assertThat(requestList).isEqualTo(Arrays.asList(request1, request2));
    }

    @Test
    void getExpiredRequests() {

        Request request1 = Request.builder().id(1l).isExpired(false).build();
        underTest.save(request1);

        Request request2 = Request.builder().id(2l).isExpired(false).build();
        underTest.save(request2);

        Request request3 = Request.builder().id(3l).isExpired(true).build();
        underTest.save(request3);

        Request request4 = Request.builder().id(4l).isExpired(true).build();
        underTest.save(request4);

        List<Request> requestList = underTest.getExpiredRequests();

        assertThat(requestList).isEqualTo(Arrays.asList(request3, request4));
    }

//    @Test
//    void deactivateRequestByUuid() {
//        Request request1 = Request.builder().uuid("uuid").build();
//        underTest.save(request1);
//
//        underTest.deactivateRequestByUuid("uuid");
//
//        assertThat(request1.getIsExpired()).isTrue();
//
//    }

    @Test
    void deactivateRequestByRequestId() {
    }
}