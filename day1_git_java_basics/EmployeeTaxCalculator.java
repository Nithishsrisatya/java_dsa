import java.util.Scanner;

public class EmployeeTaxCalculator {

    private String name;
    private String location;
    private String designation;
    private double basicSalary;

    private double hra;
    private double da;
    private double monthlyBonus;

    private double monthlySalaryWithoutBonus;
    private double monthlyGrossSalary;
    private double annualGrossSalary;
    private double annualBonus;

    private double charityAmount;
    private double homeLoanInterest;
    private double section87Deduction;

    private double taxableIncome;
    private double totalStandardDeductions;
    private double taxPayable;

    public EmployeeTaxCalculator(String name, String location, String designation, double basicSalary,
                                double charityAmount, double homeLoanInterest, double section87Deduction) {
        this.name = name;
        this.location = location.toLowerCase();
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.charityAmount = charityAmount;
        this.homeLoanInterest = homeLoanInterest;
        this.section87Deduction = section87Deduction;

        calculateComponents();
        calculateTaxableIncome();
        calculateTaxPayable();
    }

    private void calculateComponents() {
        // Calculate HRA based on location
        switch (location) {
            case "cosmopolitan":
                hra = 0.15 * basicSalary;
                break;
            case "semi urban":
                hra = 0.10 * basicSalary;
                break;
            case "rural":
                hra = 0.05 * basicSalary;
                break;
            default:
                hra = 0.05 * basicSalary; // default to rural if unknown
                break;
        }

        da = 0.12 * basicSalary;
        monthlyBonus = 0.20 * basicSalary;

        monthlySalaryWithoutBonus = basicSalary + hra + da;
        monthlyGrossSalary = monthlySalaryWithoutBonus + monthlyBonus;
        annualGrossSalary = monthlyGrossSalary * 12;
        annualBonus = monthlyBonus * 12;
    }

    private void calculateTaxableIncome() {
        // Deduct charity amount under 80G (full amount)
        // Deduct home loan interest up to 1,20,000
        // Deduct section 87 up to 3,00,000

        double homeLoanDeduction = Math.min(homeLoanInterest, 120000);
        double section87DeductionAllowed = Math.min(section87Deduction, 300000);

        totalStandardDeductions = charityAmount + homeLoanDeduction + section87DeductionAllowed;

        taxableIncome = annualGrossSalary - totalStandardDeductions;
        if (taxableIncome < 0) {
            taxableIncome = 0;
        }
    }

    private void calculateTaxPayable() {
        double income = taxableIncome;
        taxPayable = 0;

        if (income <= 500000) {
            taxPayable = 0;
        } else if (income <= 800000) {
            taxPayable = (income - 500000) * 0.10;
        } else if (income <= 1000000) {
            taxPayable = (300000 * 0.10) + (income - 800000) * 0.15;
        } else if (income <= 1200000) {
            taxPayable = (300000 * 0.10) + (200000 * 0.15) + (income - 1000000) * 0.20;
        } else if (income <= 1500000) {
            taxPayable = (300000 * 0.10) + (200000 * 0.15) + (200000 * 0.20) + (income - 1200000) * 0.20;
        } else if (income <= 2000000) {
            taxPayable = (300000 * 0.10) + (200000 * 0.15) + (200000 * 0.20) + (300000 * 0.20) + (income - 1500000) * 0.25;
        } else {
            taxPayable = (300000 * 0.10) + (200000 * 0.15) + (200000 * 0.20) + (300000 * 0.20) + (500000 * 0.25) + (income - 2000000) * 0.30;
        }
    }

    public void printLevel1Details() {
        System.out.println("Employee Details:");
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Designation: " + designation);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Monthly Gross Salary: " + monthlyGrossSalary);
        System.out.println("Annual Gross Salary: " + annualGrossSalary);
        System.out.println("Bonus Earned for the Year: " + annualBonus);
    }

    public void printLevel2Details() {
        System.out.println("Taxable Income: " + taxableIncome);
    }

    public void printLevel3Details() {
        System.out.println("Employee Details:");
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Designation: " + designation);
        System.out.println();

        System.out.println("Salary Details:");
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Monthly Gross Salary: " + monthlyGrossSalary);
        System.out.println("Annual Gross Salary: " + annualGrossSalary);
        System.out.println("Annual Bonus: " + annualBonus);
        System.out.println();

        System.out.println("Taxable Income: " + taxableIncome);
        System.out.println("Total Standard Deductions: " + totalStandardDeductions);
        System.out.println("Tax Amount Payable: " + taxPayable);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee location (cosmopolitan, semi urban, rural): ");
        String location = scanner.nextLine();

        System.out.print("Enter employee designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter basic salary: ");
        double basicSalary = scanner.nextDouble();

        System.out.print("Enter charity amount under 80G: ");
        double charityAmount = scanner.nextDouble();

        System.out.print("Enter home loan interest paid: ");
        double homeLoanInterest = scanner.nextDouble();

        System.out.print("Enter section 87 deduction amount: ");
        double section87Deduction = scanner.nextDouble();

        EmployeeTaxCalculator employee = new EmployeeTaxCalculator(name, location, designation, basicSalary,
                charityAmount, homeLoanInterest, section87Deduction);

        System.out.println("\n--- Level 1 Details ---");
        employee.printLevel1Details();

        System.out.println("\n--- Level 2 Details ---");
        employee.printLevel2Details();

        System.out.println("\n--- Level 3 Details ---");
        employee.printLevel3Details();

        scanner.close();
    }
}
