package com.api_sing.sing09.controller;

import com.api_sing.sing09.domain.Member;
import com.api_sing.sing09.repository.MemberRepository;
import com.api_sing.sing09.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class MemberController {

    //private final MemberService service;
    private final MemberRepository repository;
    private final MemberService service;

    @ApiOperation(value = "전체조회")
    @GetMapping("/users")
    public List<Member> findAllUser() {
        return repository.findAll();
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/users/add")
    public Member save(@ApiParam(value ="회원 id", required = true) @RequestParam String userId,
                     @ApiParam(value ="회원 pw", required = true) @RequestParam String password,
                     @ApiParam(value ="회원 이름", required = true) @RequestParam String name,
                     @ApiParam(value ="회원 주민등록번호", required = true) @RequestParam String regNo
                     ) {
        Member member = Member.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .regNo(regNo)
                .build();

        return repository.save(member);
    }
    @GetMapping("/main")
    public String mainIndexPage() {

        JsonObject param = new JsonObject();
        // POST 방식으로 호출.(GET, POST, PUT, DELETE 다 가능 합니다.)
        service.callApi(param, "POST");

        return "/tiles/view/main";
    }

}
