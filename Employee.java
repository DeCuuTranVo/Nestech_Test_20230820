import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

class MonthYear implements Comparable<MonthYear> {
    private int month;
    private int year;

    public MonthYear(int pMonth, int pYear) {
        super();
        this.month = pMonth;
        this.year = pYear;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    @Override
    public int compareTo(MonthYear other) {
        if (this.year < other.year) {
            return -1;
        } else if (this.year > other.year) {
            return 1;
        } else {
            if (this.month < other.month) {
                return -1;
            } else if (this.month > other.month) {
                return 1;
            } else {
                return 0;
            }
        }
    }   
}

public class Employee {
    private String name;
    private double dailyWage;
    private Calendar startDate;
    private Calendar endDate;

    private TreeMap<MonthYear, Integer> workDayEveryMonth;
    private HashMap<MonthYear, Double> salaryPerMonth;

    public Employee(String pName, double pDailyWage, Calendar pStartDate, Calendar pEndDate) {
        super();
        this.name = pName;
        this.dailyWage = pDailyWage;
        this.startDate = pStartDate;
        this.endDate = pEndDate;

        this.workDayEveryMonth = new TreeMap<MonthYear, Integer>();
        this.salaryPerMonth = new HashMap<MonthYear, Double>();
    }

    public void updateWorkDayPerMonth() {
        for (Date date = this.startDate.getTime(); this.startDate.before(this.endDate); this.startDate.add(Calendar.DATE, 1), date=this.startDate.getTime()) {
            if ((this.startDate.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) && (this.startDate.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                int idxMonth = startDate.get(Calendar.MONTH) + 1;
                int idxYear = startDate.get(Calendar.YEAR);
                MonthYear newMonthYear = new MonthYear(idxMonth, idxYear);

                if (!this.workDayEveryMonth.containsKey(newMonthYear)) {
                    this.workDayEveryMonth.put(newMonthYear, 0);
                } else {
                    this.workDayEveryMonth.put(newMonthYear, this.workDayEveryMonth.get(newMonthYear) + 1);
                }
            }        
        }
    }

    public void computeSalaryPerMonth() {
        for (MonthYear idxMonthYear : this.workDayEveryMonth.keySet()){
            int idxMonthWorkday = this.workDayEveryMonth.get(idxMonthYear);
            double idxMonthSalary = idxMonthWorkday * this.dailyWage;
            this.salaryPerMonth.put(idxMonthYear, idxMonthSalary);
        }
    }

    public void printReport() {
        System.out.println("Name: " + this.name);
        System.out.println(String.format("Start Day: %02d/%02d/%04d", 
            this.startDate.get(Calendar.DAY_OF_MONTH),
            this.startDate.get(Calendar.MONTH),
            this.startDate.get(Calendar.YEAR)));

        System.out.println(String.format("End Day: %02d/%02d/%04d", 
            this.endDate.get(Calendar.DAY_OF_MONTH),
            this.endDate.get(Calendar.MONTH),
            this.endDate.get(Calendar.YEAR)));

        System.out.println("Payroll Summary:");
        System.out.println(String.format("%7s|%10s|%7s", "Month", "#Workdays", "Salary"));

        for (MonthYear idxMonthYear : this.workDayEveryMonth.keySet()) {
            String payrollRecord = String.format("%02d/%04d|%10d|%7.3f mVND", idxMonthYear.getMonth(),idxMonthYear.getYear(), this.workDayEveryMonth.get(idxMonthYear), (this.salaryPerMonth.get(idxMonthYear)/1000.0));
            System.out.println(payrollRecord);
        }
    }
    
    public static void main(String[] args) throws Exception {        
        Scanner scan = new Scanner(System.in);
        System.out.println("Input Employee Name:");
        String employeeName = scan.nextLine();
        System.out.println("Input Daily Wage:");
        Double dailyWage = scan.nextDouble();
        scan.nextLine();
        System.out.println("Input Start Date in dd-MM-yyyy format:");
        String startDateString = scan.nextLine();
        System.out.println("Input End Date in dd-MM-yyyy format:");
        String endDateString = scan.nextLine();

        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = null;
        try {
            startDate = formater.parse(startDateString);
        } catch (Exception ex) {
            throw new Exception("Can not parse start date");
        }

        Date endDate = null;
        try {
            scan.close();
            endDate = formater.parse(endDateString);
        } catch (Exception ex) {
            scan.close();
            throw new Exception("Can not parse end date");
        }
          
        Calendar startDateCalendar = Calendar.getInstance();
        startDateCalendar.setTime(startDate);
        startDateCalendar.set(Calendar.HOUR_OF_DAY, 0);
    
        Calendar endDateCalendar = Calendar.getInstance();
        endDateCalendar.setTime(endDate);
        endDateCalendar.set(Calendar.HOUR_OF_DAY, 0);

        Employee myEmployee = new Employee(employeeName, dailyWage, startDateCalendar, endDateCalendar);

        myEmployee.updateWorkDayPerMonth();
        myEmployee.computeSalaryPerMonth();
        myEmployee.printReport();
        scan.close();
    }
}
