package nl.nl0e0.petclinicrefactor.entity.consultation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;
import java.io.Serializable;

@Entity
@Table(name = "consultation")
public class ConsultationEntity implements Serializable {

	public ConsultationEntity() {
	}

	public String  getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getId() {
		return id;
	}

	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "symptom")
	private String symptom;

	public ConsultationEntity(MedicalRecord medicalRecord){
		this.id = medicalRecord.getConsultaionId();
	}
}
