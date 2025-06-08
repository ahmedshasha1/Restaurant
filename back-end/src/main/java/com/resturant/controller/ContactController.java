package com.resturant.controller;

import com.resturant.dto.ContactInfoDto;
import com.resturant.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @Autowired
    private ContactService contactService;


    @PostMapping("/contact-info")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<String> saveMessage(@RequestBody @Validated ContactInfoDto contactInfoDto){
        return ResponseEntity.ok().body(contactService.saveMessage(contactInfoDto));

    }
}
