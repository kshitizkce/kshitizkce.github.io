package com.momobowl.projectMomo.ContactUs.model;

import com.momobowl.projectMomo.users.model.Users;
import jakarta.persistence.*;

@Entity
@NamedStoredProcedureQuery(
        name = "InsertContactUsMessage",
        procedureName = "InsertContactUsMessage",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "name"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "email"),
                @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "message"),
                @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "response") // Output parameter
        }
)
public class ContactUs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Users users;  // Relationship with User entity

    private String name;
    private String email;
    private String message;

    // Getters and Setters
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users user) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
