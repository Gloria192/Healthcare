package org.example.healthcare.clinic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicCreateDto {
    private long id;
    private String name;
    private String address;
    private String phone;

}
