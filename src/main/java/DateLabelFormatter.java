import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormat.parse(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            if (value instanceof GregorianCalendar) {
                Date date = ((GregorianCalendar) value).getTime();
                return dateFormat.format(date);
            } else if (value instanceof Date) {
                return dateFormat.format((Date) value);
            }
        }
        return "";
    }
}
