package org.example.healthcare.clinic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
    @Column(unique = true, length = 100)
    @NotBlank(message = "Name cannot be empty")
    private String name;
    private String address;
    @Column(unique = true, length = 100)
    private String phone;
    private String email;

}
