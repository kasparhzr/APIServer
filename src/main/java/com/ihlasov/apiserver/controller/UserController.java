package com.ihlasov.apiserver.controller;

import com.ihlasov.apiserver.dto.ChangeStatusDTO;
import com.ihlasov.apiserver.dto.GetStatusDTO;
import com.ihlasov.apiserver.entity.User;
import com.ihlasov.apiserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @GetMapping("/status")
    public GetStatusDTO getStatus(
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate timestamp) {
        if (Objects.nonNull(timestamp)) {
            return service.getStatus(timestamp);
        } else {
            return service.getStatus();
        }
    }

    @PostMapping("/store")
    public String storeJpg(@RequestParam MultipartFile file) {
        return service.storeJpg(file);
    }

    @PostMapping("/create")
    public Long createUser(
            @RequestParam String uri, @RequestParam String name, @RequestParam String email) {
        return service.createUser(uri, name, email);
    }

    @PostMapping("/changeStatus/{id}/{status}")
    public ChangeStatusDTO changeStatus(@PathVariable Long id, @PathVariable String status) {
        return service.changeStatus(id, status);
    }

}
