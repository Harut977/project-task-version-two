package com.task.api.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {


    @NotBlank(message = "Title should not be blank")
    @Size(max = 350, message = "Title has not to be  more than 350 ")
    private String title;

    @Min(value = 0, message = "status has to be more than or equal to 0")
    private int status;


}
