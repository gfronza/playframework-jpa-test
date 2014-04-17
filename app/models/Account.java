package models;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames={"user_id", "company_cnpj"})
})
public class Account extends Model {

    public enum Privilege {
        One,
        Two,
        Three
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "company_cnpj", referencedColumnName = "cnpj")
    public Company company;

    @Enumerated(EnumType.STRING)
    @Required
    public Privilege privilege;

    public static Finder<Long, User> finder = new Finder<>(Long.class, User.class);

}