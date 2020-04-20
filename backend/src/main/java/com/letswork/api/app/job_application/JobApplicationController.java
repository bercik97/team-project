package com.letswork.api.app.job_application;

import com.letswork.api.app.job_application.domain.JobApplicationFacade;
import com.letswork.api.app.job_application.domain.dto.CreateJobApplicationDto;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/job-applications")
@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
public class JobApplicationController {

    private final JobApplicationFacade facade;

    @PostMapping
    @ApiOperation("Add new job application")
    public void add(@RequestBody CreateJobApplicationDto dto, Authentication authentication) {
        facade.add(dto, authentication.getName());
    }
}
