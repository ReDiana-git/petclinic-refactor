package nl.nl0e0.petclinicrefactor.entity.consultation;

public class UpdateConsultationDTO {
    public String getMedicines(){
		return medicines;
	};

    public String getRecordId() {
		return RecordId;
	}

	private String RecordId;
	private String medicines;
}
