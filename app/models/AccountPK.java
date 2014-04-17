package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AccountPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "company_cnpj")
    public String companyCnpj;

    public int hashCode() {
        return ((this.companyCnpj==null) ? 0 : this.companyCnpj.hashCode());
    }

    public boolean equals(final Object other) {
        if (this == other)
            return true;

        if (!(other instanceof AccountPK))
            return false;

        AccountPK otherAccountPK = (AccountPK)other;
        return this.userId == otherAccountPK.userId &&
               this.companyCnpj.equals(otherAccountPK.companyCnpj);
    }
}