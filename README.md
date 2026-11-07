# 🍳 Cookify – Smart Recipe Discovery & Ingredient Management App

## 📖 About the Project

**Cookify** is an Android-based recipe discovery and ingredient management application designed to make everyday cooking easier and more convenient.

The application helps users explore recipes from different culinary cultures, discover alternative ingredients when certain items are unavailable, and manage their available kitchen ingredients through a digital pantry.

Built with Java, XML, and Firebase, Cookify combines recipe exploration with practical ingredient management to provide a more flexible cooking experience.

## ✨ Key Features

### 🔐 User Authentication
- User registration and login.
- Firebase Authentication integration.
- Google Sign-In support.
- Secure user authentication workflow.

### 🍽️ Recipe Discovery
- Explore recipes from different culinary cultures.
- Browse an organized collection of recipes.
- Search recipes by name.
- Filter recipes by cuisine or culture.
- View detailed recipe information.

### 🔄 Ingredient Substitution
- Explore alternative ingredients for cooking.
- Find substitutes when original ingredients are unavailable.
- Make cooking more flexible with ingredient alternatives.

### 🥬 Digital Pantry Management
- Manage available kitchen ingredients.
- Keep track of pantry items.
- Make ingredient management more convenient.

### 📱 User-Friendly Interface
- Clean and intuitive Android interface.
- XML-based layouts.
- RecyclerView-based recipe browsing.
- Easy navigation between application sections.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| Java | Android application development |
| XML | UI design and layouts |
| Firebase Authentication | User authentication |
| Cloud Firestore | Cloud database and data management |
| Android Studio | Development environment |
| RecyclerView | Dynamic recipe lists |
| Glide | Image loading |

## 🏗️ Application Structure

The application is organized into different activities and reusable components.

| Component | Responsibility |
|---|---|
| `SplashActivity` | Application launch screen |
| `LoginActivity` | User login |
| `SignupActivity` | User registration |
| `HomeActivity` | Main application dashboard |
| `BrowseActivity` | Recipe discovery and filtering |
| `RecipeDetailsActivity` | Detailed recipe information |
| `SubstituteActivity` | Ingredient substitution |
| `PantryActivity` | Pantry management |
| `RecipeAdapter` | Recipe list presentation |
| `NavigationHelper` | Application navigation |

## ⚙️ Installation & Setup

Follow these steps to run Cookify locally.

**1. Clone the repository**


**2. Open the project**

- Open Android Studio.
- Select **Open** and choose the cloned Cookify project.
- Wait for Gradle synchronization.

**3. Configure Firebase**

- Create a Firebase project in the Firebase Console.
- Register your Android application using its package name.
- Add the `google-services.json` configuration file to the app module.
- Enable Firebase Authentication and configure the required sign-in methods.
- Set up Cloud Firestore and configure the required collections.

**4. Run the application**

- Connect an Android device or start an emulator.
- Build and run the project using Android Studio.

## 🔥 Firebase Configuration

Cookify uses Firebase services for authentication and cloud-based data management.

The application uses Cloud Firestore to manage recipe-related information, including recipe details and culinary culture data.

**Note:** Firebase configuration files and credentials are not included in this repository. You must configure your own Firebase project before running the application.

## 🚀 Future Improvements

- Personalized recipe recommendations.
- Advanced ingredient-based recipe search.
- Meal planning and weekly cooking schedules.
- Save favorite recipes.
- Enhanced pantry tracking.
- Improved nutritional information and dietary filters.

## 👩‍💻 Developer

**Fahima Haque Nupur**
**Zafrin Akter Binty**

Computer Science & Engineering Student  
Southeast University



