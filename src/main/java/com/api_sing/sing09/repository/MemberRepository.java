package com.api_sing.sing09.repository;

import com.api_sing.sing09.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    List<Member> findAll();
}
