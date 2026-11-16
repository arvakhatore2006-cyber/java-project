import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class HospitalManagementSystem extends JFrame {

    // ================= COLORS =================
    private final Color BACKGROUND = new Color(20, 24, 30);
    private final Color PANEL = new Color(30, 36, 44);
    private final Color CARD = new Color(40, 47, 57);
    private final Color BLUE = new Color(45, 125, 220);
    private final Color GREEN = new Color(40, 160, 100);
    private final Color ORANGE = new Color(220, 145, 40);
    private final Color RED = new Color(200, 60, 60);
    private final Color PURPLE = new Color(125, 85, 190);
    private final Color WHITE = Color.WHITE;
    private final Color LIGHT = new Color(190, 200, 210);

    // ================= DATA =================
    private final ArrayList<Patient> patients = new ArrayList<>();
    private final ArrayList<Doctor> doctors = new ArrayList<>();
    private final ArrayList<Appointment> appointments = new ArrayList<>();
    private final ArrayList<Bill> bills = new ArrayList<>();

    // ================= TABLES =================
    private JTable patientTable;
    private JTable doctorTable;
    private JTable appointmentTable;

    private DefaultTableModel patientModel;
    private DefaultTableModel doctorModel;
    private DefaultTableModel appointmentModel;

    // ================= COUNTERS =================
    private int nextPatientId = 6;
    private int nextDoctorId = 5;
    private int nextAppointmentId = 4;
    private int nextBillId = 2;

    // ================= CONSTRUCTOR =================
    public HospitalManagementSystem() {
        setTitle("Hospital Management System");
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loadSampleData();
        showLogin();
    }

    // ================= PATIENT CLASS =================
    class Patient {
        int id;
        String name;
        int age;
        String gender;
        String bloodGroup;
        String phone;
        String disease;
        String doctor;
        String room;
        String admissionDate;
        String status;

        Patient(int id, String name, int age, String gender,
                String bloodGroup, String phone, String disease,
                String doctor, String room, String admissionDate,
                String status) {

            this.id = id;
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.bloodGroup = bloodGroup;
            this.phone = phone;
            this.disease = disease;
            this.doctor = doctor;
            this.room = room;
            this.admissionDate = admissionDate;
            this.status = status;
        }
    }

    // ================= DOCTOR CLASS =================
    class Doctor {
        int id;
        String name;
        String specialization;
        String phone;
        String department;
        String availability;

        Doctor(int id, String name, String specialization,
               String phone, String department,
               String availability) {

            this.id = id;
            this.name = name;
            this.specialization = specialization;
            this.phone = phone;
            this.department = department;
            this.availability = availability;
        }
    }

    // ================= APPOINTMENT CLASS =================
    class Appointment {
        int id;
        int patientId;
        String patientName;
        String doctor;
        String date;
        String time;
        String reason;
        String status;

        Appointment(int id, int patientId, String patientName,
                    String doctor, String date, String time,
                    String reason, String status) {

            this.id = id;
            this.patientId = patientId;
            this.patientName = patientName;
            this.doctor = doctor;
            this.date = date;
            this.time = time;
            this.reason = reason;
            this.status = status;
        }
    }

    // ================= BILL CLASS =================
    class Bill {
        int billId;
        int patientId;
        String patientName;
        double consultation;
        double roomCharge;
        double medicine;
        double testCharge;
        double total;
        String date;

        Bill(int billId, int patientId, String patientName,
             double consultation, double roomCharge,
             double medicine, double testCharge,
             String date) {

            this.billId = billId;
            this.patientId = patientId;
            this.patientName = patientName;
            this.consultation = consultation;
            this.roomCharge = roomCharge;
            this.medicine = medicine;
            this.testCharge = testCharge;
            this.total = consultation + roomCharge + medicine + testCharge;
            this.date = date;
        }
    }

    // ================= SAMPLE DATA =================
    private void loadSampleData() {

        patients.add(new Patient(
                1, "Rahul Sharma", 35, "Male", "B+",
                "9876543210", "Fever", "Dr. Mehta",
                "101", currentDate(), "Admitted"
        ));

        patients.add(new Patient(
                2, "Priya Singh", 28, "Female", "A+",
                "9876501234", "Migraine", "Dr. Kapoor",
                "102", currentDate(), "Admitted"
        ));

        patients.add(new Patient(
                3, "Amit Verma", 45, "Male", "O+",
                "9876512345", "Diabetes", "Dr. Sharma",
                "103", currentDate(), "Discharged"
        ));

        patients.add(new Patient(
                4, "Neha Gupta", 31, "Female", "AB+",
                "9876523456", "Asthma", "Dr. Mehta",
                "-", currentDate(), "Outpatient"
        ));

        patients.add(new Patient(
                5, "Rohit Jain", 52, "Male", "B-",
                "9876534567", "Heart Problem", "Dr. Kapoor",
                "104", currentDate(), "Admitted"
        ));

        doctors.add(new Doctor(
                1, "Dr. Mehta", "General Physician",
                "9000000001", "General Medicine", "Available"
        ));

        doctors.add(new Doctor(
                2, "Dr. Kapoor", "Cardiologist",
                "9000000002", "Cardiology", "Available"
        ));

        doctors.add(new Doctor(
                3, "Dr. Sharma", "Diabetologist",
                "9000000003", "Diabetes", "Busy"
        ));

        doctors.add(new Doctor(
                4, "Dr. Verma", "Neurologist",
                "9000000004", "Neurology", "Available"
        ));

        appointments.add(new Appointment(
                1, 1, "Rahul Sharma", "Dr. Mehta",
                currentDate(), "10:00 AM",
                "Regular Checkup", "Scheduled"
        ));

        appointments.add(new Appointment(
                2, 2, "Priya Singh", "Dr. Kapoor",
                currentDate(), "11:30 AM",
                "Migraine Consultation", "Scheduled"
        ));

        appointments.add(new Appointment(
                3, 4, "Neha Gupta", "Dr. Verma",
                currentDate(), "02:00 PM",
                "Headache", "Completed"
        ));

        bills.add(new Bill(
                1, 1, "Rahul Sharma",
                500, 1500, 700, 300, currentDate()
        ));
    }

    // ================= DATE =================
    private String currentDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    // ================= LOGIN =================
    private void showLogin() {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND);

        JPanel box = new JPanel();
        box.setPreferredSize(new Dimension(400, 350));
        box.setBackground(PANEL);
        box.setLayout(new GridLayout(7, 1, 10, 10));

        JLabel title = new JLabel(
                "HOSPITAL MANAGEMENT",
                SwingConstants.CENTER
        );
        title.setForeground(WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel subtitle = new JLabel(
                "Admin Login",
                SwingConstants.CENTER
        );
        subtitle.setForeground(LIGHT);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));

        JTextField username = new JTextField();
        username.setBorder(BorderFactory.createTitledBorder("Username"));

        JPasswordField password = new JPasswordField();
        password.setBorder(BorderFactory.createTitledBorder("Password"));

        JButton loginButton = createButton("LOGIN", BLUE);

        JLabel info = new JLabel(
                "Username: admin | Password: 1234",
                SwingConstants.CENTER
        );
        info.setForeground(LIGHT);

        loginButton.addActionListener(e -> {

            String user = username.getText();
            String pass = new String(password.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {

                createMainGUI();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password!",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        box.add(title);
        box.add(subtitle);
        box.add(username);
        box.add(password);
        box.add(loginButton);
        box.add(info);

        panel.add(box);

        setContentPane(panel);
        revalidate();
        repaint();
    }

    // ================= MAIN GUI =================
    private void createMainGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PANEL);
        header.setPreferredSize(new Dimension(0, 70));

        JLabel title = new JLabel("  HOSPITAL MANAGEMENT SYSTEM");
        title.setForeground(WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel admin = new JLabel("Administrator  |  ");
        admin.setForeground(LIGHT);

        header.add(title, BorderLayout.WEST);
        header.add(admin, BorderLayout.EAST);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setBackground(PANEL);
        sidebar.setPreferredSize(new Dimension(210, 0));
        sidebar.setLayout(new GridLayout(9, 1, 5, 5));

        JButton dashboardBtn = createButton("Dashboard", BLUE);
        JButton patientBtn = createButton("Patients", GREEN);
        JButton doctorBtn = createButton("Doctors", PURPLE);
        JButton appointmentBtn = createButton("Appointments", ORANGE);
        JButton prescriptionBtn = createButton("Prescription", BLUE);
        JButton billingBtn = createButton("Billing", GREEN);
        JButton aboutBtn = createButton("About", PURPLE);
        JButton logoutBtn = createButton("Logout", RED);

        sidebar.add(dashboardBtn);
        sidebar.add(patientBtn);
        sidebar.add(doctorBtn);
        sidebar.add(appointmentBtn);
        sidebar.add(prescriptionBtn);
        sidebar.add(billingBtn);
        sidebar.add(aboutBtn);
        sidebar.add(logoutBtn);

        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(BACKGROUND);

        dashboardBtn.addActionListener(e -> showDashboard(content));
        patientBtn.addActionListener(e -> showPatients(content));
        doctorBtn.addActionListener(e -> showDoctors(content));
        appointmentBtn.addActionListener(e -> showAppointments(content));
        prescriptionBtn.addActionListener(e -> showPrescription());
        billingBtn.addActionListener(e -> showBilling());
        aboutBtn.addActionListener(e -> showAbout());

        logoutBtn.addActionListener(e -> {

            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                showLogin();
            }
        });

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(content, BorderLayout.CENTER);

        setContentPane(mainPanel);
        revalidate();
        repaint();

        showDashboard(content);
    }

    // ================= DASHBOARD =================
    private void showDashboard(JPanel content) {

        content.removeAll();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND);

        JLabel heading = new JLabel("Dashboard");
        heading.setForeground(WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setBorder(BorderFactory.createEmptyBorder(
                20, 20, 20, 20
        ));

        JPanel cards = new JPanel(new GridLayout(2, 2, 20, 20));
        cards.setBackground(BACKGROUND);
        cards.setBorder(BorderFactory.createEmptyBorder(
                20, 30, 30, 30
        ));

        int admitted = 0;

        for (Patient p : patients) {
            if (p.status.equalsIgnoreCase("Admitted")) {
                admitted++;
            }
        }

        cards.add(createCard(
                "TOTAL PATIENTS",
                String.valueOf(patients.size()),
                BLUE
        ));

        cards.add(createCard(
                "ADMITTED PATIENTS",
                String.valueOf(admitted),
                GREEN
        ));

        cards.add(createCard(
                "TOTAL DOCTORS",
                String.valueOf(doctors.size()),
                PURPLE
        ));

        cards.add(createCard(
                "APPOINTMENTS",
                String.valueOf(appointments.size()),
                ORANGE
        ));

        panel.add(heading, BorderLayout.NORTH);
        panel.add(cards, BorderLayout.CENTER);

        content.add(panel);
        content.revalidate();
        content.repaint();
    }

    // ================= CARD =================
    private JPanel createCard(
            String title,
            String value,
            Color color) {

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(CARD);
        card.setBorder(
                BorderFactory.createLineBorder(color, 2)
        );

        JLabel titleLabel = new JLabel(
                title,
                SwingConstants.CENTER
        );

        titleLabel.setForeground(LIGHT);
        titleLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        JLabel valueLabel = new JLabel(
                value,
                SwingConstants.CENTER
        );

        valueLabel.setForeground(color);
        valueLabel.setFont(
                new Font("Arial", Font.BOLD, 42)
        );

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    // ================= PATIENT SCREEN =================
    private void showPatients(JPanel content) {

        content.removeAll();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND);
        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel heading = new JLabel("Patient Management");
        heading.setForeground(WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(BACKGROUND);

        JTextField searchField = new JTextField(15);
        searchField.setToolTipText("Search patient");

        JButton searchButton = createButton("Search", BLUE);
        JButton addButton = createButton("Add Patient", GREEN);
        JButton updateButton = createButton("Update", ORANGE);
        JButton deleteButton = createButton("Delete", RED);
        JButton viewButton = createButton("View", PURPLE);

        top.add(new JLabel("Search:"));
        top.add(searchField);
        top.add(searchButton);
        top.add(addButton);
        top.add(updateButton);
        top.add(deleteButton);
        top.add(viewButton);

        patientModel = new DefaultTableModel(
                new String[]{
                        "ID", "Name", "Age", "Gender",
                        "Blood", "Phone", "Disease",
                        "Doctor", "Room", "Date", "Status"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {
                return false;
            }
        };

        patientTable = new JTable(patientModel);
        patientTable.setRowHeight(28);
        patientTable.setBackground(CARD);
        patientTable.setForeground(WHITE);
        patientTable.setSelectionBackground(BLUE);

        loadPatientTable();

        TableRowSorter<DefaultTableModel> sorter =
                new TableRowSorter<>(patientModel);

        patientTable.setRowSorter(sorter);

        searchButton.addActionListener(e -> {
            String text = searchField.getText().trim();

            if (text.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(
                        RowFilter.regexFilter(
                                "(?i)" + text
                        )
                );
            }
        });

        addButton.addActionListener(e -> {
            addPatient();
            loadPatientTable();
        });

        updateButton.addActionListener(e -> {
            updatePatient();
            loadPatientTable();
        });

        deleteButton.addActionListener(e -> {
            deletePatient();
            loadPatientTable();
        });

        viewButton.addActionListener(e -> viewPatient());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(BACKGROUND);
        topPanel.add(heading, BorderLayout.NORTH);
        topPanel.add(top, BorderLayout.SOUTH);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(patientTable), BorderLayout.CENTER);

        content.add(panel);
        content.revalidate();
        content.repaint();
    }

    // ================= LOAD PATIENT TABLE =================
    private void loadPatientTable() {

        if (patientModel == null) {
            return;
        }

        patientModel.setRowCount(0);

        for (Patient p : patients) {

            patientModel.addRow(new Object[]{
                    p.id,
                    p.name,
                    p.age,
                    p.gender,
                    p.bloodGroup,
                    p.phone,
                    p.disease,
                    p.doctor,
                    p.room,
                    p.admissionDate,
                    p.status
            });
        }
    }

    // ================= ADD PATIENT =================
    private void addPatient() {

        JTextField name = new JTextField();
        JTextField age = new JTextField();
        JTextField phone = new JTextField();
        JTextField disease = new JTextField();
        JTextField room = new JTextField();

        JComboBox<String> gender =
                new JComboBox<>(new String[]{
                        "Male", "Female", "Other"
                });

        JComboBox<String> blood =
                new JComboBox<>(new String[]{
                        "A+", "A-", "B+", "B-",
                        "AB+", "AB-", "O+", "O-"
                });

        JComboBox<String> doctor = new JComboBox<>();

        for (Doctor d : doctors) {
            doctor.addItem(d.name);
        }

        JComboBox<String> status =
                new JComboBox<>(new String[]{
                        "Admitted",
                        "Outpatient",
                        "Discharged"
                });

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));

        form.add(new JLabel("Name:"));
        form.add(name);

        form.add(new JLabel("Age:"));
        form.add(age);

        form.add(new JLabel("Gender:"));
        form.add(gender);

        form.add(new JLabel("Blood Group:"));
        form.add(blood);

        form.add(new JLabel("Phone:"));
        form.add(phone);

        form.add(new JLabel("Disease:"));
        form.add(disease);

        form.add(new JLabel("Doctor:"));
        form.add(doctor);

        form.add(new JLabel("Room:"));
        form.add(room);

        form.add(new JLabel("Status:"));
        form.add(status);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Add New Patient",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            String patientName = name.getText().trim();
            int patientAge = Integer.parseInt(
                    age.getText().trim()
            );

            if (patientName.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Name cannot be empty."
                );
                return;
            }

            if (patientAge <= 0 || patientAge > 120) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid age."
                );
                return;
            }

            Patient p = new Patient(
                    nextPatientId++,
                    patientName,
                    patientAge,
                    gender.getSelectedItem().toString(),
                    blood.getSelectedItem().toString(),
                    phone.getText().trim(),
                    disease.getText().trim(),
                    doctor.getSelectedItem().toString(),
                    room.getText().trim(),
                    currentDate(),
                    status.getSelectedItem().toString()
            );

            patients.add(p);

            JOptionPane.showMessageDialog(
                    this,
                    "Patient added successfully!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= UPDATE PATIENT =================
    private void updatePatient() {

        int row = patientTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a patient first."
            );
            return;
        }

        int modelRow =
                patientTable.convertRowIndexToModel(row);

        int id = (int) patientModel.getValueAt(
                modelRow, 0
        );

        Patient selected = null;

        for (Patient p : patients) {
            if (p.id == id) {
                selected = p;
                break;
            }
        }

        if (selected == null) {
            return;
        }

        JTextField name = new JTextField(selected.name);
        JTextField age = new JTextField(
                String.valueOf(selected.age)
        );
        JTextField phone = new JTextField(selected.phone);
        JTextField disease = new JTextField(selected.disease);
        JTextField room = new JTextField(selected.room);

        JComboBox<String> status =
                new JComboBox<>(new String[]{
                        "Admitted",
                        "Outpatient",
                        "Discharged"
                });

        status.setSelectedItem(selected.status);

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));

        form.add(new JLabel("Name:"));
        form.add(name);

        form.add(new JLabel("Age:"));
        form.add(age);

        form.add(new JLabel("Phone:"));
        form.add(phone);

        form.add(new JLabel("Disease:"));
        form.add(disease);

        form.add(new JLabel("Room:"));
        form.add(room);

        form.add(new JLabel("Status:"));
        form.add(status);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Update Patient",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            selected.name = name.getText().trim();
            selected.age = Integer.parseInt(
                    age.getText().trim()
            );
            selected.phone = phone.getText().trim();
            selected.disease = disease.getText().trim();
            selected.room = room.getText().trim();
            selected.status =
                    status.getSelectedItem().toString();

            JOptionPane.showMessageDialog(
                    this,
                    "Patient updated successfully!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a number."
            );
        }
    }

    // ================= DELETE PATIENT =================
    private void deletePatient() {

        int row = patientTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a patient."
            );
            return;
        }

        int modelRow =
                patientTable.convertRowIndexToModel(row);

        int id = (int) patientModel.getValueAt(
                modelRow, 0
        );

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete selected patient?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        patients.removeIf(p -> p.id == id);

        JOptionPane.showMessageDialog(
                this,
                "Patient deleted successfully!"
        );
    }

    // ================= VIEW PATIENT =================
    private void viewPatient() {

        int row = patientTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a patient."
            );
            return;
        }

        int modelRow =
                patientTable.convertRowIndexToModel(row);

        int id = (int) patientModel.getValueAt(
                modelRow, 0
        );

        for (Patient p : patients) {

            if (p.id == id) {

                String details =
                        "Patient ID: " + p.id +
                        "\nName: " + p.name +
                        "\nAge: " + p.age +
                        "\nGender: " + p.gender +
                        "\nBlood Group: " + p.bloodGroup +
                        "\nPhone: " + p.phone +
                        "\nDisease: " + p.disease +
                        "\nDoctor: " + p.doctor +
                        "\nRoom: " + p.room +
                        "\nAdmission Date: " +
                        p.admissionDate +
                        "\nStatus: " + p.status;

                JOptionPane.showMessageDialog(
                        this,
                        details,
                        "Patient Details",
                        JOptionPane.INFORMATION_MESSAGE
                );

                break;
            }
        }
    }

    // ================= DOCTOR SCREEN =================
    private void showDoctors(JPanel content) {

        content.removeAll();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND);
        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel heading = new JLabel("Doctor Management");
        heading.setForeground(WHITE);
        heading.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel buttons = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );
        buttons.setBackground(BACKGROUND);

        JButton add = createButton("Add Doctor", GREEN);
        JButton delete = createButton("Delete Doctor", RED);

        buttons.add(add);
        buttons.add(delete);

        doctorModel = new DefaultTableModel(
                new String[]{
                        "ID", "Name", "Specialization",
                        "Phone", "Department", "Availability"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {
                return false;
            }
        };

        doctorTable = new JTable(doctorModel);
        doctorTable.setRowHeight(28);
        doctorTable.setBackground(CARD);
        doctorTable.setForeground(WHITE);
        doctorTable.setSelectionBackground(BLUE);

        loadDoctorTable();

        add.addActionListener(e -> {
            addDoctor();
            loadDoctorTable();
        });

        delete.addActionListener(e -> {
            deleteDoctor();
            loadDoctorTable();
        });

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(BACKGROUND);
        top.add(heading, BorderLayout.NORTH);
        top.add(buttons, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(doctorTable),
                BorderLayout.CENTER);

        content.add(panel);
        content.revalidate();
        content.repaint();
    }

    // ================= LOAD DOCTORS =================
    private void loadDoctorTable() {

        doctorModel.setRowCount(0);

        for (Doctor d : doctors) {

            doctorModel.addRow(new Object[]{
                    d.id,
                    d.name,
                    d.specialization,
                    d.phone,
                    d.department,
                    d.availability
            });
        }
    }

    // ================= ADD DOCTOR =================
    private void addDoctor() {

        JTextField name = new JTextField();
        JTextField specialization = new JTextField();
        JTextField phone = new JTextField();
        JTextField department = new JTextField();

        JComboBox<String> availability =
                new JComboBox<>(new String[]{
                        "Available",
                        "Busy",
                        "On Leave"
                });

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));

        form.add(new JLabel("Name:"));
        form.add(name);

        form.add(new JLabel("Specialization:"));
        form.add(specialization);

        form.add(new JLabel("Phone:"));
        form.add(phone);

        form.add(new JLabel("Department:"));
        form.add(department);

        form.add(new JLabel("Availability:"));
        form.add(availability);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Add Doctor",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        if (name.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Doctor name is required."
            );
            return;
        }

        doctors.add(new Doctor(
                nextDoctorId++,
                name.getText().trim(),
                specialization.getText().trim(),
                phone.getText().trim(),
                department.getText().trim(),
                availability.getSelectedItem().toString()
        ));

        JOptionPane.showMessageDialog(
                this,
                "Doctor added successfully!"
        );
    }

    // ================= DELETE DOCTOR =================
    private void deleteDoctor() {

        int row = doctorTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a doctor."
            );
            return;
        }

        int id = (int) doctorModel.getValueAt(
                row, 0
        );

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete selected doctor?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            doctors.removeIf(d -> d.id == id);

            JOptionPane.showMessageDialog(
                    this,
                    "Doctor deleted successfully!"
            );
        }
    }

    // ================= APPOINTMENT SCREEN =================
    private void showAppointments(JPanel content) {

        content.removeAll();

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND);
        panel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel heading = new JLabel(
                "Appointment Management"
        );

        heading.setForeground(WHITE);
        heading.setFont(new Font(
                "Arial",
                Font.BOLD,
                24
        ));

        JPanel buttons = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

        buttons.setBackground(BACKGROUND);

        JButton add = createButton(
                "Book Appointment",
                GREEN
        );

        JButton update = createButton(
                "Change Status",
                ORANGE
        );

        JButton delete = createButton(
                "Delete",
                RED
        );

        buttons.add(add);
        buttons.add(update);
        buttons.add(delete);

        appointmentModel = new DefaultTableModel(
                new String[]{
                        "ID", "Patient ID", "Patient",
                        "Doctor", "Date", "Time",
                        "Reason", "Status"
                }, 0
        ) {
            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {
                return false;
            }
        };

        appointmentTable = new JTable(
                appointmentModel
        );

        appointmentTable.setRowHeight(28);
        appointmentTable.setBackground(CARD);
        appointmentTable.setForeground(WHITE);
        appointmentTable.setSelectionBackground(BLUE);

        loadAppointmentTable();

        add.addActionListener(e -> {
            addAppointment();
            loadAppointmentTable();
        });

        update.addActionListener(e -> {
            updateAppointmentStatus();
            loadAppointmentTable();
        });

        delete.addActionListener(e -> {
            deleteAppointment();
            loadAppointmentTable();
        });

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(BACKGROUND);
        top.add(heading, BorderLayout.NORTH);
        top.add(buttons, BorderLayout.SOUTH);

        panel.add(top, BorderLayout.NORTH);
        panel.add(
                new JScrollPane(appointmentTable),
                BorderLayout.CENTER
        );

        content.add(panel);
        content.revalidate();
        content.repaint();
    }

    // ================= LOAD APPOINTMENTS =================
    private void loadAppointmentTable() {

        appointmentModel.setRowCount(0);

        for (Appointment a : appointments) {

            appointmentModel.addRow(new Object[]{
                    a.id,
                    a.patientId,
                    a.patientName,
                    a.doctor,
                    a.date,
                    a.time,
                    a.reason,
                    a.status
            });
        }
    }

    // ================= ADD APPOINTMENT =================
    private void addAppointment() {

        if (patients.isEmpty() || doctors.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "You need at least one patient and doctor."
            );

            return;
        }

        JComboBox<String> patientBox =
                new JComboBox<>();

        for (Patient p : patients) {
            patientBox.addItem(
                    p.id + " - " + p.name
            );
        }

        JComboBox<String> doctorBox =
                new JComboBox<>();

        for (Doctor d : doctors) {
            doctorBox.addItem(d.name);
        }

        JTextField date =
                new JTextField(currentDate());

        JTextField time = new JTextField();

        JTextField reason = new JTextField();

        JPanel form = new JPanel(
                new GridLayout(0, 2, 8, 8)
        );

        form.add(new JLabel("Patient:"));
        form.add(patientBox);

        form.add(new JLabel("Doctor:"));
        form.add(doctorBox);

        form.add(new JLabel("Date:"));
        form.add(date);

        form.add(new JLabel("Time:"));
        form.add(time);

        form.add(new JLabel("Reason:"));
        form.add(reason);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Book Appointment",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String patientText =
                patientBox.getSelectedItem().toString();

        int patientId = Integer.parseInt(
                patientText.substring(
                        0,
                        patientText.indexOf(" - ")
                )
        );

        String patientName =
                patientText.substring(
                        patientText.indexOf(" - ") + 3
                );

        appointments.add(new Appointment(
                nextAppointmentId++,
                patientId,
                patientName,
                doctorBox.getSelectedItem().toString(),
                date.getText().trim(),
                time.getText().trim(),
                reason.getText().trim(),
                "Scheduled"
        ));

        JOptionPane.showMessageDialog(
                this,
                "Appointment booked successfully!"
        );
    }

    // ================= UPDATE APPOINTMENT =================
    private void updateAppointmentStatus() {

        int row = appointmentTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment."
            );
            return;
        }

        int id = (int) appointmentModel.getValueAt(
                row, 0
        );

        String[] options = {
                "Scheduled",
                "Completed",
                "Cancelled"
        };

        String status = (String) JOptionPane.showInputDialog(
                this,
                "Select appointment status:",
                "Update Status",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (status == null) {
            return;
        }

        for (Appointment a : appointments) {

            if (a.id == id) {
                a.status = status;
                break;
            }
        }

        JOptionPane.showMessageDialog(
                this,
                "Appointment status updated!"
        );
    }

    // ================= DELETE APPOINTMENT =================
    private void deleteAppointment() {

        int row = appointmentTable.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select an appointment."
            );
            return;
        }

        int id = (int) appointmentModel.getValueAt(
                row, 0
        );

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete selected appointment?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            appointments.removeIf(
                    a -> a.id == id
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment deleted!"
            );
        }
    }

    // ================= PRESCRIPTION =================
    private void showPrescription() {

        if (patients.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No patients available."
            );
            return;
        }

        JComboBox<String> patientBox =
                new JComboBox<>();

        for (Patient p : patients) {
            patientBox.addItem(
                    p.id + " - " + p.name
            );
        }

        JTextArea prescription =
                new JTextArea(10, 30);

        prescription.setLineWrap(true);
        prescription.setWrapStyleWord(true);

        JPanel panel = new JPanel(
                new BorderLayout(10, 10)
        );

        JPanel top = new JPanel();

        top.add(new JLabel("Patient:"));
        top.add(patientBox);

        panel.add(top, BorderLayout.NORTH);

        panel.add(
                new JScrollPane(prescription),
                BorderLayout.CENTER
        );

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Prescription",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {

            if (prescription.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter prescription."
                );

                return;
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Prescription saved successfully!"
            );
        }
    }

    // ================= BILLING =================
    private void showBilling() {

        String[] columns = {
                "Bill ID",
                "Patient ID",
                "Patient",
                "Consultation",
                "Room",
                "Medicine",
                "Tests",
                "Total",
                "Date"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns, 0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {
                        return false;
                    }
                };

        JTable table = new JTable(model);

        for (Bill b : bills) {

            model.addRow(new Object[]{
                    b.billId,
                    b.patientId,
                    b.patientName,
                    b.consultation,
                    b.roomCharge,
                    b.medicine,
                    b.testCharge,
                    b.total,
                    b.date
            });
        }

        JButton generate =
                createButton("Generate Bill", GREEN);

        generate.addActionListener(e ->
                generateBill(model)
        );

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND);

        JLabel title = new JLabel("Billing Management");
        title.setForeground(WHITE);
        title.setFont(new Font(
                "Arial",
                Font.BOLD,
                24
        ));

        panel.add(title, BorderLayout.NORTH);
        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );
        panel.add(generate, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(
                this,
                panel,
                "Hospital Billing",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    // ================= GENERATE BILL =================
    private void generateBill(
            DefaultTableModel model) {

        if (patients.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No patients available."
            );
            return;
        }

        JComboBox<String> patientBox =
                new JComboBox<>();

        for (Patient p : patients) {

            patientBox.addItem(
                    p.id + " - " + p.name
            );
        }

        JTextField consultation =
                new JTextField("500");

        JTextField room =
                new JTextField("1000");

        JTextField medicine =
                new JTextField("500");

        JTextField tests =
                new JTextField("300");

        JPanel form = new JPanel(
                new GridLayout(0, 2, 8, 8)
        );

        form.add(new JLabel("Patient:"));
        form.add(patientBox);

        form.add(new JLabel("Consultation:"));
        form.add(consultation);

        form.add(new JLabel("Room Charge:"));
        form.add(room);

        form.add(new JLabel("Medicine:"));
        form.add(medicine);

        form.add(new JLabel("Test Charge:"));
        form.add(tests);

        int result = JOptionPane.showConfirmDialog(
                this,
                form,
                "Generate Bill",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            String patientText =
                    patientBox.getSelectedItem().toString();

            int patientId = Integer.parseInt(
                    patientText.substring(
                            0,
                            patientText.indexOf(" - ")
                    )
            );

            String patientName =
                    patientText.substring(
                            patientText.indexOf(" - ") + 3
                    );

            double consultationAmount =
                    Double.parseDouble(
                            consultation.getText()
                    );

            double roomAmount =
                    Double.parseDouble(room.getText());

            double medicineAmount =
                    Double.parseDouble(
                            medicine.getText()
                    );

            double testAmount =
                    Double.parseDouble(tests.getText());

            Bill bill = new Bill(
                    nextBillId++,
                    patientId,
                    patientName,
                    consultationAmount,
                    roomAmount,
                    medicineAmount,
                    testAmount,
                    currentDate()
            );

            bills.add(bill);

            model.addRow(new Object[]{
                    bill.billId,
                    bill.patientId,
                    bill.patientName,
                    bill.consultation,
                    bill.roomCharge,
                    bill.medicine,
                    bill.testCharge,
                    bill.total,
                    bill.date
            });

            JOptionPane.showMessageDialog(
                    this,
                    "Bill generated successfully!\n\n" +
                    "Patient: " + bill.patientName +
                    "\nTotal Amount: ₹" +
                    String.format("%.2f", bill.total)
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric amounts.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ================= ABOUT =================
    private void showAbout() {

        String message =
                "HOSPITAL MANAGEMENT SYSTEM\n\n" +
                "Developed using Java Swing\n\n" +
                "Features:\n" +
                "• Admin Login\n" +
                "• Patient Management\n" +
                "• Doctor Management\n" +
                "• Appointment Management\n" +
                "• Prescription Management\n" +
                "• Billing System\n" +
                "• Search and Filtering\n" +
                "• Dashboard Statistics\n\n" +
                "Technology:\n" +
                "Java | Swing | AWT | OOP | Collections";

        JOptionPane.showMessageDialog(
                this,
                message,
                "About Project",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= BUTTON =================
    private JButton createButton(
            String text,
            Color color) {

        JButton button = new JButton(text);

        button.setBackground(color);
        button.setForeground(WHITE);
        button.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        return button;
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(
                        UIManager
                                .getSystemLookAndFeelClassName()
                );
            } catch (Exception ignored) {
            }

            HospitalManagementSystem app =
                    new HospitalManagementSystem();

            app.setVisible(true);
        });
    }
}