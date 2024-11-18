import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DermatologistManager {
    private List<Dermatologist> dermatologists;

    public DermatologistManager() {
        this.dermatologists = new ArrayList<>();
        initializeDermatologists();
    }

    private void initializeDermatologists() {
        dermatologists.add(new Dermatologist("D001", "Dr. Smith", new String[]{"Monday", "Wednesday"}));
        dermatologists.add(new Dermatologist("D002", "Dr. Johnson", new String[]{"Friday", "Saturday"}));
        dermatologists.add(new Dermatologist("D003", "Dr. Lee", new String[]{"Tuesday", "Thursday"}));
        dermatologists.add(new Dermatologist("D004", "Dr. Patel", new String[]{"Monday", "Friday"}));
    }

    public Dermatologist getDermatologistByName(String name) {
        for (Dermatologist d : dermatologists) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }

    public List<String> getAvailableTimes(String dermatologistName, String date) {
        Dermatologist dermatologist = getDermatologistByName(dermatologistName);
        if (dermatologist == null) return List.of();

        List<String> allTimeSlots = List.of("10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM");
        List<String> bookedTimes = dermatologist.getBookedTimesForDate(date);

        return allTimeSlots.stream()
                .filter(time -> !bookedTimes.contains(time))
                .collect(Collectors.toList());
    }

}
