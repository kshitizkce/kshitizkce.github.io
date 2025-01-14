package com.momobowl.projectMomo.ContactUs.repositories;

import com.momobowl.projectMomo.ContactUs.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {
    // Call the stored procedure
    @Procedure(procedureName = "InsertContactUsMessage")
    String insertContactUsMessage(
            @Param("name") String name,
            @Param("email") String email,
            @Param("message") String message
    );
}