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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/szs")
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
    @PostMapping("/signup")
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
        if(("홍길동".equals(name) && "860824-1655068".equals(name)) ||
                ("김둘리".equals(name) && "921108-1582816".equals(name)) ||
                ("마징가".equals(name) && "880601-2455116".equals(name)) ||
                ("베지터".equals(name) && "910411-1656116".equals(name)) ||
                ("손오공".equals(name) && "820326-2715702".equals(name)) ){

        }

        return repository.save(member);
    }
    @ApiOperation(value = "유저 정보 스크랩")
    @GetMapping(value = "/scrap")
    public void mainIndexPage() {

        service.callApi();
    }

}
