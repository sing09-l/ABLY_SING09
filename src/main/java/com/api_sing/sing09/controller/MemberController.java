package com.api_sing.sing09.controller;

import com.api_sing.sing09.repository.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService service;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/members")
    public ResponseEntity<?> members() {
        List members = service.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

}
