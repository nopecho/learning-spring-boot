package com.zerorock.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zerorock.ex2.entity.Memo;

public interface MemoRepository extends JpaRepository < Memo, Long> {
//
}
