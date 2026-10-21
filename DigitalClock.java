import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DigitalClock extends JPanel {

    private JLabel timeLabel;
    private SimpleDateFormat timeFormat;

    public DigitalClock() {

        setLayout(new BorderLayout());

        timeFormat = new SimpleDateFormat("hh:mm:ss a");  // 12-hour format

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(timeLabel, BorderLayout.CENTER);

        // Update time every 1 second
        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();

        updateClock();
    }

    private void updateClock() {
        timeLabel.setText(timeFormat.format(new Date()));
    }
}
