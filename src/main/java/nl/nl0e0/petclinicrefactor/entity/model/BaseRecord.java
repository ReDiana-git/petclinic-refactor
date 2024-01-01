package nl.nl0e0.petclinicrefactor.entity.model;
public class BaseRecord {
	public Integer getOwnerId() {
		return ownerId;
	}

	public Integer getPetId() {
		return petId;
	}

	public AppointmentState getState() {
		return state;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public void setState(AppointmentState state) {
		this.state = state;
	}

	private Integer ownerId;
	private Integer petId;
	private AppointmentState state;

}
