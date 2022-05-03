package com.api.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionHistoryJSON implements Serializable {

    private String storyKey;
    private List<ExecutionStepJSON> executionSteps;
}
