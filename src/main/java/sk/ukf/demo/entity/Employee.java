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

    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]{1,64}@[A-Za-z0-9.-]{1,253}\\.[A-Za-z]{2,}$",
            message = "Invalid email"
    )
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    @Column(name = "phone")
    private String phone;

    @Column(name = "birth_date")
    private String birth_date;

    @Column(name = "job_title")
    private String job_title;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "full_time")
    private boolean full_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}