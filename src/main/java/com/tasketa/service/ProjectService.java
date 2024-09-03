package com.tasketa.service;

import com.tasketa.model.Chat;
import com.tasketa.model.Project;
import com.tasketa.model.User;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project,User user) throws Exception;

    List<Project> getProjectsByTeam(User user, String category, String tag) throws Exception ;

    Project getProjectById(Long projectId) throws Exception;

    Project updateProject(Project updatedProject,Long id) throws Exception;

    void deleteProject(Long projectId, Long userId) throws Exception;

    void addUserToProject(Long projectId, Long userId) throws Exception;

    void removeUserFromProject(Long projectId, Long userId) throws Exception ;

    Chat getChatByProjectId(Long projectId) throws Exception;

    List<Project> searchProjects(String keyword, User user);

}
