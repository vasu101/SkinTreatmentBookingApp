import java.util.*;

public class Dermatologist {

    private String dermatologistID;
    private String name;
    private String[] availableDays;
    private Map<String, List<String>> schedule;
    private Map<String, List<String>> bookingsByDate;

    public Dermatologist(String dermatologistID, String name, String[] availableDays) {
        this.dermatologistID = dermatologistID;
        this.name = name;
        this.availableDays = availableDays;
        this.schedule = new HashMap<>();
        this.bookingsByDate = new HashMap<>();
    }

    public boolean bookTimeSlot(String date, String time) {
        List<String> bookedTimes = bookingsByDate.getOrDefault(date, new ArrayList<>());

        if (bookedTimes.contains(time)) {
            return false;
        }
        bookedTimes.add(time);
        bookingsByDate.put(date, bookedTimes);
        return true;
    }

    public String getDermatologistID() {
        return dermatologistID;
    }

    public String getName() {
        return name;
    }

    public List<String> getBookedTimesForDate(String date) {
        return bookingsByDate.getOrDefault(date, new ArrayList<>());
    }
}
