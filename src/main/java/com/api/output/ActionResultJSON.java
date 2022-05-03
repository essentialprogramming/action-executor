package com.api.output;

import com.actions.model.ActionStatus;
import com.actions.utils.model.Failure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActionResultJSON implements Serializable {

    private StoryJSON story;
    private Failure failure;
    private ActionStatus status;
}
