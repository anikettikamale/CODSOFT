import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class task2 extends JFrame implements ActionListener {
    private final JTextArea textArea;
    private final JButton countButton;

    public task2() {
        super("Word Counter");

       
        textArea = new JTextArea(15, 50);
        JScrollPane scrollPane = new JScrollPane(textArea);

        countButton = new JButton("Count Words");
        countButton.addActionListener(this);

     
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(countButton, BorderLayout.SOUTH);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == countButton) {
            String inputText = textArea.getText();
            int wordCount = countWords(inputText);
            JOptionPane.showMessageDialog(this, "Total Words: " + wordCount);
        }
    }

    private int countWords(String input) {
        String[] words = splitTextIntoWords(input);
        return words.length;
    }

    private String[] splitTextIntoWords(String text) {
       
        String pattern = "\\s+|(?=\\p{Punct})|(?<=\\p{Punct})";
        return text.toLowerCase().split(pattern);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new task2());
    }
}

