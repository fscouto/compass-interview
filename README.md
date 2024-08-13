# Contact Dedup

This project is part of the interview process for **Compass**.

## Instructions

You have been asked to take a list of contact information and identify which contacts are
potentially duplicates. You need to write a function that will do the following:

1. Identify which contacts are possible matches.
2. A score for each match that represents how accurate you believe the match is. This
   scoring is defined by you.
3. A contact might have multiple or no matches
4. All processing should be done in working memory (no database).

## Requirements

- Java 17 (OpenJDK 17.0.1)
- Maven

## Usage

1. Compile the code:

```bash
mvn clean package
```

2. Test the code:

```bash
mvn test
```

3. Run the code:

```bash
java -cp target/classes/ compass.interview.Main <CSV_FILE_PATH>
```

### Example

```bash
java -cp target/classes/ compass.interview.Main ./src/main/resources/input.csv
```
