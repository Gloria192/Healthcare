package org.example.healthcare;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column ( length = 50, nullable = false)
    private String firstName;
    @Column ( length = 50, nullable = false)
    private String lastName;
    @Column(unique = true,nullable = false)
    @Email
    private String email;
    @Column ( length = 20, nullable = false)
    @Pattern(regexp = "^[0-9]+$", message = "Phone must contain only numbers")
    private String phone;
    @Column (  nullable = false)
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

}
