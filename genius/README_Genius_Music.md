
# Genius Music Platform

🎵 A Java-based music platform inspired by Genius.com 🎵

## Description
Built with **Java** using **OOP principles** (inheritance, polymorphism, encapsulation). This application allows users to register as listeners or artists, interact with songs, follow artists, comment, and suggest lyric edits. Artists can manage their songs and albums, while admins oversee artist approvals and manage the platform's content.

---

## Features

### **User Features:**
- **Sign Up**: Create a regular user or artist account.
- **Login**: Access your account based on user role.
- **Follow Artists**: Follow your favorite artists to stay updated on their releases.
- **Comment on Songs**: Add comments to songs you enjoy.
- **Suggest Lyric Edits**: Suggest improvements to song lyrics.

### **Artist Features:**
- **Create Songs and Albums**: Upload and organize your music.
- **Manage Followers**: Track how many users follow your music.
- **Request Song Edits**: Submit song lyrics for edits and manage approval.

### **Admin Features:**
- **Approve Artists**: Approve or reject artist requests.
- **Moderate Content**: Approve or reject lyric edit requests.
- **View All Users**: Access user data and activity.

---

## Setup Instructions

### Prerequisites
Make sure Java is installed on your system:
```bash
java -version
```
If not installed, download Java [here](https://www.oracle.com/java/technologies/javase-downloads.html).

### Steps to Run the Application:
1. Clone the repository or download the project files:
```bash
git clone https://github.com/your-repository/genius-music.git
```

2. Navigate to the project directory:
```bash
cd genius-music
```

3. Compile and run the project:
```bash
javac -d bin src/*.java
java -cp bin org.example.Main
```

---

## Application Workflow

### Main Menu:
Upon opening the app, you’ll be presented with the following options:
```
  |~~~~~~Genius~~~~~~|
1.|      Sign Up     |
2.|       Login      |
0.|       Exit       |
```
- **Sign Up**: Register a new account as either a regular user or an artist.
- **Login**: Login to an existing account.

- **Admin Login**: Only for the (admin) who approves artists, you must enter the admin username and password in the login.

### Registering an Account:
1. Enter your **name**, **age**, **email**, **username**, and **password**.
2. Indicate if you're an **Artist** or not.
   - If you select **Artist**, your account will await **Admin approval**.
   - If you select **User**, you’ll gain immediate access to explore music.

### Logging In:
1. Enter your **username** and **password** to access your personal dashboard.

---

## User Dashboard

### Regular User Menu:
As a **listener**, you can:
```
  | ~~~~~~~User Menu~~~~~~~ |
1.|View Followed Artists    |
2.|View All Songs           |
3.|Comment on a Song        |
4.|Suggest Lyric Edit       |
5.|Check Notifications      |
6.|View Song Lyrics         |
7.|View Comments on a Song  |
0.|Logout                   |
Choose:  
```

### Artist Dashboard:
As an **approved artist**, you’ll have access to:
```
  | ~~~~~~Artist Menu~~~~~~ |
1.|View My Songs            |
2.|View My Albums           |
3.|Create Song              |
4.|Create Album             |
5.|Review Edit Requests     |
6.|Check Notifications      |
7.|View Song Lyrics         |
8.|View Comments on a Song  |
0.|Logout                   |
```

### Admin Dashboard:
Admins can access:
```
  | ~~~~~~~Admin Menu~~~~~~~ |
1.|Approve Artists           |
2.|Review Lyric Edit Requests|
3.|Check Notifications       |
4.|View Song Lyrics          |
0.|Logout                    | 
```

---

## Code Structure

- **Main.java**: The entry point that handles initialization and directs user flows.
- **PasswordUtils.java**: Handles password hashing using SHA-256.
- **UserStage.java**: Manages user interactions like following artists, commenting on songs, and viewing profiles.
- **ArtistStage.java**: Manages artist-specific actions like song/album creation and viewership stats.
- **AdminStage.java**: Controls admin operations including artist approval and content moderation.
- **AuthController.java**: Manages user authentication (sign up, login).
- **SongController.java**: Handles song-related features like viewing details and managing comments.
- **SearchController.java**: Manages search functionalities for songs, artists, and albums.
- **SeedData.java**: Generates initial sample data for the platform.
- **Song.java**: Represents songs in the system, including metadata, lyrics, comments, and view counts.
- **Comment.java**: Handles comments on songs, including user interaction and timestamps.
- **Notification.java**: Represents system notifications for user interactions and admin approvals.
- **LyricEditRequest.java**: Manages lyric edit requests submitted by users, including approval and notification flow.
- **Artist.java**: Represents an artist's profile, songs, and albums in the system.
- **Album.java**: Represents albums created by artists, including their tracklists and release dates.
- **Admin.java**: Manages admin actions like artist approvals and lyric edit reviews.
- **Account.java**: Base class for user, artist, and admin accounts, handling common functionality like password hashing.

---

## Contributing

To contribute:
1. Fork the repository.
2. Create a feature branch.
3. Submit a pull request with clear explanations of your changes.

---


