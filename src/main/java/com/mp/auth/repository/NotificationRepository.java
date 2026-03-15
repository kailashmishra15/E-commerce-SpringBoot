package com.mp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mp.auth.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long>{
}