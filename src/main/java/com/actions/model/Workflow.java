package com.actions.model;


import com.api.output.ExecutionHistoryJSON;

import java.util.List;

public interface Workflow<T> {

    /**
     * Executes the workflow. The workflow either starts where it left off or from the beginning.
     *
     * The method returns an execution history.
     * It should contain every action executed in the workflow. If the workflow fails before it reaches a
     * certain action it isn't part of the execution history. Only action which were executed are part of it.
     *
     * @return an execution history
     */
    ExecutionHistoryJSON execute(final T target);

    /**
     * Checks if the passed action is part of the actions in the workflow.
     *
     * @param action action name
     * @return if action is part of action names
     */
    default boolean isActionPartOfWorkflow(final String action) {
        return getActionNames().contains(action);
    }

    /**
     * Contains all action names that are part of the workflow.
     *
     * @return a list with action names
     */
    List<String> getActionNames();
}
