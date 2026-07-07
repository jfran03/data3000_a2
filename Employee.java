package data3000_a2;

public class Employee implements Comparable<Employee> {
    public int id;
    public String name;
    public int hoursWorked;
    public float hourlyRate;
    public float deductionProvince;
    public float deductionFederal;
    public float educationAllownace;

    public Employee(int id, String name, int hoursWorked, float hourlyRate, float deductionProvince, float deductionFederal, float educationAllownace) {
        this.id = id;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.deductionProvince = deductionProvince;
        this.deductionFederal = deductionFederal;
        this.educationAllownace = educationAllownace;
    }

    public float calcHourlySalary(){
        return this.hoursWorked * this.hourlyRate;
    }

    @Override
    public String toString() {
        return this.id + "," + this.name + "," + this.hoursWorked + "," + this.hourlyRate + "," + this.deductionProvince + "," + this.deductionFederal + "," + this.educationAllownace;
    }

    @Override
    public int compareTo(Employee other) {
        return this.name.compareTo(other.name);
    }
}
