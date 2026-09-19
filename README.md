# Activity 11: IAccount Interface & Account Factory

## Overview

This activity introduces a stable account abstraction for the Global Digital Bank domain. Instead of depending directly on concrete account classes such as `SavingsAccount` or `CurrentAccount`, client code depends on the `IAccount` interface and receives concrete implementations via an `AccountFactory`.

This design demonstrates the following object-oriented principles:

- Interface Segregation Principle (ISP)
- Dependency Inversion Principle (DIP)
- Factory Pattern
- Encapsulation of account-specific logic inside concrete subclasses

## Learning Objectives

- Understand the purpose of an interface-first account contract
- Design a stable abstraction that does not change when new account types are added
- Use a factory to hide concrete implementations from clients
- Implement validation and exception handling for invalid account creation and operations

## Project Structure

```text
activity-11/
├── README.md
├── src/
│   └── com/
│       └── activity11/
│           ├── account/
│           │   ├── AbstractAccount.java
│           │   ├── AccountFactory.java
│           │   ├── CurrentAccount.java
│           │   ├── FixedDepositAccount.java
│           │   ├── IAccount.java
│           │   ├── InvalidAccountTypeException.java
│           │   ├── SalaryAccount.java
│           │   └── SavingsAccount.java
│           └── tests/
│               └── AccountFactoryTest.java
└── out/
```

## Core Design

### IAccount
The `IAccount` interface exposes only the operations that a generic account client needs:

- `deposit(double amount)`
- `withdraw(double amount)`
- `getBalance()`
- `getAccountType()`

### AbstractAccount
The shared logic is centralized in `AbstractAccount`, including:

- balance tracking
- active/inactive status checks
- validation routines
- common deposit/withdraw handling
- error and exception handling

Concrete account types keep their own rules in subclasses.

### AccountFactory
The factory hides implementation details and creates the correct account based on the account type identifier.

Example:

```java
IAccount account = AccountFactory.createAccount("SAVINGS", "SA-1001", "Alice", "1234", 1000.0);
```

## Account Types

- `SavingsAccount`
- `CurrentAccount`
- `FixedDepositAccount`
- `SalaryAccount`

## Validation Rules

- Account identifiers and holder names cannot be blank
- PIN must be valid
- Initial balance cannot be negative
- Deposits must be positive
- Withdrawals must be positive and valid for the account type
- Unsupported account types throw `InvalidAccountTypeException`

## How to Run

### Windows (PowerShell)

```powershell
cd "<path-to-activity-11>"
if (!(Test-Path out)) { New-Item -ItemType Directory -Path out }
javac -d out $(Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.activity11.tests.AccountFactoryTest
```

### Linux / macOS

```bash
mkdir -p out
find src -name "*.java" -print0 | xargs -0 javac -d out
java -cp out com.activity11.tests.AccountFactoryTest
```

## Expected Output

```text
All account factory validation checks passed.
```

## Key Takeaways

- Client code depends on the abstraction, not concrete classes.
- New types can be added without modifying the existing account classes.
- The factory centralizes creation logic and keeps the design clean and extensible.

## Activity Requirements Covered

- Interface-based account contract implemented
- Abstract base class implemented
- Factory returns `IAccount`
- Concrete account classes implemented
- Validation and exceptions implemented
- ISP and DIP reflected in the design
