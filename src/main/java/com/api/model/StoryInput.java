package com.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoryInput {

    @Schema(example = "`Implement Hello World`")
    @NotNull(message = "Story name can not be null!")
    @JsonProperty("name")
    private String name;

    @Schema(example = "Razvan Prichici")
    @NotNull(message = "Story assignee can not be null!")
    @JsonProperty("assignee")
    private String assignee;

}
