package sk.ukf.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "First name cannot be empty")
    @Length(max = 50, message = "First name max size is 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Length(max = 50, message = "Last name max size is 50 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]{1,64}@[A-Za-z0-9.-]{1,253}\\.[A-Za-z]{2,}$",
            message = "Invalid email"
    )
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone cannot be empty")
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Birth date cannot be empty")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Birth date must be in format YYYY-MM-DD")
    @Column(name = "birth_date")
    private String birth_date;

    @NotBlank(message = "Job title cannot be empty")
    @Length(max = 100, message = "Job title max size is 100 characters")
    @Column(name = "job_title")
    private String job_title;

    @NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "0.0", inclusive = true, message = "Salary must be >= 0")
    @Digits(integer = 12, fraction = 2, message = "Salary must be a valid monetary amount")
    @Column(name = "salary")
    private Double salary;

    @NotBlank(message = "Employment type cannot be empty")
    @Pattern(
            regexp = "Plny uvazok|Ciastocny uvazok|Dohoda|Stazista/Praktikant",
            message = "Employment type must be one of predefined values"
    )
    @Column(name = "full_time")
    private String full_time;  // String, привязан к списку employmentTypes

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFull_time() {
        return full_time;
    }

    public void setFull_time(String full_time) {
        this.full_time = full_time;
    }
}