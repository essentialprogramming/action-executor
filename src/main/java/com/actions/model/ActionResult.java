package com.actions.model;

import com.actions.utils.model.Failure;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Holds a value of an action. Allows handling expected and normal failures.
 * Unexpected errors (runtime exceptions) should be thrown through exceptions.
 *
 * @param <T> The type of the enclosed value
 */
@Builder
@Getter
@Setter
public class ActionResult<T> {

    private final T value;
    private final Failure failure;
    private final ActionStatus status;

    public boolean isSuccess() {
        return ActionStatus.SUCCESS.equals(status);
    }

    public boolean isError() {
        return ActionStatus.FAILED.equals(status);
    }

    public static <T> ActionResult<T> success() {
        return ActionResult.<T>builder().status(ActionStatus.SUCCESS).build();
    }

    public static <T> ActionResult<T> waiting() {
        return ActionResult.<T>builder().status(ActionStatus.WAITING).build();
    }

    /**
     * Create a successful ActionResult<T>
     *
     * @param result a mandatory value the value of the action
     * @return a Result<T> with the given value and success = true
     */
    public static <T> ActionResult<T> success(final T result) {
        if (result == null) {
            throw new IllegalArgumentException("value must not be null");
        }
        return ActionResult.<T>builder()
                .status(ActionStatus.SUCCESS)
                .value(result)
                .build();
    }

    public static <T> ActionResult<T> error(final String errorCode, final String errorMessage) {
        return ActionResult.<T>builder()
                .failure(Failure.builder()
                           .code(errorCode)
                           .description(errorMessage)
                           .build())
                .status(ActionStatus.FAILED).build();
    }

    public static <T> ActionResult<T> error(final Failure failure) {
        return ActionResult.<T>builder()
                .failure(failure)
                .status(ActionStatus.FAILED).build();
    }

    /**
     * Result of successful action
     *
     * @return value of <T> if action was successful, null otherwise
     */
    public T getValue() {
        return value;
    }

    /**
     * Result of unsuccessful action
     */
    public Failure getFailure() {
        return failure;
    }

    public String getErrorCode() {
        return failure.getErrorCode();
    }

    public String getErrorMessage() {
        return failure.getDescription();
    }
}
