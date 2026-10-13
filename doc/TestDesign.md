# Test Design
This file contains the test design using the methods established in Module 1. Boundary 
Value Analysis is excluded because the method's inputs do not have specified numeric ranges or boundaries. 
The specification defines only null and non-null behavior. 

## 1. Equivalence Partitioning (EP)

| TCI| Parameter | Equivalence Partition | Expected Behavior                     |
|---|---|---|---------------------------------------|
| EP1 | resourceId | Null | Return false                          
| EP2 | resourceId | Non-null | Proceed to check internal conditions  |

**Note:** `memberEmail` is excluded from equivalence partitioning because the specification does not define validation rules or expected behavior for different email values.

### 1.1 Equivalence Partitions Test Cases
| Test Case | Test Coverage | resourceId | Expected Result                        |
|---|---------------|------------|----------------------------------------|
| TC1 | EP1           | Null       | Return false                           |
| TC2 | EP2           | Valid UUID | Proceed to check internal conditions |

**Note:** TC2 assumes that a valid UUID is non-null. The expected result depends on the resource's availability and the outcomes of subsequent operations. 

## 2. Decision Table (DT)
Infeasible combinations are excluded because the method's control flow prevents certain conditions from being evaluated once an earlier return statement or exception is reached. 
The symbol `-` indicates that a condition is not evaluated for that rule because execution does not reach it.

|  | Rule 1 | Rule 2 | Rule 3 | Rule 4 | Rule 5 |
|---|---|---|---|---|---|
| **Causes** |  |  |  |  |  |
| resourceId == null | T | F | F | F | F |
| Resource available | - | F | T | T | T |
| Status update succeeds | - | - | F | T | T |
| Email sending succeeds | - | - | - | F | T |
| **Effects** |  |  |  |  |  |
| Result | Return false | Return false | DatabaseFailureException | EmailFailureException | Return true |

### 2.1 Decision Table Test Cases

| Test Case | Test Coverage | resourceId | Resource Available | Status Update Succeeds | Email Succeeds | Expected Result |
|---|---------------|---|---|---|---|---|
| TC3 | Rule 1        | null | N/A | N/A | N/A | Return false |
| TC4 | Rule 2        | Valid UUID | false | N/A | N/A | Return false |
| TC5 | Rule 3        | Valid UUID | true | false | N/A | Throw DatabaseFailureException |
| TC6 | Rule 4        | Valid UUID | true | true | false | Throw EmailFailureException |
| TC7 | Rule 5        | Valid UUID | true | true | true | Return true |


## 3. Finalized Test Suite
The test suite contains all unique test cases from equivalence partitioning and decision table testing, with redundant test cases removed.

| Test Case | resourceId | Resource Available | Status Update Succeeds | Email Succeeds | Expected Result |
|---|---|---|---|---|---|
| TC1 | null | N/A | N/A | N/A | Return false |
| TC2 | Valid UUID | false | N/A | N/A | Return false |
| TC3 | Valid UUID | true | false | N/A | Throw DatabaseFailureException |
| TC4 | Valid UUID | true | true | false | Throw EmailFailureException |
| TC5 | Valid UUID | true | true | true | Return true |
