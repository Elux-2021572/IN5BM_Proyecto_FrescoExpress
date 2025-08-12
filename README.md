# 🥗 FrescoExpress

![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-17+-lightblue.svg)
![JDBC](https://img.shields.io/badge/JDBC-latest-green.svg)

A comprehensive fresh food delivery management system built with JavaFX, following the MVC architecture pattern and using JDBC for database operations.

## ✨ Features

- **Inventory Management**: Track fresh produce and grocery items
- **Order Processing**: Manage customer orders from creation to delivery
- **Customer Management**: Store and manage customer data and preferences
- **Delivery Tracking**: Monitor delivery status and logistics
- **Employee Management**: Handle staff information and assignments
- **Reporting**: Generate sales, inventory, and performance reports
- **User Authentication**: Secure login system with role-based access
- **Intuitive UI**: User-friendly interface with responsive design

## 🛠️ Technologies Used

- **Java**: Core programming language
- **JavaFX**: GUI framework for desktop application
- **JDBC**: Database connectivity for data persistence
- **CSS**: Custom styling for the user interface
- **MVC Pattern**: Architecture for clean code organization
- **MySQL/PostgreSQL**: Relational database
- **Scene Builder**: Visual layout tool for JavaFX

## 🚀 Installation and Setup

### Prerequisites
- Java 17 or higher
- JavaFX SDK 17 or higher
- MySQL/PostgreSQL database
- Maven (optional)

### 1. Clone the repository
```bash
git clone https://github.com/Elux-2021572/IN5BM_Proyecto_FrescoExpress.git
cd IN5BM_Proyecto_FrescoExpress
```

### 2. Configure database
Create a MySQL/PostgreSQL database and update the database configuration in the `DBConfig.java` file or properties file.

### 3. Build and run the application
```bash
# Using Maven (if configured)
mvn clean javafx:run

# Using Java directly
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar FrescoExpress.jar
```

## 📚 Project Structure

The project follows the MVC (Model-View-Controller) architecture:
- **Model**: Data entities and business logic
- **View**: FXML files for UI layout
- **Controller**: JavaFX controllers handling user interactions
- **DAO**: Data Access Objects for database operations
- **Utils**: Utility classes and helpers

## 📋 Database Schema

The application uses the following main tables:
- **Products**: Inventory items with details
- **Customers**: Customer information
- **Orders**: Customer purchase orders
- **OrderDetails**: Line items in each order
- **Employees**: Staff information
- **Deliveries**: Delivery tracking information

## 🔑 Authentication

The application includes a login system with different access levels:
- **Administrator**: Full access to all features
- **Manager**: Access to reports and employee management
- **Staff**: Access to orders and inventory
- **Delivery**: Access to delivery tracking

## 📖 Usage Examples

### Product Management
- Add new products with details like name, category, price, and quantity
- Update product information and stock levels
- View product sales history and performance

### Order Processing
- Create new customer orders
- Add products to orders with quantity
- Apply discounts and calculate totals
- Track order status through fulfillment

### Reporting
- Generate daily/weekly/monthly sales reports
- View inventory status and reorder suggestions
- Analyze delivery performance and times

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👨‍💻 Author

- **Elux-2021572** - [GitHub Profile](https://github.com/Elux-2021572)

---

Made with ❤️ by Elux-2021572
