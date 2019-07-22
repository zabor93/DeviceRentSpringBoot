package pl.javastart.devicerentboot.app;

public class InvalidOptionException extends RuntimeException {
    InvalidOptionException(){
        super("Opcja nie istnieje");
    }
}
