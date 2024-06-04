package com.elvfan.pjc_test.repository;

import com.elvfan.pjc_test.entity.PjcTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PjcTestRepository extends JpaRepository<PjcTest, Long> {
}
