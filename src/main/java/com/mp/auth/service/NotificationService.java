package com.mp.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mp.auth.model.Notification;
import com.mp.auth.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository repo;

    public Notification create(Notification notification){
        return repo.save(notification);
    }

    public List<Notification> getAll(){
        return repo.findAll();
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
}