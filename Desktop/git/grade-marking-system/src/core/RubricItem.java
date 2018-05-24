package core;


public class RubricItem {
	
	private String rubricItemID;
	private String rubricItemWeight;
	private String rubricItemName;
	private String rubricID;
	
	public RubricItem(String rubricItemName, String rubricItemWeight) {
		this.rubricItemName = rubricItemName;
		this.rubricItemWeight = rubricItemWeight;
	}
	
	public RubricItem(String rubricID, String rubricItemName, String rubricItemWeight) {
		this.rubricID = rubricID;
		this.rubricItemName = rubricItemName;
		this.rubricItemWeight = rubricItemWeight;
	}
	
	public String getRubricID() {
		return rubricID;
	}
	
	public String getID() {
		return rubricItemID;
	}
	
	public String getName() {
		return rubricItemName;
	}
	
	public void setID(String id) {
		rubricItemID = id;
	}
	
	public void setName(String name) {
		rubricItemName = name;
	}
	
	public String getWeight() {
		return rubricItemWeight;
	}
	
	public void setWeight(String weight) {
		rubricItemWeight = weight;
	}
}
