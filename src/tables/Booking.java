package tables;

import java.util.Objects;

public class Booking {

    private Integer id;
    private Boolean isActive;
    private String confirmationCode;

    public Booking() {
    }

    public Booking(Boolean isActive, String confirmationCode) {
        this.id = id;
        this.isActive = isActive;
        this.confirmationCode = confirmationCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActive() {
        if (isActive) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setActive(Integer active) {
        if (active == 1) {
            isActive = true;
        } else {
            isActive = false;
        }
    }

    public void setActive(Boolean active) {
        this.isActive = active;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
