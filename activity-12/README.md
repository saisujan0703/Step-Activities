# Activity 12: ITransaction Interface & Transaction Factory

## Overview

This activity builds a transaction abstraction for the Global Digital Bank domain. Instead of depending directly on concrete transaction classes such as `CreditTransaction` or `DebitTransaction`, client code depends on the `ITransaction` interface and receives concrete implementations via a `TransactionFactory`.

This design demonstrates:

- Interface-based programming
- Factory pattern
- Encapsulation of type-specific behavior
- Validation at the abstraction boundary

## Learning Objectives

- Model a stable transaction contract for the banking workflow
- Hide concrete implementation details behind a factory
- Validate inputs before creating or processing transactions
- Use polymorphism to work with different transaction types through a common interface

## Project Structure

```text
activity-12/
├── README.md
├── src/
│   └── com/
│       └── activity12/
│           ├── transaction/
│           │   ├── AbstractTransaction.java
│           │   ├── CreditTransaction.java
│           │   ├── DebitTransaction.java
│           │   ├── ITransaction.java
│           │   ├── InvalidTransactionTypeException.java
│           │   ├── TransactionFactory.java
│           │   └── TransferTransaction.java
│           └── tests/
│               └── TransactionFactoryTest.java
└── out/
```

## Core Design

### ITransaction
The `ITransaction` interface exposes the common behavior all transaction types must support:

- `getTransactionType()`
- `getTransactionId()`
- `getAmount()`
- `getDescription()`
- `isProcessed()`
- `process()`

### AbstractTransaction
The common validation and processing behavior is centralized in `AbstractTransaction`:

- transaction id validation
- description validation
- positive amount validation
- process state tracking
- base processing flow

Concrete classes handle transaction-specific rules with polymorphism.

### TransactionFactory
The factory creates the correct transaction implementation based on the transaction type.

Example:

```java
ITransaction transaction = TransactionFactory.createTransaction("CREDIT", "TXN-1001", "Salary credit", 2500.00);
```

## Supported Transaction Types

- `CREDIT`
- `DEBIT`
- `TRANSFER`

## Validation Rules

- Transaction ID cannot be blank
- Description cannot be blank
- Amount must be greater than zero
- Unsupported types throw `InvalidTransactionTypeException`
- A transaction cannot be processed more than once

## How to Run

### Windows (PowerShell)

```powershell
cd "<path-to-activity-12>"
if (!(Test-Path out)) { New-Item -ItemType Directory -Path out }
javac -d out $(Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.activity12.tests.TransactionFactoryTest
```

### Linux / macOS

```bash
mkdir -p out
find src -name "*.java" -print0 | xargs -0 javac -d out
java -cp out com.activity12.tests.TransactionFactoryTest
```

## Expected Output

```text
All transaction factory validation checks passed.
```

## Key Takeaways

- Client code depends on the interface rather than concrete classes.
- New transaction types can be added with minimal disruption.
- The factory centralizes creation logic and keeps the design maintainable.
