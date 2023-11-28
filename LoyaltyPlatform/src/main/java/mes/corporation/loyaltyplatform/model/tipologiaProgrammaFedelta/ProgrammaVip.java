package mes.corporation.loyaltyplatform.model.tipologiaProgrammaFedelta;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;

@Entity
public class ProgrammaVip extends ProgrammaFedelta {
    @Id
    @GeneratedValue
    Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

