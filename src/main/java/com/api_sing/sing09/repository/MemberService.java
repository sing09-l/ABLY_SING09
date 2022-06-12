package com.api_sing.sing09.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository repository;

    public List findAll() {
        return repository.findAll();
    }

}
