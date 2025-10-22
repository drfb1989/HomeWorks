package app;

public class Employee {
    private Integer id;      // может быть null до вставки
    private String name;
    private Integer age;
    private String position;
    private Float salary;

    public Employee(Integer id, String name, Integer age, String position, Float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public Employee(String name, Integer age, String position, Float salary) {
        this(null, name, age, position, salary);
    }

    // getters / setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public Integer getAge() { return age; }
    public String getPosition() { return position; }
    public Float getSalary() { return salary; }

    public void setName(String name) { this.name = name; }
    public void setAge(Integer age) { this.age = age; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(Float salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("Employee{id=%s, name='%s', age=%d, position='%s', salary=%.2f}",
                id, name, age, position, salary);
    }
}
