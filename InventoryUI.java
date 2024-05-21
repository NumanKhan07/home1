import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class InventoryGUI extends JFrame {
    private InventoryItem[] myInventory = new InventoryItem[100];
    private int numberEntries;
    private JMenuItem newItem, deleteItem, exitItem, aboutItem;
    private JButton newButton, deleteButton, saveButton, previousButton, nextButton, printButton, exitButton, photoButton;
    private JLabel itemLabel, locationLabel, serialLabel, priceLabel, dateLabel, storeLabel, noteLabel, photoLabel;
    private JTextField itemTextField, serialTextField, priceTextField, storeTextField, noteTextField;
    private JCheckBox markedCheckBox;
    private JComboBox<String> locationComboBox;
    private JRadioButton searchByItem, searchByLocation;
    private JList<String> searchList;
    private DefaultListModel<String> searchListModel;
    private JPanel searchPanel, photoPanel;
    private JTextArea photoTextArea;
    private JFileChooser photoFileChooser;
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        new InventoryGUI().setVisible(true);
    }

    public InventoryGUI() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Home Inventory Manager");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        menuBar.add(fileMenu);

        newItem = new JMenuItem("New Item");
        newItem.setMnemonic('N');
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newButtonActionPerformed(e);
            }
        });
        fileMenu.add(newItem);

        deleteItem = new JMenuItem("Delete Item");
        deleteItem.setMnemonic('D');
        deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });
        fileMenu.add(deleteItem);

        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic('E');
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
            }
        });
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic('H');
        menuBar.add(helpMenu);

        aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic('A');
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Home Inventory Manager\nVersion 1.0\n\nBy John Smith", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        helpMenu.add(aboutItem);

        JPanel inventoryToolBar = new JPanel();
        newButton = new JButton();
        deleteButton = new JButton();
        saveButton = new JButton();
        previousButton = new JButton();
        nextButton = new JButton();
        printButton = new JButton();
        exitButton = new JButton();
        itemLabel = new JLabel();
        itemTextField = new JTextField();
        locationLabel = new JLabel();
        locationComboBox = new JComboBox<>();
        markedCheckBox = new JCheckBox();
        serialLabel = new JLabel();
        serialTextField = new JTextField();
        priceLabel = new JLabel();
        priceTextField = new JTextField();
        dateLabel = new JLabel();
        storeLabel = new JLabel();
        storeTextField = new JTextField();
        noteLabel = new JLabel();
        noteTextField = new JTextField();
        photoLabel = new JLabel();
        photoTextArea = new JTextArea();
        photoButton = new JButton();
        searchPanel = new JPanel();
        searchList = new JList<>();
        searchListModel = new DefaultListModel<>();
        ButtonGroup searchGroup = new ButtonGroup();
        searchByItem = new JRadioButton("Search by Item");
        searchByLocation = new JRadioButton("Search by Location");
        searchGroup.add(searchByItem);
        searchGroup.add(searchByLocation);
        photoPanel = new JPanel();

        setSize(780, 480);
        getContentPane().setLayout(new GridBagLayout());

        inventoryToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        newButton.setText("New");
        newButton.setToolTipText("Add New Item");
        newButton.setFocusable(false);
        newButton.setHorizontalTextPosition(SwingConstants.CENTER);
        newButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryToolBar.add(newButton);
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newButtonActionPerformed(e);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setToolTipText("Delete Item");
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryToolBar.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteButtonActionPerformed(e);
            }
        });

        saveButton.setText("Save");
        saveButton.setToolTipText("Save Current Item");
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryToolBar.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });

        previousButton.setText("Previous");
        previousButton.setToolTipText("Display Previous Item");
        previousButton.setFocusable(false);
        previousButton.setHorizontalTextPosition(SwingConstants.CENTER);
        previousButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryToolBar.add(previousButton);
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousButtonActionPerformed(e);
            }
        });

        nextButton.setText("Next");
        nextButton.setToolTipText("Display Next Item");
        nextButton.setFocusable(false);
        nextButton.setHorizontalTextPosition(SwingConstants.CENTER);
        nextButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryToolBar.add(nextButton);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextButtonActionPerformed(e);
            }
        });

        printButton.setText("Print");
        printButton.setToolTipText("Print Inventory List");
        printButton.setFocusable(false);
        printButton.setHorizontalTextPosition(SwingConstants.CENTER);
        printButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryToolBar.add(printButton);
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printButtonActionPerformed(e);
            }
        });

        exitButton.setText("Exit");
        exitButton.setFocusable(false);
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        inventoryToolBar.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
            }
        });

        GridBagConstraints gridConstraints;

        itemLabel.setText("Inventory Item");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(itemLabel, gridConstraints);

        itemTextField.setPreferredSize(new Dimension(400, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(itemTextField, gridConstraints);

        locationLabel.setText("Location");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(locationLabel, gridConstraints);

        locationComboBox.setPreferredSize(new Dimension(270, 25));
        locationComboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        locationComboBox.setEditable(true);
        locationComboBox.addItem("");
        locationComboBox.addItem("Attic");
        locationComboBox.addItem("Basement");
        locationComboBox.addItem("Bedroom");
        locationComboBox.addItem("Garage");
        locationComboBox.addItem("Kitchen");
        locationComboBox.addItem("Living Room");
        locationComboBox.addItem("Outside");
        locationComboBox.addItem("Storage Unit");
        locationComboBox.addItem("Other");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 3;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(locationComboBox, gridConstraints);

        markedCheckBox.setText("Marked?");
        markedCheckBox.setFocusable(false);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 5;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.WEST;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(markedCheckBox, gridConstraints);

        serialLabel.setText("Serial Number");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(serialLabel, gridConstraints);

        serialTextField.setPreferredSize(new Dimension(270, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        gridConstraints.gridwidth = 3;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(serialTextField, gridConstraints);

        priceLabel.setText("Purchase Price");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 3;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(priceLabel, gridConstraints);

        priceTextField.setPreferredSize(new Dimension(160, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 3;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(priceTextField, gridConstraints);

        dateLabel.setText("Date Purchased");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 4;
        gridConstraints.gridy = 3;
        gridConstraints.gridwidth = 2;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(dateLabel, gridConstraints);

        storeLabel.setText("Store/Website");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 4;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(storeLabel, gridConstraints);

        storeTextField.setPreferredSize(new Dimension(400, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 4;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(storeTextField, gridConstraints);

        noteLabel.setText("Note");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 5;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(noteLabel, gridConstraints);

        noteTextField.setPreferredSize(new Dimension(400, 25));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 5;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(noteTextField, gridConstraints);

        photoLabel.setText("Photo");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 6;
        gridConstraints.insets = new Insets(10, 10, 0, 10);
        getContentPane().add(photoLabel, gridConstraints);

        photoTextArea.setPreferredSize(new Dimension(350, 35));
        photoTextArea.setFont(new Font("Arial", Font.PLAIN, 12));
        photoTextArea.setEditable(false);
        photoTextArea.setLineWrap(true);
        photoTextArea.setWrapStyleWord(true);
        photoTextArea.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(photoTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 6;
        gridConstraints.gridwidth = 4;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(scrollPane, gridConstraints);

        photoButton.setText("...");
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 6;
        gridConstraints.gridy = 6;
        gridConstraints.insets = new Insets(10, 0, 0, 10);
        getContentPane().add(photoButton, gridConstraints);
        photoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                photoButtonActionPerformed(e);
            }
        });

        searchPanel.setBorder(BorderFactory.createTitledBorder("Search"));
        searchPanel.setLayout(new GridBagLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 7;
        gridConstraints.gridwidth = 5;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(searchPanel, gridConstraints);

        searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane searchListScrollPane = new JScrollPane(searchList);
        searchListScrollPane.setPreferredSize(new Dimension(300, 100));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.gridwidth = 3;
        gridConstraints.insets = new Insets(0, 0, 10, 0);
        searchPanel.add(searchListScrollPane, gridConstraints);

        JPanel searchButtonPanel = new JPanel();
        searchButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        searchButtonPanel.add(searchByItem);
        searchButtonPanel.add(searchByLocation);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.insets = new Insets(0, 0, 10, 10);
        searchPanel.add(searchButtonPanel, gridConstraints);

        photoPanel.setBorder(BorderFactory.createTitledBorder("Photo"));
        photoPanel.setLayout(new BorderLayout());
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 8;
        gridConstraints.gridwidth = 6;
        gridConstraints.gridheight = 3;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(photoPanel, gridConstraints);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitForm(evt);
            }
        });

        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
            }

            public String getDescription() {
                return "GIF Images";
            }
        });

        setVisible(true);
    }

    private void newButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "New" button click here
    }

    private void deleteButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "Delete" button click here
    }

    private void saveButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "Save" button click here
    }

    private void previousButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "Previous" button click here
    }

    private void nextButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "Next" button click here
    }

    private void printButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "Print" button click here
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "Exit" button click here
    }

    private void photoButtonActionPerformed(ActionEvent e) {
        // Add your code for handling the "Photo" button click here
    }

    private void exitForm(WindowEvent evt) {
        // Add your code for handling the window closing event here
    }
}
