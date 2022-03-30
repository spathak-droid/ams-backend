package allMySons.allMySons.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "AMSsandesh")
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotBlank(message = "Device name is required")
    private String device;

    @Column
    @NotBlank(message = "Operating System name is required")
    private String os;

    @Column
    @NotBlank(message = "manufacturer name is required")
    private String manufacturer;

    @Column
    private String lastCheckedOutDate;

    @Column
    private String lastCheckedOutBy;

    @Column
    private boolean isCheckedOut;



}
