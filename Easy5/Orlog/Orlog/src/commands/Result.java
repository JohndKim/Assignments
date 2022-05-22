package commands;

/**
 * A class representing a result of an executed command
 *
 * @author kayak
 * @version 1.0
 */
public class Result {
    private final ResultType resultType;
    private final String resultMessage;

    /**
     * The constructor used for the quit command
     *
     * @param resultType result type
     */
    public Result(ResultType resultType) {
        this.resultType = resultType;
        this.resultMessage = null;
    }

    /**
     * Initializes result type and result message of an executed command
     *
     * @param resultType result type
     * @param resultMessage resul message
     */
    public Result(ResultType resultType, String resultMessage) {
        this.resultType = resultType;
        this.resultMessage = resultMessage;
    }

    /**
     * An enum containing possible resul types
     */
    public enum ResultType {
        /**
         * Succeeded in executing
         */
        SUCCESS,
        /**
         * Failed to execute
         */
        FAILURE
    }

    /**
     * Gets result type
     *
     * @return result type
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * Gets the result message of a result
     *
     * @return result message
     */
    public String getResultMessage() {
        return resultMessage;
    }
}
