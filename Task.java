import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class Employee {
    private String id;
    private String name;
    private Date dateOfBirth;
    private String email;
    private Date joiningDate;

    public Employee(String id, String name, Date dateOfBirth, String email, Date joiningDate) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.joiningDate = joiningDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "ID: " + id + ", Name: " + name + ", Date of Birth: " + sdf.format(dateOfBirth) + ", Email: " + email;
    }
}

class Leave<T> {
    private String employeeType;
    private int vacationDays;
    private int sickDays;

    public Leave(String employeeType, int vacationDays, int sickDays) {
        this.employeeType = employeeType;
        this.vacationDays = vacationDays;
        this.sickDays = sickDays;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public int getSickDays() {
        return sickDays;
    }

    public String toString() {
        return "Employee Type: " + employeeType + ", Vacation Days: " + vacationDays + ", Sick Days: " + sickDays;
    }
}

public class PaperlessOffice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            System.out.println("Enter Employee ID:");
            String id = scanner.nextLine();

            System.out.println("Enter Name:");
            String name = scanner.nextLine();

            System.out.println("Enter Date of Birth (dd/MM/yyyy):");
            String dobString = scanner.nextLine();
            Date dob = parseDate(dobString);

            if (dob == null) {
                System.out.println(" Please use the correct format dd/MM/yyyy.");
                continue;
            }

            System.out.println("Enter Email:");
            String email = scanner.nextLine();

            System.out.println("Is the employee a Staff or Officer? (Type 'Staff' or 'Officer'):");
            String employeeType = scanner.nextLine();

            System.out.println("Enter Joining Date (dd/MM/yyyy):");
            String joiningDateString = scanner.nextLine();
            Date joiningDate = parseDate(joiningDateString);

            if (joiningDate == null) {
                System.out.println(" Please use the correct format dd/MM/yyyy.");
                continue;
            }

            Employee employee = new Employee(id, name, dob, email, joiningDate);
            Leave<?> leave = calculateLeave(employee, employeeType);

            System.out.println("\nEmployee Details:");
            System.out.println(employee);
            System.out.println(leave);
        }

        scanner.close();
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static <T> Leave<?> calculateLeave(Employee employee, String employeeType) {
        int year = 2023; // Assuming the current year is 2023

        if (employee.getJoiningDate() == null) {
            return new Leave<>("Unknown", 0, 0);
        }

        Leave<?> leave = getLeaveForEmployeeType(employeeType);
        int vacationDays = calculateVacationLeave(employee.getJoiningDate(), leave.getVacationDays());
        int sickDays = calculateSickLeave(employee.getJoiningDate(), leave.getSickDays());

        return new Leave<>(employeeType, vacationDays, sickDays);
    }

    private static int calculateVacationLeave(Date joiningDate, int maxVacationDays) {
        int year = 1900 + joiningDate.getYear();
        int daysInYear = isLeapYear(year) ? 366 : 365;
        Date endOfYear = new Date(year - 1900, 11, 31);
        long daysWorked = (endOfYear.getTime() - joiningDate.getTime()) / (24 * 60 * 60 * 1000) + 1;
        return (int) Math.round((daysWorked * maxVacationDays) / daysInYear);
    }

    private static int calculateSickLeave(Date joiningDate, int maxSickDays) {
        int year = 1900 + joiningDate.getYear();
        int daysInYear = isLeapYear(year) ? 366 : 365;
        Date endOfYear = new Date(year - 1900, 11, 31);
        long daysWorked = (endOfYear.getTime() - joiningDate.getTime()) / (24 * 60 * 60 * 1000) + 1;
        return (int) Math.round((daysWorked * maxSickDays) / daysInYear);
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static Leave<?> getLeaveForEmployeeType(String employeeType) {
        if (employeeType.equalsIgnoreCase("Officer")) {
            return new Leave<>(employeeType, 15, 10);
        } else {
            return new Leave<>(employeeType, 10, 7);
        }
    }
}
