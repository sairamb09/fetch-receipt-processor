

# Fetch Receipt Processor

## Description

The Fetch Receipt Processor is a Spring Boot application designed for processing receipts, calculating reward points, uploading receipt images, and displaying receipt history. It includes OAuth2 login with Google for user authentication.

## Features

1. **Receipt Processing**: Users can submit receipt data in JSON format.
2. **Points Calculation**: Calculates reward points based on the receipt data.
3. **Image Upload**: Allows users to upload images of receipts.
4. **Receipt History**: Displays a history of processed receipts with details.
5. **Category-Based Rewards**: Shows reward points categorized by item categories.
6. **OAuth2 Login**: Users can log in using their Google account.

## Project Structure

- **Receipt Controller**: Handles HTTP requests related to receipts.
- **Receipt Service**: Contains the business logic for processing receipts and calculating points.
- **Reward Response**: A POJO to encapsulate the response message and points.
- **Security Config**: Configures Spring Security to handle OAuth2 login with Google.
- **Frontend Templates**: HTML and CSS files for the user interface.

## API Endpoints

- **POST /receipts/process**: Processes a receipt and calculates the reward points.
    - Request Body: JSON with receipt data.
    - Response: Success or failure message with points calculated.
    
- **GET /receipts/{id}**: Retrieves receipt details by ID.
    - URL Parameter: `id` (The ID of the receipt).
    - Response: Receipt details in JSON format.

## Prerequisites

- **Java 17**
- **Spring Boot 3.1.5**
- **Maven**

Ensure that your environment is set up accordingly.

## Step-by-Step Guide to Run the Project

### 1. Clone the Repository

Clone the project repository to your local machine.

```bash
cd <project-directory>
git clone <repository-URL>
```

### 2. Build the Project

Use Maven to build the project.

```bash
mvn clean install
```

### 3. Run the Application

Start the Spring Boot application.

```bash
mvn spring-boot:run
```

### 4. Access the Application

Open a web browser and navigate to:

```text
http://localhost:8080
```

## Important Notes

1. **Configuration**: Ensure that the Google OAuth2 credentials in `application.properties` are correctly set up.
2. **Upload Directory**: The application uses a directory for uploading receipt images. Ensure that the directory exists and is writable.
3. **Dependencies**: The project uses Java 17 and Spring Boot 3.1.5.

## Future Enhancements

1. **Enhanced Security**: Implement proper CSRF protection and other security best practices.
2. **Database Integration**: Store receipt data in a database instead of in-memory storage.
3. **Improved UI**: Enhance the user interface for a better user experience.
4. **Additional Authentication Providers**: Support more OAuth2 providers besides Google.
5. **User Roles and Permissions**: Implement different user roles with specific permissions to access certain features.
6. **Receipt OCR Integration**: Integrate Optical Character Recognition (OCR) to automatically extract receipt data from uploaded images.
7. **Mobile Application**: Develop a mobile application for users to process receipts on the go.
8. **Multi-Language Support**: Add support for multiple languages to cater to a broader audience.
9. **Enhanced Reporting**: Provide detailed reports on user spending and reward points.
10. **Notification System**: Implement notifications for new points, promotions, or updates.
11. **Third-Party Integrations**: Integrate with third-party services like accounting software or loyalty programs.
12. **Advanced Search and Filtering**: Enhance search functionality to allow users to filter receipts by various criteria.
13. **User Feedback and Support**: Add a feedback and support system for user input and assistance.
14. **Performance Optimization**: Optimize for better performance and scalability under high load.
15. **Data Export**: Allow users to export data and points history in various formats (e.g., CSV, PDF).
16. **Gamification**: Introduce gamification elements like badges, levels, and leaderboards.
