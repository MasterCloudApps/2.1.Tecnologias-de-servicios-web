package es.codeurjc.items;

public class Item {

	private long id;
	private String description;
	private boolean checked;

	public Item(String description, boolean checked) {
		this.description = description;
		this.checked = checked;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", description=" + description + ", checked=" + checked + "]";
	}

}
