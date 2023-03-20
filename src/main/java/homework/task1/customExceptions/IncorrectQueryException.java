package homework.task1.customExceptions;

public class IncorrectQueryException extends Exception {
    private String incorrectArgument;

    public IncorrectQueryException(String message, String incorrectArgument) {
        super(message);
        this.incorrectArgument = incorrectArgument;
    }

    public String getIncorrectArgument() {
        return incorrectArgument;
    }
}
