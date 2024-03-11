package com.test.web.repository;

import com.test.web.enitiy.Audit;
import com.test.web.enitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

}
