import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class HotelSystem extends JFrame implements ActionListener {
    private JTextField tfGuestName, tfGuestAge;
    private JButton btnAdd, btnDelete, btnSearch;
    private JTable table;
    private DefaultTableModel model;

    public HotelSystem() {
        setTitle("Orion de Hotel");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel lblGuestName = new JLabel("Guest Name:");
        tfGuestName = new JTextField();
        JLabel lblGuestAge = new JLabel("Guest Age:");
        tfGuestAge = new JTextField();

        panel.add(lblGuestName);
        panel.add(tfGuestName);
        panel.add(lblGuestAge);
        panel.add(tfGuestAge);

        btnAdd = new JButton("Add Guest");
        btnDelete = new JButton("Delete Guest");
        btnSearch = new JButton("Search Guest");

        panel.add(btnAdd);
        panel.add(btnDelete);
        panel.add(btnSearch);

        model = new DefaultTableModel();
        table = new JTable(model);
        model.addColumn("Guest Name");
        model.addColumn("Guest Age");

        JScrollPane scrollPane = new JScrollPane(table);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnAdd.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        new HotelSystem();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            String guestName = tfGuestName.getText();
            String guestAge = tfGuestAge.getText();

            model.addRow(new Object[]{guestName, guestAge});
            tfGuestName.setText("");
            tfGuestAge.setText("");
        } else if (e.getSource() == btnDelete) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            }
        } else if (e.getSource() == btnSearch) {
            String searchTerm = JOptionPane.showInputDialog(this, "Enter guest name to search:");
            if (searchTerm != null && !searchTerm.isEmpty()) {
                boolean found = false;
                for (int i = 0; i < model.getRowCount(); i++) {
                    String guestName = model.getValueAt(i, 0).toString();
                    if (guestName.equalsIgnoreCase(searchTerm)) {
                        table.setRowSelectionInterval(i, i);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    JOptionPane.showMessageDialog(this, "Guest not found.");
                }
            }
        }
    }
}