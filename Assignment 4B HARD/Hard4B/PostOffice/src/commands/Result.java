package commands;

/**
 * Represents a result of an executed command
 *
 * @author kayak
 * @version 1.0
 */
public class Result {
    private final String resultMessage;
    private final ResultType resultType;

    /**
     * Initializes result type and result message
     *
     * @param resultType type of result
     * @param resultMessage result message
     */
    public Result(ResultType resultType, String resultMessage){
        this.resultMessage = resultMessage;
        this.resultType = resultType;
    }

    /**
     * An enum with all types of results (success or failure)
     */
    public enum ResultType{
        SUCCESS,
        FAILURE;
    }

    /**
     * Gets the result type of result
     *
     * @return result type
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * Gets the result message of result
     *
     * @return result type
     */
    public String getResultMessage() {
        return resultMessage;
    }
}
