package data3000_a2;

public class Employee {
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
}
