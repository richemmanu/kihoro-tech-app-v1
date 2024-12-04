package com.kcbgroup.kihoro_tech_app_v1.models.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Setter
@Getter
@AllArgsConstructor
public class ProjectReq {
    @NotBlank(message = "Project name is required")
    @Size(max = 100)
    private String name;
    private String description;
}
