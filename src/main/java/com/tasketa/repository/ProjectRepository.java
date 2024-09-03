package com.tasketa.repository;

import com.tasketa.model.Project;
import com.tasketa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

//    List<Project> findByOwner(User user);

    List<Project> findByNameContainingAndTeamContains(String partialName,User user);

//    @Query("SELECT p FROM Project p JOIN p.team WHERE t:=user")
//    List<Project> findProjectByTeam(@Param("user") User user);

    List<Project> findByTeamContainingOrOwner(User user, User owner);


}
