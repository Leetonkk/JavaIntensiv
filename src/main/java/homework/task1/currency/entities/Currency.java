package homework.task1.currency.entities;

public enum Currency {
    USD,
    EUR,
    TRY,
    ;
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public static Currency fromString(String text) {
        for (Currency c : Currency.values()) {
            if (c.toString().equals(text)) {
                return c;
            }
        }
        return null;
    }


}
