package com.example.buytourwebproject.repositories;

import com.example.buytourwebproject.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, Long> {
}
