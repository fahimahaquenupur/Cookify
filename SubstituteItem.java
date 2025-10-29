package bd.edu.seu.cookify.model;

public class SubstituteItem {

    private String name;
    private String amount;
    private String imageUrl;
    private String category;

    public SubstituteItem() {}

    public SubstituteItem(String name, String amount, String imageUrl, String category) {
        this.name = name;
        this.amount = amount;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getName() { return name; }
    public String getAmount() { return amount; }
    public String getImageUrl() { return imageUrl; }
    public String getCategory() { return category; }
}


