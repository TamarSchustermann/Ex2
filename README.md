# Ex2
Project Structure
Cell Class:

Responsible for representing a cell in a spreadsheet.
Supports the following data types:
Numbers (Double)
Texts (String)
Formulas (Formula)
Spreadsheet Class:

Manages a two-dimensional array of cells.
Provides functions such as spreadsheet creation, cell access, value computation, and dependency depth management.
Main Functions:

isNumber(String text): Identifies if the string is a valid number.
isText(String text): Identifies if the string is text.
isFormula(String text): Identifies if the string is a valid formula.
computeFormula(String formula): Calculates the value of the formula.
eval(int x, int y): Computes the displayed value of a specific cell.
evalAll(): Computes the values of all cells in the spreadsheet.
Key Features
Cell Validation (isCell):

Identifies if a valid cell starts with = followed by a letter (uppercase/lowercase) and a number between 0 and 99.
Formula Validation (isFormula):

Validates if a formula adheres to the rules of parentheses, operators, and cell references.
Dependency Depth Calculation (depth):

Computes the dependency depth of each cell based on other cells.
Usage Examples
Valid Formulas:
=1
=1.2
=(A1+2)*3
=A1
=(2+A3)/A2
Invalid Formulas:
a (Invalid text)
AB (Text without a number)
=() (Empty parentheses)
=5** (Double operator)
Main Classes
Cell Class: Represents a single cell.
Spreadsheet Class: Manages a two-dimensional array of cells.
FormulaValidator Class: Provides functions for formula validation and value computation.
Testing
Each class was tested using JUnit.
Main tests include:
Validation of valid and invalid cells (isCell).
Formula value computation (computeFormula).
Dependency depth calculation (depth).
