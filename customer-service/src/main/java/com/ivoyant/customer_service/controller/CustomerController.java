package com.ivoyant.customer_service.controller;

import com.ivoyant.customer_service.entity.Customer;
import com.ivoyant.customer_service.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repo;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable UUID id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) {
        Customer saved = repo.save(customer);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable UUID id, @Valid @RequestBody Customer in) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setFirstName(in.getFirstName());
                    existing.setLastName(in.getLastName());
                    existing.setEmail(in.getEmail());
                    existing.setPhone(in.getPhone());
                    Customer updated = repo.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
