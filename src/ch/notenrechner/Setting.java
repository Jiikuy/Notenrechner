package ch.notenrechner;

public class Setting {
	private String name;
	private String value;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return getName() + "=" + getValue();
	}
	
	public Setting(String name, String value) {
		this.name = name;
		this.value = value;
	}
}
