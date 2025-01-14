package com.momobowl.projectMomo.ContactUs.service;

import com.momobowl.projectMomo.ContactUs.repositories.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    public String createContactUsMessage(String name, String email, String message, String username) {

        if (!username.equals(email)) {
            throw new RuntimeException("Unauthorized: Email mismatch.You are only authorized to send message using logged in email which is:" + username);
        }

        return contactUsRepository.insertContactUsMessage(name, email, message);
    }
}
