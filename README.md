Features:
RESTful Endpoints for managing CSRs:

Create a new CSR.
Modify a specific CSR.
Delete a specific CSR.
Retrieve all CSRs.
Retrieve a specific CSR.
Database Structure:

CSR Table: Stores information about CSRs.
CSR Authority Table: Maintains the authority associated with each CSR.
Security:

Uses Basic Authentication for securing endpoints.
Ensures that API operations are authorized based on the CSR’s corresponding authority.
Custom Exception Handling:

Returns descriptive error messages for invalid inputs, missing data, or conflicts.
Includes:
CSRNotFoundException
CSRAlreadyExistsException
InvalidInputException
Errors are standardized using a custom ErrorResponse format.
Tools:

Tested with Postman for all API operations.
