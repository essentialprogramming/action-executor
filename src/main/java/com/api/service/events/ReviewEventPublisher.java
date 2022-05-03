package com.api.service.events;

import com.actions.model.ActionCompleteEvent;
import com.api.actions.ActionType;
import com.api.entities.Story;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ReviewEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishActionReviewEvent(final Story story) {

        log.info("Pull request review finished for story {}", story.getName());
        ActionCompleteEvent<Story> actionCompleteEvent = new ActionCompleteEvent<>(this, ActionType.SEND_PULL_REQUEST_EVENT, story);
        applicationEventPublisher.publishEvent(actionCompleteEvent);
    }
}
