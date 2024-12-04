package com.kcbgroup.kihoro_tech_app_v1;

import com.kcbgroup.kihoro_tech_app_v1.models.entity.Project;
import com.kcbgroup.kihoro_tech_app_v1.models.repositories.ProjectRepo;
import com.kcbgroup.kihoro_tech_app_v1.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class KihoroTechAppV1ApplicationTests {

	@Mock
	private ProjectRepo projectRepo;

	@InjectMocks
	private ProjectService projectService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testSaveCustomer() {
		// Create a sample project
		Project project = new Project();
		project.setName("Test Project 1");
		project.setDescription("A great Noah's Project");

		when(projectRepo.save(any(Project.class))).thenReturn(project);

		// Action to save the project
		Project savedProject = projectService.saveProject(project);

		// Assert
		assertNotNull(savedProject);
		assertEquals("Test Project 1", savedProject.getName());
		verify(projectRepo, times(1)).save(project);
	}

	@Test
	void testGetProjectById_Found() {
		// Lets create a sample project for mock
		Long projectId = 1L;
		Project project = new Project();
		project.setId(projectId);
		project.setName("KCB Test");
		project.setDescription("A valid interview");

		when(projectRepo.findById(projectId)).thenReturn(Optional.of(project));

		// Get project items
		Project foundProject = projectService.getProjectById(projectId).orElse(null);

		// Assertions list here
		assertNotNull(foundProject);
		assertEquals(projectId, foundProject.getId());
		assertEquals("KCB Test", foundProject.getName());
		verify(projectRepo, times(1)).findById(projectId);
	}

	@Test
	void testGetProjectById_NotFound() {
		// Create a sample project id
		Long projectId = 1L;
		when(projectRepo.findById(projectId)).thenReturn(Optional.empty());

		// Act here to get details of a project
		Project foundProject = projectService.getProjectById(projectId).orElse(null);

		// Assertion here to perform tests
		assertNull(foundProject);
		verify(projectRepo, times(1)).findById(projectId);
	}

}
