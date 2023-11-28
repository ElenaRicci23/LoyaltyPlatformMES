package mes.corporation.loyaltyplatform.DTO;

import mes.corporation.loyaltyplatform.model.ProgrammaFedelta;
import mes.corporation.loyaltyplatform.model.TipoProgrammaFedelta;

public class ProgrammaFedeltaDTO {
    private String nome;
    private String descrizione;

    private TipoProgrammaFedelta tipoProgrammaFedelta;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public TipoProgrammaFedelta getTipoProgrammaFedelta() {
        return tipoProgrammaFedelta;
    }

    public void setTipoProgrammaFedelta(TipoProgrammaFedelta tipoProgrammaFedelta) {
        this.tipoProgrammaFedelta = tipoProgrammaFedelta;
    }

    public static ProgrammaFedeltaDTO convertPFtoEntity(ProgrammaFedelta nuovoProgramma) {
        ProgrammaFedeltaDTO programmaFedeltaDTO = new ProgrammaFedeltaDTO();
        programmaFedeltaDTO.setNome(nuovoProgramma.getNome());
        programmaFedeltaDTO.setDescrizione(nuovoProgramma.getDescrizione());
        programmaFedeltaDTO.setTipoProgrammaFedelta(nuovoProgramma.getTipoProgrammaFedelta());

        return programmaFedeltaDTO;
    }

    public static ProgrammaFedeltaDTO convertToDTO(ProgrammaFedelta programmaFedelta) {
        ProgrammaFedeltaDTO programmaFedeltaDTO=new ProgrammaFedeltaDTO();
        programmaFedeltaDTO.setTipoProgrammaFedelta(programmaFedelta.getTipoProgrammaFedelta());
        programmaFedeltaDTO.setNome(programmaFedeltaDTO.getNome());
        programmaFedeltaDTO.setDescrizione(programmaFedeltaDTO.getDescrizione());

        return programmaFedeltaDTO;
    }

}


