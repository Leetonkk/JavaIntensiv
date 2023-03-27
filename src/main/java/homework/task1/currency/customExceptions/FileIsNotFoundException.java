package homework.task1.currency.customExceptions;

public class FileIsNotFoundException extends RuntimeException {
    public FileIsNotFoundException(String message){
        super(message);
    }
}
