package com.joywayi.dao;

import com.joywayi.process.ApproveConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ApproveConfigDao extends JpaRepository<ApproveConfig,Long>,
        QuerydslPredicateExecutor<ApproveConfig>, PagingAndSortingRepository<ApproveConfig,Long> {
}
