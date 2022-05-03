package com.api.controller;

import com.api.output.ExecutionStepJSON;
import com.api.model.StoryInput;
import com.api.workflow.IssueTrackerWorkflow;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/action")
@RequiredArgsConstructor
@Tag(description = "Action API", name = "Action Services")
public class ActionController {

    private final IssueTrackerWorkflow issueTrackerWorkflow;

    @PostMapping("execute")
    @Operation(summary = "Execute actions", description = "Simulate story start, implementation, pull request, and completion",
                responses = {
                        @ApiResponse(responseCode = "200", description = "Returns 200 if action was successfully completed",
                                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ExecutionStepJSON.class))))
                }
    )
    public List<ExecutionStepJSON> executeAction(@Valid @RequestBody StoryInput storyInput) {
        return issueTrackerWorkflow.executeAction(storyInput);
    }
}
