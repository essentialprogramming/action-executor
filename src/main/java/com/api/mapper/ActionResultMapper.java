package com.api.mapper;

import com.actions.model.ActionResult;
import com.api.entities.Story;
import com.api.output.ActionResultJSON;
import com.api.output.StoryJSON;

public class ActionResultMapper {

    public static ActionResultJSON entityToJSON(final ActionResult<Story> actionResult) {
        return ActionResultJSON.builder()
                .story(toStoryJSON(actionResult.getValue()))
                .failure(actionResult.getFailure())
                .status(actionResult.getStatus())
                .build();
    }

    private static StoryJSON toStoryJSON(final Story story) {
        return story != null
                ? StoryMapper.entityToJSON(story)
                : null;
    }
}
