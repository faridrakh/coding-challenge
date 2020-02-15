package gln.test.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="gln.user")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="avatar")
    private String avatar;

    @Column(name="job")
    private String job;

    @Column(name="dt_create")
    private Date dtCreate;

    @Column(name="dt_update")
    private Date dtUpdate;

    @Transient
    private String createAt;
    @Transient
    private String updateAt;
//    @Transient
//    private Integer total;
}
