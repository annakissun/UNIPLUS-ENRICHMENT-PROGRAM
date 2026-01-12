
> To customize the folder structure in VS Code, edit `.vscode/settings.json`.

---

## Features

- **User Management**: Register, authenticate, and manage Student/Admin accounts  
- **Session Management**: Create, edit, delete, join, and cancel sessions  
- **GUI Dashboard**: Dynamic views with real-time updates  
- **Waitlist Support**: Automatic waitlist management for full sessions  
- **File Persistence**: Save and load user/session data to files  
- **Alerts & Pop-ups**: Inform users of errors, confirmations, and messages  

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17+**  
- **JavaFX SDK** compatible with your JDK  
- **VS Code** with the **Java Extension Pack** installed  

### Setup

1. Clone this repository:

```bash
git clone https://github.com/yourusername/uniplus-enrichment.git
cd uniplus-enrichment


---

# How to Run

Once the project is set up in VS Code:

1. Make sure the **JavaFX SDK** is correctly linked to your project:
   - In VS Code, go to **Settings → Java → Project → Build Path → Add External JARs** and add the JavaFX SDK `lib` folder.
2. Compile the project:
   ```bash
   javac -d bin src/app/App.java

