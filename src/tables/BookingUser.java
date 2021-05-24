package tables;

import java.util.Objects;

public class BookingUser {

    private Booking booking;
    private User user;

    public BookingUser() {
    }

    public BookingUser(Booking booking, User user) {
        this.booking = booking;
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingUser that = (BookingUser) o;
        return Objects.equals(booking, that.booking) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking, user);
    }
}
