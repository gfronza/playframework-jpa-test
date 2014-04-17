package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.validator.constraints.br.CNPJ;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Company extends Model {

    @Id
    @Column(length = 14, nullable = false, unique = true)
    @Required
    @MinLength(value = 14)
    @MaxLength(value = 14)
    @CNPJ
    public String cnpj;

    @Column(length = 255, nullable = false)
    @Required
    @MinLength(value = 1)
    @MaxLength(value = 255)
    public String tradeName;

    @Column(length = 255, nullable = false)
    @Required
    @MinLength(value = 1)
    @MaxLength(value = 255)
    public String corporateName;

    @Column(length = 255, nullable = false)
    @Required
    @MinLength(value = 1)
    @MaxLength(value = 255)
    public String departament;

    @Column(length = 255, nullable = false)
    @Required
    @MinLength(value = 1)
    @MaxLength(value = 255)
    public String contact;

    @Column(length = 50, nullable = false)
    @Required
    @MinLength(value = 1)
    @MaxLength(value = 50)
    public String phone;

    @Required
    @Min(1)
    @Max(Integer.MAX_VALUE)
    public Integer employers;

    @OneToMany(mappedBy = "company")
    public List<Account> accounts;

    public static Finder<String, Company> finder = new Finder<>(String.class, Company.class);

}