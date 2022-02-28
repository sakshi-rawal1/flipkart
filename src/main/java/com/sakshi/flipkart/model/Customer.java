package com.sakshi.flipkart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_tb",
        uniqueConstraints = @UniqueConstraint(
                name = "email_unique",
                columnNames = "email_id"
        )
)
@Builder
public class Customer {

    public enum Gender{
        MALE,FEMALE;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
    @Column(name = "email_id")
    private String emailId;
    private String imageUrl;
    private String password;
    private Boolean status;
    private Gender gender;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private List<Address> addresses;
}
