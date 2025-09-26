class Employee
{
	int empNum;
	String empName;
	double empSalary;
	void setEmpDetails(int empNum, String empName, double empSalary)
	{
		this.empNum = empNum;
		this.empName = empName;
		this.empSalary = empSalary;
	}
	void dispEmpDetails()
	{
		System.out.println("Emp Number = "+empNum);
		System.out.println("Emp Name = "+empName);
		System.out.println("Emp Salary = "+empSalary);
	}
}
public class EmployeeTest {
	public static void main(String[] args) {
		Employee e1 = new Employee();
		e1.setEmpDetails(101, "Ramu",6000.0);
		e1.dispEmpDetails();
		Employee e2 = new Employee();
		e2.setEmpDetails(102, "Raj",8000.0);
		e2.dispEmpDetails();
	}
}
