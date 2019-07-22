package pl.javastart.devicerentboot.app;

public enum Options {

    ADD_DEVICE(1, "Dodaj urzadzenie"),
    ADD_CATEGORY(2, "Dodaj kategorie"),
    ADD_CUSTOMER(3, "Dodaj klienta"),
    RENT_DEVICE(4, "Wypozycz urzadzenie"),
    REMOVE_DEVICE(5, "Usun urzadzenie"),
    REMOVE_CATEGORY(6, "Usun kategorie"),
    REMOVE_CUSTOMER(7, "Usun klienta"),
    EXIT(8, "Zamknij aplikacjie");

    private int number;
    private String name;

    Options(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return number + "-" + name;
    }

    static Options numberToCategory(int number) {
        if (number < 1 || number > values().length) {
            throw new InvalidOptionException();
        }
        return values()[number - 1];
    }
}
