package bd.edu.seu.cookify.model;

public class Recipe {
    // Firestore document ID (not stored in Firestore fields, set from doc.getId())
    private String id;
    // Recipe name (example: "Pasta Carbonara")
    private String name;
    // Category of the recipe (example: "Dinner", "Dessert")
    private String category;
    // Image URL of the recipe (stored in Firestore/Cloud Storage)
    private String imageUrl;

    // Empty constructor required by Firebase Firestore to map data
    public Recipe() {}

    // Constructor used when creating a Recipe object manually
    public Recipe(String name, String category, String imageUrl) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Getter method → returns recipe name
    public String getName() { return name; }
    // Getter method → returns recipe category
    public String getCategory() { return category; }
    // Getter method → returns recipe image URL
    public String getImageUrl() { return imageUrl; }

    // ID accessors
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
