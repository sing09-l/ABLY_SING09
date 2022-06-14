package com.api_sing.sing09.repository;

import com.api_sing.sing09.domain.Member;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByName(String name);

}
