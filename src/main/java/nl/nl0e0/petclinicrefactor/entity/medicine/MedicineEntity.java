package nl.nl0e0.petclinicrefactor.entity.medicine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import nl.nl0e0.petclinicrefactor.entity.medicalRecord.MedicalRecord;

import java.io.Serializable;

@Entity
@Table(name = "medicine")
public class MedicineEntity implements Serializable {
	@Id
	@Column(name = "id")
	private String id;

	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
	public String getMedicines(){
		return this.medicines;
	}

	private String medicines;

	public MedicineEntity() {

	}


	public MedicineEntity(MedicalRecord medicalRecord){
		this.id = medicalRecord.getMedicineId();
	}
}
