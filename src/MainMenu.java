import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Implements Singleton pattern
public class MainMenu extends JPanel {
    private static MainMenu instance = null;

    public static final int WIDTH = SimulationFrame.WIDTH;
    public static final int HEIGHT = SimulationFrame.HEIGHT;
    private final JTextField ruleTextField;

    private MainMenu() {
        setSize(WIDTH, HEIGHT);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Upper panel
        JPanel upperPanel = new JPanel(new GridBagLayout());
        upperPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 2));
//        upperPanel.setBackground(Color.BLUE);
        add(upperPanel, gbc);
        gbc.gridy++;

        JLabel mainText = new JLabel("Cellular Automata Simulator");
        mainText.setFont(new Font("Arial", Font.BOLD, 30));

        upperPanel.add(mainText, gbc);

        // Lower panel
        JPanel lowerPanel = new JPanel(new GridBagLayout());
        lowerPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 2));
//        lowerPanel.setBackground(Color.RED);
        add(lowerPanel, gbc);

            // Rule input
        JPanel rulePanel = new JPanel(new GridLayout(1,2));
//        rulePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT / 4));

        JLabel ruleLabel = new JLabel("Rule: ");
        ruleLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        rulePanel.add(ruleLabel);

        ruleTextField = new JTextField(3);
        ruleTextField.setPreferredSize(new Dimension(200, 40));
        ruleTextField.setFont(new Font("Arial", Font.PLAIN, 22));
        ruleTextField.setBackground(null);
        ruleTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ruleTextField.addActionListener(e -> inspectTextField(ruleTextField));
        rulePanel.add(ruleTextField);

        gbc.gridy = 0;
        lowerPanel.add(rulePanel, gbc);
        gbc.gridy++;

        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(200, 40));
        startButton.setFont(new Font("Arial", Font.PLAIN, 22));
        startButton.addActionListener(e -> {
            SimulationPanel.getInstance().setRule(getRule());
            setVisible(false);
        });
        lowerPanel.add(startButton, gbc);

        add(upperPanel, gbc);
        gbc.gridy++;
        add(lowerPanel, gbc);

        setVisible(true);
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    // allows only 3 digits to be entered, in the range [0, 255]
    private void inspectTextField(JTextField textField) {
        String text = textField.getText();
        if (text.length() > 3) {
            textField.setText(text.substring(0, 3));
        }

        try {
            int rule = Integer.parseInt(text);
            if (rule < 0) {
                textField.setText("0");
            } else if (rule > 255) {
                textField.setText("255");
            }
        } catch (NumberFormatException e) {
            textField.setText("");
        }
    }

    public int getRule() {
        String text = ruleTextField.getText();
        if (text.isEmpty()) {
            return 0;
        }
        int rule = 0;
        try {
            rule = Integer.parseInt(text);
            if (rule < 0) {
                return 0;
            } else if (rule > 255) {
                return 255;
            }
        } catch (NumberFormatException e) {
            return 0;
        }
        return rule;
    }

}
