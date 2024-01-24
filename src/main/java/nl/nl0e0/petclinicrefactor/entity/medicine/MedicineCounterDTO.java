package nl.nl0e0.petclinicrefactor.entity.medicine;

public class MedicineCounterDTO {
    String recordId;
    String state;
    public MedicineCounterDTO(String recordId, String state){
        this.recordId = recordId;
        this.state = state;
    }
}
