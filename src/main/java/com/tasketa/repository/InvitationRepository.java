package com.tasketa.repository;

import com.tasketa.model.Invitation;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.apache.el.lang.ELArithmetic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    public Invitation findByToken(String token);

    public Invitation findByEmail(String userEmail);

}
