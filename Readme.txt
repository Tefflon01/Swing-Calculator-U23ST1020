


Calculator Application

This project is a simple GUI-based calculator implemented in Java using Swing. It supports basic arithmetic operations such as addition, subtraction, multiplication, division, as well as percentage and sign inversion functionalities.

Features

- Graphical User Interface (GUI): Built using Java Swing with a modern look and feel.
- Basic Operations: Supports addition (`+`), subtraction (`-`), multiplication (`x`), and division (`÷`).
- Special Functions:
  - Clear (`C`): Resets the display and internal states.
  - Sign Toggle (`±`): Inverts the sign of the current number.
  - Percentage (`%`): Calculates the percentage (divides the current number by 100).
- Decimal Support: Allows input of decimal numbers and prevents multiple decimals in a single number.
- Error Handling: Detects division by zero and displays an error message.
- Responsive UI: Buttons change color on hover to improve user experience.

Code Structure

The application is organized in a single Java class named `Calculator` under the `calculator` package. Below is an overview of the main components:

1. Display Field

- Component:A non-editable `JTextField` that shows the current number or result.
- Styling: Right-aligned, uses a bold Arial font, and has a custom background and border.

2. Main Panel and Layout

- Main Panel: Uses a `BorderLayout` to separate the display area from the buttons.
- Buttons Panel: Arranged in a grid (`GridLayout`) that contains all calculator buttons.

 3. Buttons Creation and Styling

- `createButton(String label)` Method:
  - Creates a `JButton` with a specified label.
  - Sets fonts, colors, and styles depending on the button’s type (number, operator, or function).
  - Adds an `ActionListener` to handle button clicks.
  - Adds a `MouseListener` for a hover effect that darkens the button background.

4. Event Handling

- `handleButtonClick(String label)` Method:
  - Determines the action based on the button pressed.
  - Handles input for numbers, operators, and special functions.
  - Manages the state of the calculator (e.g., starting a new number, performing calculations).

- Operator Handling:
  - When an operator button (such as `+`, `-`, `x`, `÷`) is pressed, the current value is stored as the first operand, and the operation is recorded.
  
- Calculation:
  - The `calculateResult()` method performs the arithmetic operation based on the stored operator and operands.
  - It also checks for division by zero and formats the result to remove unnecessary decimal places.

5. Constructors

- Default Constructor: Sets up the full calculator UI, adds the display and buttons to the main frame, and configures the window properties.
- Additional Constructors: Allow for custom instantiation with a pre-defined display field or title.

6. Main Method

- The `main` method sets the system’s look and feel for the UI.
- It then initializes and displays the calculator on the Event Dispatch Thread using `SwingUtilities.invokeLater`.

Running the Application

To run the Calculator application:

1. Compile the Code:
   "bash
   javac -d bin src/calculator/Calculator.java"

2. Run the Application:
   "bash
   java -cp bin calculator.Calculator"

Alternatively, you can run the application from an IDE such as IntelliJ IDEA, Eclipse, or NetBeans by simply running the `Calculator` class.

License
Copyright (c) 2025 Isah Maji-Isah

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

