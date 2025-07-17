package com.ivoyant.customer_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @NotBlank private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank private String lastName;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank private String email;

    private String phone;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
}
